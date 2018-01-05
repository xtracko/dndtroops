package cz.muni.fi.pa165.dndtroops.mvc.models;

/**
 * @author Jiří Novotný
 */

public class BattleModel {
    private long troop1 = 0;
    private long troop2 = 0;

    public BattleModel() {
        this.troop1 = 0;
        this.troop2 = 0;
    }

    public long getTroop1() {
        return troop1;
    }

    public void setTroop1(long troop1) {
        this.troop1 = troop1;
    }

    public long getTroop2() {
        return troop2;
    }

    public void setTroop2(long troop2) {
        this.troop2 = troop2;
    }
}
