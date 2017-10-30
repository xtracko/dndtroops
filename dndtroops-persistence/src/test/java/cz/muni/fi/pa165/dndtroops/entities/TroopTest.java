/**
 * @author Jiří Novotný
 */
package cz.muni.fi.pa165.dndtroops.dao;

import cz.muni.fi.pa165.dndtroops.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.dndtroops.entities.Troop;
import org.assertj.core.api.SoftAssertions;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
public class TroopTest {

    private Troop t1;
    private Troop t2;
    private Troop t3;

    @BeforeClass
    public void setup() {
        t1 = new Troop();
        t1.setId(1L);
        t1.setName("Same");
        t1.setGoldenMoney(1L);

        t2 = new Troop();
        t2.setId(2L);
        t2.setName("Same");
        t2.setGoldenMoney(2L);

        t3 = new Troop();
        t3.setId(3L);
        t3.setName("Different");
        t3.setGoldenMoney(3L);
    }

    @Test
    public void equal() {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(t1).isEqualTo(t2);
        softly.assertThat(t1.hashCode()).isEqualTo(t2.hashCode());
        softly.assertAll();
    }

    @Test
    public void notEqual() {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(t1).isNotEqualTo(t3);
        softly.assertThat(t1.hashCode()).isNotEqualTo(t3.hashCode());
        softly.assertAll();
    }

}
