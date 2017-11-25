/**
 * @author Jiří Novotný
 */
package cz.muni.fi.pa165.dndtroops.dao;

import cz.muni.fi.pa165.dndtroops.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.dndtroops.entities.Troop;
import org.assertj.core.api.SoftAssertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class TroopDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private TroopDao troopDao;

    private Troop t1;
    private Troop t2;

    @BeforeMethod
    public void setup() {
        t1 = new Troop("nameT1", "missionT1", 1);
        t2 = new Troop("nameT2", "missionT2", 2);

        troopDao.create(t1);
        troopDao.create(t2);
    }

    @Test
    public void doesNotSaveNullName() {
        assertThatThrownBy(() -> troopDao.create(new Troop()))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    public void storesCorrectly() {
        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(troopDao.findById(t1.getId()))
                .isNotNull()
                .hasFieldOrPropertyWithValue("name", "nameT1")
                .hasFieldOrPropertyWithValue("mission", "missionT1")
                .hasFieldOrPropertyWithValue("goldenMoney", 1L);

        softly.assertThat(troopDao.findById(t2.getId()))
                .isNotNull()
                .hasFieldOrPropertyWithValue("name", "nameT2")
                .hasFieldOrPropertyWithValue("mission", "missionT2")
                .hasFieldOrPropertyWithValue("goldenMoney", 2L);

        softly.assertAll();
    }

    @Test
    void doesNotStoreDuplicities() {
        Troop duplicity = new Troop(t1.getName(), t1.getMission(), t1.getGoldenMoney());

        assertThatThrownBy(() ->troopDao.create(duplicity));
    }

    @Test
    public void removesCorrectly() {
        troopDao.delete(t1);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(troopDao.findById(t1.getId()))
                .isNull();
        softly.assertThat(troopDao.findById(t2.getId()))
                .isNotNull();
        softly.assertAll();
    }

    @Test
    public void findById() {
        Long existingId = t1.getId();
        Long nonExistingId = createNonExistingId();

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(troopDao.findById(existingId))
                .isNotNull();
        softly.assertThat(troopDao.findById(nonExistingId))
                .isNull();
        softly.assertAll();
    }

    @Test
    public void findByName() {
        assertThat(troopDao.findByName(t1.getName()))
                .isNotNull()
                .isSameAs(t1);
    }

    @Test
    public void findAllTroops() {
        List<Troop> result = troopDao.findAll();

        assertThat(result.size())
                .isEqualTo(2);
    }

    private Long createNonExistingId() {
        Long id = t1.getId() + 1;
        if (Objects.equals(id, t2.getId())) {
            id = id + 1;
        }
        return id;
    }

}
