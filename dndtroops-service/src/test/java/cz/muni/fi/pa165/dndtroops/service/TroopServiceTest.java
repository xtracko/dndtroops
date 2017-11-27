package cz.muni.fi.pa165.dndtroops.service;


import cz.muni.fi.pa165.dndtroops.ServiceConfiguration;
import cz.muni.fi.pa165.dndtroops.entities.Hero;
import cz.muni.fi.pa165.dndtroops.entities.Role;
import cz.muni.fi.pa165.dndtroops.entities.Troop;
import cz.muni.fi.pa165.dndtroops.enums.Power;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

@ContextConfiguration(classes = ServiceConfiguration.class)
public class TroopServiceTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    @InjectMocks
    private TroopService troopService;

    private Troop t1;
    private Troop t2;
    private Troop t3;

    @Autowired
    @InjectMocks
    private HeroService heroService;
    private Hero h1;
    private Hero h2;
    private Hero h3;

    @Autowired
    @InjectMocks
    private RoleService roleService;
    private Role r1;
    private Role r2;
    private Role r3;

    @BeforeMethod
    public void setup() {

        t1 = new Troop("nameT1", "missionT1", 1);
        t2 = new Troop("nameT2", "missionT2", 2);
        t3 = new Troop("nameT3", "missionT3", 3);

        troopService.createTroop(t1);
        troopService.createTroop(t2);
        troopService.createTroop(t3);

        r1 = new Role("Knight", "Very good fighter with weapons, from a noble family", Power.WEAPONS,80,1);
        r2 = new Role("Druid", "Healer good at casting spells, healing and making potions", Power.MAGIC,80,1);
        r3 = new Role("Ninja", "Skilled rogue-ish warrior, trained by monks", Power.MARTIAL_ARTS,80,1);

        roleService.createRole(r1);
        roleService.createRole(r2);
        roleService.createRole(r3);

        h1 = new Hero("Masakrator", t1, r1,1500 );
        h2 = new Hero("Mr. Smoketoomuch", t2, r2, 0 );
        h3 = new Hero("JustAnotherHero", t3, r3, 100000);

        heroService.createHero(h1);
        heroService.createHero(h2);
        heroService.createHero(h3);
    }

    @Test
    public void findAllTroopsTest() {

        List<Troop> troopsFound = troopService.findAllTroops();
        Assert.assertEquals(troopsFound.size(), 3);

    }

    @Test
    public void computeTroopStrengthTest(){

    }

    @Test
    public void troopBattleTest(){

    }


}
