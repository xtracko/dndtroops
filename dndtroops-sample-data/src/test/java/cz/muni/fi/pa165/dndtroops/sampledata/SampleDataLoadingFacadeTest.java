package cz.muni.fi.pa165.dndtroops.sampledata;

import cz.muni.fi.pa165.dndtroops.dao.HeroDao;
import cz.muni.fi.pa165.dndtroops.dao.RoleDao;
import cz.muni.fi.pa165.dndtroops.dao.TroopDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;

/**
 * @author Jiří Novotný
 */

@ContextConfiguration(classes = {DndTroopsWithSampleDataConfiguration.class})
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class SampleDataLoadingFacadeTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private RoleDao roleDao;

    @Autowired
    private TroopDao troopDao;
    
    @Autowired
    private HeroDao heroDao;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private SampleDataLoadingFacade sampleDataLoadingFacade;

    @Test
    public void createSampleData() throws IOException {
        Assert.assertFalse(roleDao.findAllRoles().isEmpty(), "No roles loaded");
        Assert.assertFalse(troopDao.findAllTroops().isEmpty(), "No troops loaded");
        Assert.assertFalse(heroDao.findAllHeroes().isEmpty(), "No heroes loaded");
    }
}
