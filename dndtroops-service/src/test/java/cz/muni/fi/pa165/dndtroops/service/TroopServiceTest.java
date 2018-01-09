package cz.muni.fi.pa165.dndtroops.service;

import cz.muni.fi.pa165.dndtroops.ServiceConfiguration;
import cz.muni.fi.pa165.dndtroops.dao.TroopDao;
import cz.muni.fi.pa165.dndtroops.entities.Hero;
import cz.muni.fi.pa165.dndtroops.entities.Role;
import cz.muni.fi.pa165.dndtroops.entities.Troop;
import cz.muni.fi.pa165.dndtroops.enums.Power;
import cz.muni.fi.pa165.dndtroops.service.battle.HeroState;
import org.assertj.core.api.SoftAssertions;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * @author Miroslav Mačor and Jiří Novotný (changes to the non-trivial bussiness functionality)
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
public class TroopServiceTest extends AbstractTransactionalTestNGSpringContextTests {
    @Mock
    private TroopDao troopDao;
    @Mock
    private RandomService randomService;
    @Mock
    private RoleService roleService;
    @Mock
    private HeroService heroService;

    @InjectMocks
    private TroopServiceImpl troopService;

    private Role paladin;
    private Role mage;

    private Hero uther;
    private Hero jaina;
    private Hero dipsy;

    private Troop t1;
    private Troop t2;
    private Troop t3;

    @BeforeMethod
    public void setupMocks() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void setup() {
        t1 = new Troop("alience", "save the Azeroth", 1);
        t2 = new Troop("nameT2", "missionT2", 2);
        t3 = new Troop("nameT3", "missionT3", 3);

        paladin = new Role("Paladin", "Strong healing", Power.WEAPONS, 10, 25);
        mage = new Role("Mage", "Strong casting spells", Power.MAGIC, 35, 10);

        uther = new Hero("uther", t1, 100, 1, paladin);
        jaina = new Hero("jaina", t1, 100, 2, mage);
        dipsy = new Hero("dipsy", t2, 100, 1, paladin);
    }

    @Test
    public void createTroop() {
        doAnswer(invocation -> {
            t1.setId(1L);
            return null;
        }).when(troopDao).createTroop(t1);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(t1.getId()).isNull();

        troopService.createTroop(t1);

        softly.assertThat(t1.getId()).isNotNull();
        softly.assertAll();

        verify(troopDao).createTroop(t1);
    }

    @Test
    public void updateTroop() {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(t1.getName()).isEqualTo("alience");
        t1.setName("new Name");
        t1.setMission("save the queen");
        t1.setGoldenMoney(1000);
        troopService.updateTroop(t1);
        softly.assertThat(t1.getName()).isEqualTo("new Name");
        softly.assertThat(t1.getMission()).isEqualTo("save the queen");
        softly.assertThat(t1.getGoldenMoney()).isEqualTo(1000);
        softly.assertAll();
        verify(troopDao).updateTroop(t1);
    }

    @Test
    public void findTroopById() {
        when(troopDao.findTroopById(t1.getId()))
                .thenReturn(t1);
        assertThat(troopService.findTroopById(t1.getId()))
                .isEqualToComparingFieldByField(t1);
    }

    @Test
    public void findTroopByName() {
        when(troopDao.findTroopByName(t1.getName()))
                .thenReturn(t1);
        assertThat(troopService.findTroopByName(t1.getName()))
                .isEqualToComparingFieldByField(t1);
    }

    @Test
    public void findAllTroops() {
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
    public void deleteTroop() {
        troopService.deleteTroop(t1);
        verify(troopDao).deleteTroop(t1);;
    }

    @Test
    public void battleTwoEmptyTest() {
        Troop one = new Troop("One", "idk", 10);
        Troop two = new Troop("Two", "idk", 10);

        when(heroService.getHeroesByTroop(one)).thenReturn(Collections.emptyList());
        when(heroService.getHeroesByTroop(two)).thenReturn(Collections.emptyList());

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(troopService.battle(one, two)).isNull();
        softly.assertThat(one.getGoldenMoney()).isEqualTo(10);
        softly.assertThat(two.getGoldenMoney()).isEqualTo(10);
        softly.assertAll();
    }

    @Test
    public void battleOneEmptyTest() {
        Troop one = new Troop("One", "idk", 10);
        Troop two = new Troop("Two", "idk", 10);

        Hero two_hero = new Hero("Two Hero", two, 1, 1);

        when(heroService.getHeroesByTroop(one)).thenReturn(Collections.emptyList());
        when(heroService.getHeroesByTroop(two)).thenReturn(Collections.singletonList(two_hero));

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(troopService.battle(one, two)).isSameAs(two);
        softly.assertThat(one.getGoldenMoney()).isEqualTo(5);
        softly.assertThat(two.getGoldenMoney()).isEqualTo(15);
        softly.assertAll();
    }

    @Test
    public void battleWinnerTest() {
        Troop one = new Troop("One", "idk", 10);
        Troop two = new Troop("Two", "idk", 10);

        Hero one_hero = new Hero("Two Hero", one, 10, 1);
        Hero two_hero = new Hero("Two Hero", two, 1, 1);

        when(heroService.getHeroesByTroop(one)).thenReturn(Collections.singletonList(one_hero));
        when(heroService.getHeroesByTroop(two)).thenReturn(Collections.singletonList(two_hero));

        doAnswer(invocation -> {
            HeroState a = invocation.getArgumentAt(0, HeroState.class);
            HeroState b = invocation.getArgumentAt(1, HeroState.class);

            a.wound(3);
            b.wound(3);

            return null;
        }).when(heroService).fight(any(), any());

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(troopService.battle(one, two)).isSameAs(one);
        softly.assertThat(one.getGoldenMoney()).isEqualTo(15);
        softly.assertThat(two.getGoldenMoney()).isEqualTo(5);
        softly.assertAll();

        verify(heroService).fight(any(), any());
    }

    @Test
    public void battleDrawTest() {
        Troop one = new Troop("One", "idk", 10);
        Troop two = new Troop("Two", "idk", 10);

        Hero one_hero = new Hero("Two Hero", one, 10, 1);
        Hero two_hero = new Hero("Two Hero", two, 10, 1);

        when(heroService.getHeroesByTroop(one)).thenReturn(Collections.singletonList(one_hero));
        when(heroService.getHeroesByTroop(two)).thenReturn(Collections.singletonList(two_hero));

        doAnswer(invocation -> {
            HeroState a = invocation.getArgumentAt(0, HeroState.class);
            HeroState b = invocation.getArgumentAt(1, HeroState.class);

            a.wound(5);
            b.wound(5);

            return null;
        }).when(heroService).fight(any(), any());

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(troopService.battle(one, two)).isNull();
        softly.assertThat(one.getGoldenMoney()).isEqualTo(10);
        softly.assertThat(two.getGoldenMoney()).isEqualTo(10);
        softly.assertAll();

        verify(heroService, times(2)).fight(any(), any());
    }
}
