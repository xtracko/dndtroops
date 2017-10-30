package cz.muni.fi.pa165.dndtroops.dao;

import cz.muni.fi.pa165.dndtroops.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.dndtroops.entities.Hero;
import cz.muni.fi.pa165.dndtroops.entities.Role;
import cz.muni.fi.pa165.dndtroops.entities.Troop;
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
    public void initHeroes(){
        hero1 = new Hero("Masakrator", troop1, role1,1500 );
        hero2 = new Hero("Mr. Smoketoomuch", troop2, role2, null );
        hero3 = new Hero("JustAnotherHero", troop3, role3, 100000);

        heroDao.createHero(hero1);
        heroDao.createHero(hero2);
        heroDao.createHero(hero3);
    }

    @Test
    public void findById(){
        Long testHeroId = hero1.getId();
        Assert.assertEquals(heroDao.findHeroById(testHeroId), hero1, "Hero hero1 was found by ID");
    }

}
