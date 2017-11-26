/**
 * @author Jiří Novotný
 */
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
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.validation.ConstraintViolationException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class TroopDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private TroopDao troopDao;

    private Troop t1;
    private Troop t2;
    private Troop t3;

    @Autowired
    private HeroDao heroDao;
    private Hero h1;
    private Hero h2;
    private Hero h3;

    @Autowired
    private RoleDao roleDao;
    private Role r1;
    private Role r2;
    private Role r3;

    @BeforeMethod
    public void setup() {
        t1 = new Troop("nameT1", "missionT1", 1);
        t2 = new Troop("nameT2", "missionT2", 2);
        t3 = new Troop("nameT3", "missionT3", 3);

        troopDao.createTroop(t1);
        troopDao.createTroop(t2);
        troopDao.createTroop(t3);


        r1 = new Role("Knight", "Very good fighter with weapons, from a noble family", Power.WEAPONS,80,1);
        r2 = new Role("Druid", "Healer good at casting spells, healing and making potions", Power.MAGIC,80,1);
        r3 = new Role("Ninja", "Skilled rogue-ish warrior, trained by monks", Power.MARTIAL_ARTS,80,1);


        roleDao.createRole(r1);
        roleDao.createRole(r2);
        roleDao.createRole(r3);

        h1 = new Hero("Masakrator", t1, r1,1500 );
        h2 = new Hero("Mr. Smoketoomuch", t2, r2, 0 );
        h3 = new Hero("JustAnotherHero", t3, r3, 100000);

        heroDao.createHero(h1);
        heroDao.createHero(h2);
        heroDao.createHero(h3);

    }

    @Test
    public void doesNotSaveNullName() {
        assertThatThrownBy(() -> troopDao.createTroop(new Troop()))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    public void storesCorrectly() {
        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(troopDao.findTroopById(t1.getId()))
                .isNotNull()
                .hasFieldOrPropertyWithValue("name", "nameT1")
                .hasFieldOrPropertyWithValue("mission", "missionT1")
                .hasFieldOrPropertyWithValue("goldenMoney", 1L);

        softly.assertThat(troopDao.findTroopById(t2.getId()))
                .isNotNull()
                .hasFieldOrPropertyWithValue("name", "nameT2")
                .hasFieldOrPropertyWithValue("mission", "missionT2")
                .hasFieldOrPropertyWithValue("goldenMoney", 2L);

        softly.assertAll();
    }

    /*@Test
    void doesNotStoreDuplicities() {
        Troop duplicity = new Troop(t1.getName(), t1.getMission(), t1.getGoldenMoney());

        assertThatThrownBy(() -> troopDao.createTroop(duplicity))
                .isInstanceOf(PersistenceException.class)
                .hasCauseExactlyInstanceOf(ConstraintViolationException.class);


    }
    }*/



    @Test
    public void removesCorrectly() {
        troopDao.deleteTroop(t1);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(troopDao.findTroopById(t1.getId()))
                .isNull();
        softly.assertThat(troopDao.findTroopById(t2.getId()))
                .isNotNull();
        softly.assertAll();
    }

    @Test
    public void findById() {
        Long existingId = t1.getId();
        Long nonExistingId = createNonExistingId();

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(troopDao.findTroopById(existingId))
                .isNotNull();
        softly.assertThat(troopDao.findTroopById(nonExistingId))
                .isNull();
        softly.assertAll();
    }

    @Test
    public void findByName() {
        assertThat(troopDao.findTroopByName(t1.getName()))
                .isNotNull()
                .isSameAs(t1);
    }

    @Test
    public void findAllTroops() {
        List<Troop> result = troopDao.findAllTroops();

        assertThat(result.size())
                .isEqualTo(3);
    }

    private Long createNonExistingId() {
        Long id = t1.getId() + 50;
        if (Objects.equals(id, t2.getId())) {
            id = id + 50;
        }
        return id;
    }

    @Test
    public void findAllHeroesOfTroop(){
        List<Hero> heroes = troopDao.findHeroesOfTroop(t1);
        Assert.assertEquals(heroes.size(),1);
        Assert.assertEquals(heroes.get(0).getTroop().getId(),t1.getId());

            }

}
