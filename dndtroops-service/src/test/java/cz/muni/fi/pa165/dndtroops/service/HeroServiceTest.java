package cz.muni.fi.pa165.dndtroops.service;

import cz.muni.fi.pa165.dndtroops.ServiceConfiguration;
import cz.muni.fi.pa165.dndtroops.dao.HeroDao;
import cz.muni.fi.pa165.dndtroops.entities.Hero;
import cz.muni.fi.pa165.dndtroops.entities.Role;
import cz.muni.fi.pa165.dndtroops.entities.Troop;
import cz.muni.fi.pa165.dndtroops.enums.Power;
import cz.muni.fi.pa165.dndtroops.service.battle.HeroState;
import org.assertj.core.api.SoftAssertions;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author Jiří Novotný
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
public class HeroServiceTest extends AbstractTransactionalTestNGSpringContextTests {
    @Mock
    private HeroDao heroDao;
    @Mock
    private RandomService randomService;
    @Mock
    private RoleService roleService;

    @InjectMocks
    private HeroServiceImpl heroService;

    private Troop villains;
    private Troop superheroes;

    private Role human;
    private Role alien;

    private Hero batman;
    private Hero superman;

    @BeforeMethod
    public void setupMocks() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void setupEntities() {
        villains = new Troop("Villains", "Bring chaos", 1000);
        superheroes = new Troop("Superheroes", "Save humanity", 1);

        human = new Role("Human", "Just a puny human...", Power.WEAPONS, 15, 10);
        alien = new Role("Alien", "Badass alien", Power.MAGIC, 20, 1);

        batman = new Hero("Batman", superheroes, 100, 1, human);
        superman = new Hero("Superman", superheroes, 100, 2, alien);
    }

    @Test
    public void createHeroTest() {
        doAnswer(invocation -> {
            batman.setId(1L);
            return null;
        }).when(heroDao).createHero(batman);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(batman.getId()).isNull();

        heroService.createHero(batman);

        softly.assertThat(batman.getId()).isNotNull();
        softly.assertAll();

        verify(heroDao).createHero(batman);
    }

    @Test
    public void updateHeroTest() {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(batman.getName()).isEqualTo("Batman");
        softly.assertThat(batman.getTroop()).isEqualTo(superheroes);
        softly.assertThat(batman.getRoles()).hasSize(1).contains(human);

        batman.setName("Vader");
        batman.setTroop(superheroes);
        batman.addRole(alien);
        heroService.updateHero(batman);

        softly.assertThat(batman.getName()).isEqualTo("Vader");
        softly.assertThat(batman.getTroop()).isEqualTo(superheroes);
        softly.assertThat(batman.getRoles()).hasSize(2).contains(human, alien);
        softly.assertAll();

        verify(heroDao).updateHero(batman);
    }

    @Test
    public void deleteHeroTest() {
        heroService.deleteHero(superman);
        verify(heroDao).removeHero(superman);
    }

    @Test
    public void changeTroopTest() {
        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(superman.getTroop()).isEqualTo(superheroes);
        heroService.changeTroop(superman, villains);
        softly.assertThat(superman.getTroop()).isEqualTo(villains);
        softly.assertAll();

        verify(heroDao).updateHero(superman);
    }

    @Test
    public void addRoleTest() {
        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(superman.getRoles()).doesNotContain(human);
        heroService.addRole(superman, human);
        softly.assertThat(superman.getRoles()).contains(human);
        softly.assertAll();

        verify(heroDao).updateHero(superman);
    }

    @Test
    public void removeRoleTest() {
        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(superman.getRoles()).contains(alien);
        heroService.removeRole(superman, alien);
        softly.assertThat(superman.getRoles()).doesNotContain(alien);
        softly.assertAll();

        verify(heroDao).updateHero(superman);
    }

    @Test
    public void changeXpTest() {
        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(superman.getXp()).isEqualTo(2);
        heroService.changeXp(superman, 1000);
        softly.assertThat(superman.getXp()).isEqualTo(1000);
        softly.assertAll();

        verify(heroDao).updateHero(superman);
    }

    @Test
    public void getHeroByIdTest() {
        when(heroDao.findHeroById(1L))
                .thenReturn(batman);

        assertThat(heroService.getHeroById(1L))
                .isEqualToComparingFieldByField(batman);
    }

    @Test
    public void getHeroByNameTest() {
        when(heroDao.findHeroByName("Batman"))
                .thenReturn(batman);

        assertThat(heroService.getHeroByName("Batman"))
                .isEqualToComparingFieldByField(batman);
    }

