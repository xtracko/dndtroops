package cz.muni.fi.pa165.dndtroops.service;


import cz.muni.fi.pa165.dndtroops.ServiceConfiguration;
import cz.muni.fi.pa165.dndtroops.dao.TroopDao;
import cz.muni.fi.pa165.dndtroops.entities.Hero;
import cz.muni.fi.pa165.dndtroops.entities.Role;
import cz.muni.fi.pa165.dndtroops.entities.Troop;
import cz.muni.fi.pa165.dndtroops.enums.Power;
import org.assertj.core.api.SoftAssertions;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = ServiceConfiguration.class)
public class TroopServiceTest extends AbstractTransactionalTestNGSpringContextTests {

    @Mock
    private TroopDao troopDao;

    private Role paladin;
    private Role mage;

    private Hero uther;
    private Hero jaina;

    @Autowired
    @InjectMocks
    private TroopServiceImpl troopService;

    private Troop t1;
    private Troop t2;
    private Troop t3;

    @BeforeClass
    public void setupMocks() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void setup() {

        t1 = new Troop("alience", "save the Azeroth", 1);
        t2 = new Troop("nameT2", "missionT2", 2);
        t3 = new Troop("nameT3", "missionT3", 3);

        troopService.createTroop(t1);
        troopService.createTroop(t2);
        troopService.createTroop(t3);

        paladin = new Role("Paladin", "Strong healing", Power.WEAPONS, 10, 25);
        mage = new Role("Mage", "Strong casting spells", Power.MAGIC, 35, 10);

        uther = new Hero("uther", t1, paladin, 1);
        jaina = new Hero("jaina", t1, mage, 2);

        uther = new Hero("uther", t2, paladin, 1);
    }

    @Test
    public void createTroop(){
        doAnswer(invocation -> {
            t1.setId(1L);
            return null;
        }).when(troopDao).createTroop(t1);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(t1.getId()).isNull();

        troopService.createTroop(t1);

        softly.assertThat(t1.getId()).isNotNull();
        softly.assertAll();
    }

    @Test
    public void updateTroop(){
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(t1.getName()).isEqualTo("alience");
        t1.setName("new Name");
        t1.setMission("save the queen");
        t1.setGoldenMoney(1000L);
        troopService.updateTroop(t1);
        softly.assertThat(t1.getName()).isEqualTo("new Name");
        softly.assertThat(t1.getMission()).isEqualTo("save the queen");
        softly.assertThat(t1.getGoldenMoney()).isEqualTo(1000L);
        softly.assertAll();
        verify(troopDao).updateTroop(t1);
    }

    @Test
    public void findTroopById(){
        when(troopDao.findTroopById(t1.getId()))
                .thenReturn(t1);
        assertThat(troopService.findTroopById(t1.getId()))
                .isEqualToComparingFieldByField(t1);
    }

    @Test
    public void findTroopByName(){
        when(troopDao.findTroopByName(t1.getName()))
                .thenReturn(t1);
        assertThat(troopService.findTroopByName(t1.getName()))
                .isEqualToComparingFieldByField(t1);
    }

    @Test
    public void findAllTroops(){
        when(troopDao.findAllTroops()).thenAnswer(invocation -> {
            List<Troop> troops = new ArrayList<>();
            troops.add(t1);
            troops.add(t2);
            troops.add(t3);
            return troops;
        });
        assertThat(troopService.findAllTroops())
                .hasSize(3)
                .contains(t1, t2, t3);
    }


    @Test
    public void findHeroesOfTroop(){
        when(troopDao.findHeroesOfTroop(t1)).thenAnswer(invocation -> {
            List<Hero> heroes = new ArrayList<>();
            heroes.add(uther);
            heroes.add(jaina);
            return heroes;
        });
        assertThat(troopService.findHeroesOfTroop(t1))
                .hasSize(2)
                .contains(uther, jaina);
    }


    @Test
    public void computeTroopStrengthTest(){
//        when(roleService.computeAttackingForce(paladin))
//                .thenReturn(100f);
//        when(roleService.computeAttackingForce(mage))
//                .thenReturn(50f);
//        troopService.computeTroopStrength(t1);
//        softly.assertThat(troopService.computeTroopStrength(t1)).isEqualTo(150f);
//        softly.assertThat(troopService.computeTroopStrength(t2)).isEqualTo(100f);
//        softly.assertAll();
    }

    @Test
    public void troopBattleTest(){
        Troop t4 = troopService.troopBattle(t1,t2);
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(t4.getName()).isEqualTo(t1.getName());
        softly.assertThat(t4.getName()).isNotEqualTo(t2.getName());
        softly.assertAll();
    }

    @Test
    public void deleteTroop(){
        troopService.deleteTroop(t1);
        verify(troopDao).deleteTroop(t1);;
    }
}
