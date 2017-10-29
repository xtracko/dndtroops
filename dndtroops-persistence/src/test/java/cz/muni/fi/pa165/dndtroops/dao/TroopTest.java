/**
 * @author Jiří Novotný
 */
package cz.muni.fi.pa165.dndtroops.dao;

import cz.muni.fi.pa165.dndtroops.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.dndtroops.entities.Troop;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;

@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
public class TroopTest {

    @Test
    public void businessEquality() {
        Troop troop = new Troop("T", "A", 0);
        Troop troopId = new Troop("T", "A", 0);
        troopId.setId(122L);

        Assert.assertEquals(new Troop(), new Troop());
        Assert.assertEquals(new Troop("T", "A", 0), troop);
        Assert.assertEquals(new Troop("T", "A", 0), troop);
        Assert.assertEquals(new Troop("T", "B", 1), troop);
        Assert.assertEquals(troopId, troop);

        Assert.assertNotEquals(new Troop("S", "A", 0), troop);
        Assert.assertNotEquals(null, troop);
        Assert.assertNotEquals(null, troopId);
    }

    @Test
    public void businessHash() {
        Troop a1 = new Troop("A", "", 0);
        Troop a2 = new Troop("A", "M", 2);
        Troop b = new Troop("B", "", 0);
        a2.setId(254L);

        Assert.assertEquals(a1.hashCode(), a1.hashCode());
        Assert.assertEquals(a1.hashCode(), a2.hashCode());

        Assert.assertNotEquals(a1.hashCode(), b.hashCode());
        Assert.assertNotEquals(a2.hashCode(), b.hashCode());
    }

}