    @Test
    public void getHeroesByRoleTest() {
        when(heroDao.findHeroesByRole(alien)).thenAnswer(invocation -> {
            List<Hero> aliens = new ArrayList<>();
            aliens.add(superman);
            return aliens;
        });

        assertThat(heroService.getHeroesByRole(alien))
                .hasSize(1)
                .contains(superman);
    }

    @Test
    public void getHeroesByTroopTest() {
        when(heroDao.findHeroesByTroop(superheroes)).thenAnswer(invocation -> {
            List<Hero> heroes = new ArrayList<>();
            heroes.add(batman);
            heroes.add(superman);
            return heroes;
        });

        assertThat(heroService.getHeroesByTroop(superheroes))
                .hasSize(2)
                .contains(batman, superman);
    }

    @Test
    public void getHeroesByXpTest() {
        when(heroDao.findHeroesByXp(1)).thenAnswer(invocation -> {
            List<Hero> heroes = new ArrayList<>();
            heroes.add(batman);
            return heroes;
        });

        assertThat(heroService.getHeroesByXp(1))
                .hasSize(1)
                .contains(batman);
    }

    @Test
    public void getAllHeroesTest() {
        when(heroDao.findAllHeroes()).thenAnswer(invocation -> {
            List<Hero> heroes = new ArrayList<>();
            heroes.add(batman);
            heroes.add(superman);
            return heroes;
        });

        assertThat(heroService.getAllHeroes())
                .hasSize(2)
                .contains(batman, superman);
    }

    @Test
    public void winnerFightTest() {
        when(randomService.nextInt(anyInt())).thenReturn(0);
        when(roleService.computeAttackingForce(human)).thenReturn(2);
        when(roleService.computeAttackingForce(alien)).thenReturn(30);

        HeroState batmanState = new HeroState(batman);
        HeroState supermanState = new HeroState(superman);
        heroService.fight(batmanState, supermanState);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(batmanState.isAlive()).isFalse();
        softly.assertThat(supermanState.isAlive()).isTrue();
        softly.assertThat(batman.getRoles())
                .containsExactlyInAnyOrder(human);
        softly.assertThat(superman.getRoles())
                .containsExactlyInAnyOrder(human, alien);
        softly.assertAll();
    }

    @Test
    public void drawFightTest() {
        human.setCooldown(1);
        alien.setCooldown(1);

        when(randomService.nextInt(anyInt())).thenReturn(0);
        when(roleService.computeAttackingForce(human)).thenReturn(10);
        when(roleService.computeAttackingForce(alien)).thenReturn(10);

        HeroState batmanState = new HeroState(batman);
        HeroState supermanState = new HeroState(superman);
        heroService.fight(batmanState, supermanState);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(batmanState.isAlive()).isFalse();
        softly.assertThat(supermanState.isAlive()).isFalse();
        softly.assertThat(batman.getRoles())
                .containsExactlyInAnyOrder(human);
        softly.assertThat(superman.getRoles())
                .containsExactlyInAnyOrder(alien);
        softly.assertAll();
    }

    @Test
    public void fightWithoutRolesTest() {
        HeroState one = new HeroState(new Hero("One", superheroes, 100, 1));
        HeroState two = new HeroState(new Hero("Two", villains, 50, 1));
        heroService.fight(one, two);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(one.isAlive()).isTrue();
        softly.assertThat(two.isAlive()).isFalse();
        softly.assertThat(one.hero.getRoles()).isEmpty();
        softly.assertThat(two.hero.getRoles()).isEmpty();
        softly.assertAll();
    }

    @Test
    public void fightTwoDeadHeroesTest() {
        HeroState one = new HeroState(new Hero("One", superheroes, 0, 1, human));
        HeroState two = new HeroState(new Hero("Two", villains, 0, 1, alien));
        heroService.fight(one, two);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(one.isAlive()).isFalse();
        softly.assertThat(two.isAlive()).isFalse();
        softly.assertThat(one.hero.getRoles()).containsExactly(human);
        softly.assertThat(two.hero.getRoles()).containsExactly(alien);
        softly.assertAll();
    }

    @Test
    public void fightOneDeadHeroesTest() {
        HeroState one = new HeroState(new Hero("One", superheroes, 1, 1));
        HeroState two = new HeroState(new Hero("Two", villains, 0, 1, alien));
        heroService.fight(one, two);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(one.isAlive()).isTrue();
        softly.assertThat(two.isAlive()).isFalse();
        softly.assertThat(one.hero.getRoles()).isEmpty();
        softly.assertThat(two.hero.getRoles()).containsExactly(alien);
        softly.assertAll();
    }
}
