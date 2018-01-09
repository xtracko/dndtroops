package cz.muni.fi.pa165.dndtroops.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/*
 * @author Vojtech Duchoň
 */
@Entity
public class Troop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String name;

    private String mission;

    private long goldenMoney;

    /*
     * No-arg constructor.
     */
    public Troop() {
    }

    public Troop(String name, String mission, long goldenMoney) {
        this.name = name;
        this.mission = mission;
        this.goldenMoney = goldenMoney;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof Troop)) return false;

        Troop troop = (Troop) o;
        return Objects.equals(name, troop.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}
