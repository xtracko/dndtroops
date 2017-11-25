package cz.muni.fi.pa165.dndtroops.service;


import cz.muni.fi.pa165.dndtroops.ServiceConfiguration;
import cz.muni.fi.pa165.dndtroops.dao.HeroDao;
import cz.muni.fi.pa165.dndtroops.dao.RoleDao;
import cz.muni.fi.pa165.dndtroops.dao.TroopDao;
import cz.muni.fi.pa165.dndtroops.entities.Hero;
import cz.muni.fi.pa165.dndtroops.entities.Role;
import cz.muni.fi.pa165.dndtroops.entities.Troop;
import cz.muni.fi.pa165.dndtroops.enums.Power;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.service.spi.ServiceException;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import static org.mockito.Mockito.when;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;

/**
 *
 * @author Martin Sestak
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class HeroServiceImplTest extends AbstractTransactionalTestNGSpringContextTests {
    @Mock
    private HeroDao heroDao;
    @Mock
    private RoleDao roleDao;
    @Mock
    private TroopDao troopDao;
    
    @Autowired  
    @InjectMocks
    private HeroService heroService;


    private Hero hero1;

    private Role role1;

    private Troop troop1;

    @BeforeClass
    public void setup() throws ServiceException
    {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void prepareHeroes() {
        role1 = new Role("Knight", "Very good fighter with weapons, from a noble family",Power.WEAPONS);
        

        troop1 = new Troop("nameT1", "missionT1", 1);

        hero1 = new Hero();
        hero1.setName("Saint Knight");
        hero1.setTroop(troop1);
        hero1.addRole(role1);
        hero1.setXp(500);
    }
    
    @Test
    public void testCreate() {
        heroService.createHero(hero1);
        Long id = hero1.getId();
        
        when(heroDao.findHeroById(id)).thenReturn(hero1);
        
        Hero heroC = heroService.getHeroById(id);
        Assert.assertNotNull(id);
        Assert.assertNotNull(hero1);
        Assert.assertNotNull(heroC);
        Assert.assertEquals(hero1, heroC);
    }

   
    
}
