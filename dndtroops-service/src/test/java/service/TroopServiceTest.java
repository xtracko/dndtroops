package service;


import cz.muni.fi.pa165.dndtroops.ServiceConfiguration;
import cz.muni.fi.pa165.dndtroops.entities.Troop;
import cz.muni.fi.pa165.dndtroops.service.TroopService;
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

    @BeforeMethod
    public void setup() {
        t1 = new Troop("nameT1", "missionT1", 1);
        t2 = new Troop("nameT2", "missionT2", 2);

        troopService.createTroop(t1);
        troopService.createTroop(t2);
    }

    @Test
    public void findAllTroopsTest(){

        List<Troop> troopsFound = troopService.findAllTroops();
        Assert.assertEquals(troopsFound.size(), 2);

    }

}
