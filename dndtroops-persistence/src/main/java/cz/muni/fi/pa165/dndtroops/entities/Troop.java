package cz.muni.fi.pa165.dndtroops.entities;

import javax.persistence.*;

/*

Entity class for entity Troop.
Creted by: Vojtech Duchon (UCO:410007)

 */


@Entity
public class Troop {


    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String mission;

    private Long goldenMoney;

    /*
    No argument constructor.
     */

    public Troop(){

    }

    /*
    Constructor with specified attributes.
     */


    public Troop(String name, String mission, Long goldenMoney)
    {
        this.name=name;
        this.mission=mission;
        this.goldenMoney=goldenMoney;
    }

    /*
    Override of default Object.equals() method
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Troop)) return false;

        Troop troop = (Troop) o;

        if (!getName().equals(troop.getName())) return false;
        return (getMission().equals(troop.getMission()));
    }

    /*
    Override of default Object method for generation of instance hashcode.
     */

    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 17 + getName().hashCode();
        hash = hash * 31 + getMission().hashCode();
        return hash;
    }

    /*
    Getters and setters
    --- BEGIN ---
     */

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

    public Long getGoldenMoney() {
        return goldenMoney;
    }

    public void setGoldenMoney(Long goldenMoney) {
        this.goldenMoney = goldenMoney;
    }

    /*
    Getters and setters
    --- END ---
     */

}
