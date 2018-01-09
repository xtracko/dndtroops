package cz.muni.fi.pa165.dndtroops.dao;

import cz.muni.fi.pa165.dndtroops.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.dndtroops.entities.Hero;
import cz.muni.fi.pa165.dndtroops.entities.Role;
import cz.muni.fi.pa165.dndtroops.entities.Troop;
import cz.muni.fi.pa165.dndtroops.enums.Power;
import org.assertj.core.api.SoftAssertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;

/*
* @author Vojtech Duchoň and Jiří Novotný (tests rewritten)
*/

@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners (TransactionalTestExecutionListener.class)
@Transactional
public class HeroDaoTest extends AbstractTestNGSpringContextTests {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private HeroDao heroDao;

    @Autowired
    private TroopDao troopDao;

    private Hero hero1;
    private Hero hero2;
    private Hero hero3;

    private Role role1;
    private Role role2;
    private Role role3;

    private Troop troop1;
    private Troop troop2;
    private Troop troop3;

    @BeforeMethod
    public void initHeroes() {
        role1 = new Role("Knight", "Very good fighter with weapons, from a noble family",Power.WEAPONS, 80, 1);
        role2 = new Role("Druid", "Healer good at casting spells, healing and making potions", Power.MAGIC, 80, 1);
        role3 = new Role("Ninja", "Skilled rogue-ish warrior, trained by monks", Power.MARTIAL_ARTS, 80, 1);

        troop1 = new Troop("nameT1", "missionT1", 1);
        troop2 = new Troop("nameT2", "missionT2", 2);
        troop3 = new Troop("nameT3", "missionT3", 3);

        hero1 = new Hero("Masakrator", troop1, 100, 0, role1, role2);
        hero2 = new Hero("Mr. Smoketoomuch", troop3, 100, 0, role2);
        hero3 = new Hero("JustAnotherHero", troop3, 10, 1, role2);

        roleDao.createRole(role1);
        roleDao.createRole(role2);
        roleDao.createRole(role3);

        troopDao.createTroop(troop1);
        troopDao.createTroop(troop2);
        troopDao.createTroop(troop3);

        heroDao.createHero(hero1);
        heroDao.createHero(hero2);
        heroDao.createHero(hero3);
    }

    @Test
    public void findById() {
        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(heroDao.findHeroById(hero1.getId()))
                .isNotNull()
                .isSameAs(hero1);
        softly.assertThat(heroDao.findHeroById(15897L))
                .isNull();

        softly.assertAll();
    }

    @Test
    public void findByName() {
        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(heroDao.findHeroByName(hero1.getName()))
                .isNotNull()
                .isSameAs(hero1);
        softly.assertThat(heroDao.findHeroByName(hero2.getName()))
                .isNotNull()
                .isSameAs(hero2);
        softly.assertThat(heroDao.findHeroByName("NonExistingName"))
                .isNull();
        softly.assertAll();
    }

    @Test
    public void findByRole() {
       SoftAssertions softly = new SoftAssertions();

       softly.assertThat(heroDao.findHeroesByRole(role1))
               .containsExactlyInAnyOrder(hero1);
       softly.assertThat(heroDao.findHeroesByRole(role2))
               .containsExactlyInAnyOrder(hero1, hero2, hero3);
       softly.assertThat(heroDao.findHeroesByRole(role3))
               .isEmpty();
       softly.assertAll();
    }

    @Test
    public void findByTroop() {
        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(heroDao.findHeroesByTroop(troop1))
                .containsExactlyInAnyOrder(hero1);
        softly.assertThat(heroDao.findHeroesByTroop(troop2))
                .isEmpty();
        softly.assertThat(heroDao.findHeroesByTroop(troop3))
                .containsExactlyInAnyOrder(hero2, hero3);
        softly.assertAll();
    }

    @Test
    public void findByXp() {
        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(heroDao.findHeroesByXp(0))
                .containsExactlyInAnyOrder(hero1, hero2);
        softly.assertThat(heroDao.findHeroesByXp(1))
                .containsExactlyInAnyOrder(hero3);
        softly.assertAll();
    }

    @Test
    public void listAllHeroes() {
        assertThat(heroDao.findAllHeroes())
                .hasSize(3);
    }
}
