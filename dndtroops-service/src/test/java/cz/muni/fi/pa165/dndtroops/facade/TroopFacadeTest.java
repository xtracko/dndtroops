package cz.muni.fi.pa165.dndtroops.facade;

import cz.muni.fi.pa165.dndtroops.ServiceConfiguration;
import cz.muni.fi.pa165.dndtroops.dto.HeroCreateDTO;
import cz.muni.fi.pa165.dndtroops.dto.HeroDTO;
import cz.muni.fi.pa165.dndtroops.dto.TroopCreateDTO;
import cz.muni.fi.pa165.dndtroops.dto.TroopDTO;
import org.assertj.core.api.SoftAssertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Miroslav Mačór and Jiří Novotný (changes to the non-trivial bussiness functionality)
 */
@DirtiesContext
@ContextConfiguration(classes = ServiceConfiguration.class)
public class TroopFacadeTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private TroopFacade troopFacade;

    @Autowired
    private HeroFacade heroFacade;

    private TroopDTO troopsA;
    private TroopDTO troopsB;

    private HeroDTO guldan;


    @BeforeMethod
    public void setUp() throws Exception {
        TroopCreateDTO createTroopA = new TroopCreateDTO("Heroes", "Save the queen", 100);
        TroopCreateDTO createTroopB = new TroopCreateDTO("Peasants", "Save the king", 100);
        troopsA = troopFacade.createTroop(createTroopA);
        troopsB = troopFacade.createTroop(createTroopB);

        HeroCreateDTO heroCreateDTO = new HeroCreateDTO();
        heroCreateDTO.setName("Guldan");
        heroCreateDTO.setTroop(troopsA);
        heroCreateDTO.setXp(489);
        guldan = heroFacade.createHero(heroCreateDTO);

    }

    @AfterMethod
    public void tearDown() throws Exception {
        heroFacade.getAllHeroes().forEach(hero -> {
            heroFacade.deleteHero(hero);
        });

        troopFacade.findAllTroops().forEach(troop -> {
            troopFacade.deleteTroop(troop.getId());
        });
    }


    @Test
    public void testCreateTroop() throws Exception {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(troopsA.getId()).isNotNull();
        softly.assertAll();
    }

    @Test
    public void testDeleteTroop() throws Exception {
        troopFacade.deleteTroop(troopsB.getId());

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(troopFacade.findAllTroops())
                .hasSize(1)
                .doesNotContain(troopsB)
                .contains(troopsA);
        softly.assertAll();
    }

    @Test
    public void testUpdateTroop() throws Exception {
        troopsA.setMission("Save the prince");
        troopFacade.updateTroop(troopsA);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(troopFacade.findTroopByName(troopsA.getName()))
                .isNotNull()
                .hasFieldOrPropertyWithValue("mission", "Save the prince");
        softly.assertAll();
    }

    @Test
    public void testFindTroopById() throws Exception {
        assertThat(troopFacade.findTroopByName(troopsA.getName()))
                .isNotNull()
                .hasFieldOrPropertyWithValue("id", troopsA.getId());
    }

    @Test
    public void testFindTroopByName() throws Exception {
        assertThat(troopFacade.findTroopById(troopsA.getId()))
                .isNotNull()
                .hasFieldOrPropertyWithValue("name", troopsA.getName());
    }

    @Test
    public void testFindAllTroops() throws Exception {
        assertThat(troopFacade.findAllTroops())
                .containsExactlyInAnyOrder(troopsA, troopsB);
    }

    @Test
    public void battleTest() {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(troopFacade.battle(troopsA, troopsB))
                .hasFieldOrPropertyWithValue("id", troopsA.getId());

        softly.assertThat(troopFacade.findTroopById(troopsA.getId()))
                .hasFieldOrPropertyWithValue("goldenMoney", 150L);

        softly.assertThat(troopFacade.findTroopById(troopsB.getId()))
                .hasFieldOrPropertyWithValue("goldenMoney", 50L);

        softly.assertAll();
    }
}