/**
 * Created by Miroslav Macor
 */
package cz.muni.fi.pa165.dndtroops.dao;

import cz.muni.fi.pa165.dndtroops.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.dndtroops.entities.Administrator;
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
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class AdministratorDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    public EntityManager em;

    @Autowired
    public AdministratorDao administratorDao;

    private Administrator admin1;
    private Administrator admin2;
    private Administrator admin3;

    private long nonExistentHeroID = 99;

    @BeforeMethod
    public void createAdmins() {
        admin1 = new Administrator("Karel");
        admin2 = new Administrator("Jan");
        admin3 = new Administrator("Peter");

        administratorDao.createAdministrator(admin1);
        administratorDao.createAdministrator(admin2);
        administratorDao.createAdministrator(admin3);
    }


    @Test
    public void findByIdReturnsUser(){
        Long testId = admin2.getId();
        Assert.assertEquals(administratorDao.findAdministatorById(testId), admin2);
    }

    @Test
    public void findByIdReturnsNull(){
        Assert.assertEquals(administratorDao.findAdministatorById(nonExistentHeroID), null);
    }

    @Test
    public void findAllByIdShouldReturn3(){
        List<Administrator> allAdmins = administratorDao.findAllAdministrators();
        Assert.assertEquals(allAdmins.size(), 3);
    }

    @Test
    public void removeAdminShouldRemoveAdmin(){
        administratorDao.removeAdministrator(admin1);
        List<Administrator> allAdmins = administratorDao.findAllAdministrators();
        Assert.assertEquals(allAdmins.size(), 2);
    }

    @Test
    public void updateAdminShouldChangeAttributes(){
        Administrator admin4 = new Administrator("Fero");
        administratorDao.createAdministrator(admin4);
        String newName = "Tibor";
        admin4.setName(newName);
        administratorDao.updateAdministrator(admin4);
        Assert.assertEquals(administratorDao.findAdministatorById(admin4.getId()).getName(), newName);
    }

    @Test
    public void findByNameReturnsUser(){
        String testName = admin2.getName();
        Assert.assertEquals(administratorDao.findAdministratorByName(testName), admin2);
    }

}
