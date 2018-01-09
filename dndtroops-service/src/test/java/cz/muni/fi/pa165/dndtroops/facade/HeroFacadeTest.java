package cz.muni.fi.pa165.dndtroops.facade;

import cz.muni.fi.pa165.dndtroops.ServiceConfiguration;
import cz.muni.fi.pa165.dndtroops.dto.*;
import cz.muni.fi.pa165.dndtroops.enums.Power;
import org.assertj.core.api.SoftAssertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Jiří Novotný
 */

@DirtiesContext
@ContextConfiguration(classes = ServiceConfiguration.class)
public class HeroFacadeTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private TroopFacade troopFacade;

    @Autowired
    private RoleFacade roleFacade;

    @Autowired
    private HeroFacade heroFacade;

    private TroopDTO villains;
    private TroopDTO superheroes;

    private RoleDTO human;
    private RoleDTO alien;

    private HeroDTO joker;
    private HeroDTO batman;
    private HeroDTO superman;

    @BeforeMethod
    public void setup() {
        TroopCreateDTO create_villains = new TroopCreateDTO("Villains", "Have fun", 1000);
        TroopCreateDTO create_superheroes = new TroopCreateDTO("Superheroes", "Fight", 0);

        villains = troopFacade.createTroop(create_villains);
        superheroes = troopFacade.createTroop(create_superheroes);

        CreateRoleDTO create_human = new CreateRoleDTO("Human", "Just a puny human...", Power.WEAPONS, 15, 10);
        CreateRoleDTO create_alien = new CreateRoleDTO("Alien", "Badass alien", Power.MAGIC, 20, 1);

        human = roleFacade.createRole(create_human);
        alien = roleFacade.createRole(create_alien);

        HeroCreateDTO create_joker = new HeroCreateDTO();
        create_joker.setName("Joker");
        create_joker.setTroop(villains);
        create_joker.addRole(human);
        create_joker.setXp(1);

        HeroCreateDTO create_batman = new HeroCreateDTO();
        create_batman.setName("Batman");
        create_batman.setTroop(superheroes);
        create_batman.addRole(human);
        create_batman.setXp(1);

        HeroCreateDTO create_superman = new HeroCreateDTO();
        create_superman.setName("Superman");
        create_superman.setTroop(superheroes);
        create_superman.addRole(alien);
        create_superman.setXp(2);

        joker = heroFacade.createHero(create_joker);
        batman = heroFacade.createHero(create_batman);
        superman = heroFacade.createHero(create_superman);
    }

    @AfterMethod
    public void teardown() {
        heroFacade.findAllHeroes().forEach(hero -> {
            heroFacade.removeHero(hero);
        });

        roleFacade.findAllRoles().forEach(role -> {
            roleFacade.removeRole(role.getId());
        });

        troopFacade.findAllTroops().forEach(troop -> {
            troopFacade.removeTroop(troop.getId());
        });
    }

    @Test
    public void createHeroTest() {
        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(joker.getId()).isNotNull();
        softly.assertThat(batman.getId()).isNotNull();
        softly.assertThat(superman.getId()).isNotNull();

        softly.assertThat(heroFacade.findHeroByName("Joker").getRoles())
                .hasSize(1)
                .containsExactlyInAnyOrder(human);
        softly.assertThat(heroFacade.findHeroByName("Batman").getRoles())
                .hasSize(1)
                .containsExactlyInAnyOrder(human);
        softly.assertThat(heroFacade.findHeroByName("Superman").getRoles())
                .hasSize(1)
                .containsExactlyInAnyOrder(alien);

        softly.assertThat(heroFacade.findHeroByName("Joker").getTroop())
                .isNotNull();
        softly.assertThat(heroFacade.findHeroByName("Batman").getTroop())
                .isNotNull();
        softly.assertThat(heroFacade.findHeroByName("Superman").getTroop())
                .isNotNull();

        softly.assertAll();
    }

    @Test
    public void updateHeroTest() {
        batman.setTroop(villains);
        heroFacade.updateHero(batman);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(heroFacade.findHeroByName("Batman"))
                .isNotNull()
                .hasFieldOrPropertyWithValue("troop", villains);
        softly.assertThat(heroFacade.findHeroesByTroop(villains))
                .contains(batman);
        softly.assertThat(heroFacade.findHeroesByTroop(superheroes))
                .doesNotContain(batman);
        softly.assertAll();
    }

    @Test
    public void removeHeroTest() {
        heroFacade.removeHero(batman);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(heroFacade.findAllHeroes())
                .hasSize(2)
                .contains(joker, superman);
        softly.assertThat(heroFacade.findHeroesByTroop(superheroes))
                .doesNotContain(batman);
        softly.assertAll();
    }

    @Test
    public void changeTroopTest() {
        heroFacade.changeTroop(batman, villains);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(heroFacade.findHeroByName("Batman"))
                .isNotNull()
                .hasFieldOrPropertyWithValue("troop", villains);
        softly.assertAll();
    }

    @Test
    public void addRoleTest() {
        heroFacade.addRole(batman, alien);

        assertThat(heroFacade.findHeroByName("Batman").getRoles())
                .hasSize(2)
                .contains(human, alien);
    }

    @Test
    public void removeRoleTest() {
        heroFacade.removeRole(batman, human);

        assertThat(heroFacade.findHeroByName("Batman").getRoles())
                .isEmpty();
    }

    @Test
    public void changeXpTest() {
        heroFacade.changeXp(batman, 3);

        assertThat(heroFacade.findHeroByName("Batman"))
                .isNotNull()
                .hasFieldOrPropertyWithValue("xp", 3);
    }

    @Test
    public void findHeroByIdTest() {
        assertThat(heroFacade.findHeroById(batman.getId()))
                .isNotNull()
                .hasFieldOrPropertyWithValue("id", batman.getId());
    }

    @Test
    public void findHeroByNameTest() {
        assertThat(heroFacade.findHeroByName("Batman"))
                .isNotNull()
                .hasFieldOrPropertyWithValue("name", "Batman");
    }

    @Test
    public void findHeroesByRoleTest() {
        assertThat(heroFacade.findHeroesByRole(human))
                .containsExactlyInAnyOrder(joker, batman);
    }

    @Test
    public void findHeroesByTroopTest() {
        assertThat(heroFacade.findHeroesByTroop(superheroes))
                .containsExactlyInAnyOrder(batman, superman);
    }

    @Test
    public void findHeroesByXpTest() {
        assertThat(heroFacade.findHeroesByXp(1))
                .containsExactlyInAnyOrder(joker, batman);
    }

    @Test
    public void findAllHeroesTest() {
        assertThat(heroFacade.findAllHeroes())
                .containsExactlyInAnyOrder(joker, batman, superman);
    }

}
