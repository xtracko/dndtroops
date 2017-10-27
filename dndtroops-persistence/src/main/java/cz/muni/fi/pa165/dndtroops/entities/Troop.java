package cz.muni.fi.pa165.dndtroops.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table (name = "troop")
public class Troop {


    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name= "id")
    private long id;

    @Column(name= "name")
    private String name;

    @Column (name= "mission")
    private String mission;

    @Column (name= "goldenMoney")
    private long goldenMoney;

    public Troop(){

    }

    public Troop(long id, String name, String mission, long goldenMoney)
    {
        this.id = id;
        this.name=name;
        this.mission=mission;
        this.goldenMoney=goldenMoney;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Troop)) return false;

        Troop troop = (Troop) o;

        if (getId() != troop.getId()) return false;
        if (getGoldenMoney() != troop.getGoldenMoney()) return false;
        if (getName() != null ? !getName().equals(troop.getName()) : troop.getName() != null) return false;
        return getMission() != null ? getMission().equals(troop.getMission()) : troop.getMission() == null;
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 17 + Objects.hashCode(getId());
        hash = hash * 31 + Objects.hashCode(getGoldenMoney());
        hash = hash * 13 + Objects.hashCode(getName());
        return hash;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public long getGoldenMoney() {
        return goldenMoney;
    }

    public void setGoldenMoney(long goldenMoney) {
        this.goldenMoney = goldenMoney;
    }

    // TODO

}
