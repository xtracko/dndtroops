package cz.muni.fi.pa165.dndtroops.dao;

import cz.muni.fi.pa165.dndtroops.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.dndtroops.entities.Hero;
import cz.muni.fi.pa165.dndtroops.entities.Role;
import cz.muni.fi.pa165.dndtroops.entities.Troop;
import cz.muni.fi.pa165.dndtroops.enums.Power;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


/*
* @author Vojtech Duchoň
 */


@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners (TransactionalTestExecutionListener.class)
@Transactional
public class HeroDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    public EntityManager em;

    @Autowired
    public HeroDao heroDao;

    @Autowired
    public TroopDao troopDao;

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

        hero1 = new Hero("Masakrator", troop1, role1,1500 );
        hero2 = new Hero("Mr. Smoketoomuch", troop3, role2, 0 );
        hero3 = new Hero("JustAnotherHero", troop3, role2, 100000);

        troopDao.createTroop(troop1);
        troopDao.createTroop(troop2);
        troopDao.createTroop(troop3);

        heroDao.createHero(hero1);
        heroDao.createHero(hero2);
        heroDao.createHero(hero3);
    }

    @Test
    public void findById() {
        Long testHeroId = hero1.getId();
        Assert.assertEquals(heroDao.findHeroById(testHeroId), hero1, "Hero hero1 was not found by ID!");

        Long testWrongHeroId = 15897L;
        Assert.assertNull(heroDao.findHeroById(testWrongHeroId), "No such hero is persisted!");
    }

    @Test
    public void findByName(){
        String testHeroName = hero1.getName();
        Assert.assertEquals(heroDao.findHeroByName(testHeroName), hero1, "Hero hero1 was not found by name!");

        String testHeroName2 = "JustAnotherHero";
        Assert.assertEquals(heroDao.findHeroByName(testHeroName2), hero3, "JustAnotherHero was not found!");

        String testHeroWrongName = "Idontexist";
        Assert.assertNull(heroDao.findHeroByName(testHeroWrongName), "No such hero is persisted!");
    }

   @Test
    public void findByRole(){
//        Role testHeroRole = role1;
//        Assert.assertEquals(heroDao.findHeroesByRole(testHeroRole).get(0).getId(), hero1.getId()  );
//
//        Role testHeroRole2 = role2;
//        Assert.assertEquals(heroDao.findHeroesByRole(testHeroRole2).get(0).getId(), hero2.getId()  );
//        Assert.assertEquals(heroDao.findHeroesByRole(testHeroRole2).get(1).getId(), hero3.getId()  );
//
//        Role testHeroWrongRole = role3;
//        Assert.assertEquals(heroDao.findHeroesByRole(testHeroWrongRole).isEmpty(), true ,   "Hero with role3 was found!");
    }

    @Test
    public void findByTroop(){
        Troop testHeroTroop = troop1;
        Assert.assertEquals(heroDao.findHeroesByTroop(testHeroTroop).get(0).getId(), hero1.getId()  );

        Troop testHeroTroop3 = troop3;
        Assert.assertEquals(heroDao.findHeroesByTroop(testHeroTroop3).get(0).getId(), hero2.getId()  );
        Assert.assertEquals(heroDao.findHeroesByTroop(testHeroTroop3).get(1).getId(), hero3.getId()  );

        Troop testHeroWrongTroop = troop2;
        Assert.assertEquals(heroDao.findHeroesByTroop(testHeroWrongTroop).isEmpty(), true ,  "Hero with troop2 assigned was found!");
    }

    @Test
    public void findByXp(){
        Integer testHeroXp = hero1.getXp();
        Assert.assertEquals(heroDao.findHeroesByXp(testHeroXp).get(0), hero1, "Hero hero1 was not found by XP!");

        Integer testHeroXp2 = 0;
        Assert.assertEquals(heroDao.findHeroesByXp(testHeroXp2).get(0), hero2, "Hero Mr. Smoketoomuch is a nwebie!");

        Integer testHeroWrongXp = 518475;
        Assert.assertEquals(heroDao.findHeroesByXp(testHeroWrongXp).isEmpty(), true,  "Hero with 518475XP was found!");
    }

    @Test
    public void listAllHeroes(){
        List<Hero> heroesFound = heroDao.findAllHeroes();
        Assert.assertEquals(heroesFound.size(), 3);
    }



}
