/**
 * @author Jiří Novotný
 */
package cz.muni.fi.pa165.dndtroops.entities;

import cz.muni.fi.pa165.dndtroops.PersistenceSampleApplicationContext;
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
    public void businessKeyTest() {
        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(new Troop()).isEqualTo(new Troop());

        softly.assertThat(t1).isEqualTo(t2);
        softly.assertThat(t1.hashCode()).isEqualTo(t2.hashCode());

        softly.assertThat(t1).isNotEqualTo(t3);
        softly.assertThat(t1.hashCode()).isNotEqualTo(t3.hashCode());

        softly.assertAll();
    }

    @Test
    public void equalsBestPracticies() {
        class Mock extends Troop {
            private boolean getNameCalled = false;

            @Override
            public String getName() {
                getNameCalled = true;
                return super.getName();
            }
        }

        Mock mock = new Mock();
        Troop troop = new Troop();

        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(troop).isEqualTo(mock);
        softly.assertThat(mock.getNameCalled).isTrue();

        softly.assertAll();
    }

}
