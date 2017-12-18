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
        heroFacade.getAllHeroes().forEach(hero -> {
            heroFacade.deleteHero(hero);
        });

        roleFacade.getAllRoles().forEach(role -> {
            roleFacade.deleteRole(role.getId());
        });

        troopFacade.findAllTroops().forEach(troop -> {
            troopFacade.deleteTroop(troop.getId());
        });
    }

    @Test
    public void createHeroTest() {
        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(joker.getId()).isNotNull();
        softly.assertThat(batman.getId()).isNotNull();
        softly.assertThat(superman.getId()).isNotNull();

        softly.assertThat(heroFacade.getHeroByName("Joker").getRoles())
                .hasSize(1);
                //.containsExactlyInAnyOrder(human);
        softly.assertThat(heroFacade.getHeroByName("Batman").getRoles())
                .hasSize(1);
                //.containsExactlyInAnyOrder(human);
        softly.assertThat(heroFacade.getHeroByName("Superman").getRoles())
                .hasSize(1);
                //.containsExactlyInAnyOrder(alien);

        softly.assertThat(heroFacade.getHeroByName("Joker").getTroop())
                .isNotNull();
        softly.assertThat(heroFacade.getHeroByName("Batman").getTroop())
                .isNotNull();
        softly.assertThat(heroFacade.getHeroByName("Superman").getTroop())
                .isNotNull();

        softly.assertAll();
    }

    @Test
    public void updateHeroTest() {
        batman.setTroop(villains);
        heroFacade.updateHero(batman);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(heroFacade.getHeroByName("Batman"))
                .isNotNull()
                .hasFieldOrPropertyWithValue("troop", villains);
        softly.assertThat(troopFacade.findHeroesOfTroop(villains))
                .contains(batman);
        softly.assertThat(troopFacade.findHeroesOfTroop(superheroes))
                .doesNotContain(batman);
        softly.assertAll();
    }

    @Test
    public void deleteHeroTest() {
        heroFacade.deleteHero(batman);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(heroFacade.getAllHeroes())
                .hasSize(2)
                .contains(joker, superman);
        softly.assertThat(troopFacade.findHeroesOfTroop(superheroes))
                .doesNotContain(batman);
        softly.assertAll();
    }

    @Test
    public void changeTroopTest() {
        heroFacade.changeTroop(batman, villains);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(heroFacade.getHeroByName("Batman"))
                .isNotNull()
                .hasFieldOrPropertyWithValue("troop", villains);
        softly.assertAll();
    }

    @Test
    public void addRoleTest() {
        heroFacade.addRole(batman, alien);

        assertThat(heroFacade.getHeroByName("Batman").getRoles())
                .hasSize(2)
                .contains(human, alien);
    }

    @Test
    public void removeRoleTest() {
        heroFacade.removeRole(batman, human);

        assertThat(heroFacade.getHeroByName("Batman").getRoles())
                .isEmpty();
    }

    @Test
    public void changeXpTest() {
        heroFacade.changeXp(batman, 3);

        assertThat(heroFacade.getHeroByName("Batman"))
                .isNotNull()
                .hasFieldOrPropertyWithValue("xp", 3);
    }

    @Test
    public void getHeroByIdTest() {
        assertThat(heroFacade.getHeroById(batman.getId()))
                .isNotNull()
                .hasFieldOrPropertyWithValue("id", batman.getId());
    }

    @Test
    public void getHeroByNameTest() {
        assertThat(heroFacade.getHeroByName("Batman"))
                .isNotNull()
                .hasFieldOrPropertyWithValue("name", "Batman");
    }

    @Test
    public void getHeroesByRoleTest() {
        assertThat(heroFacade.getHeroesByRole(human))
                .containsExactlyInAnyOrder(joker, batman);
    }

    @Test
    public void getHeroesByTroopTest() {
        assertThat(heroFacade.getHeroesByTroop(superheroes))
                .containsExactlyInAnyOrder(batman, superman);
    }

    @Test
    public void getHeroesByXpTest() {
        assertThat(heroFacade.getHeroesByXp(1))
                .containsExactlyInAnyOrder(joker, batman);
    }

    @Test
    public void getAllHeroesTest() {
        assertThat(heroFacade.getAllHeroes())
                .containsExactlyInAnyOrder(joker, batman, superman);
    }

}
