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
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String name;

    private String mission;

    @NotNull
    @Column (nullable = false)
    private long goldenMoney;

    /*@OneToMany()
    @JoinColumn
    private List<Hero> heroes = new ArrayList<>();*/

    /*
     * No argument constructor.
     */

    public Troop(){

    }

    /*
     * Constructor with specified attributes.
     *
     * @param name          Name of the troop.
     * @param mission       Troop's assigned mission
     * @param goldenMoney   Amount of assigned golden money
     * @param hero          Hero that belongs to the troop
     */


   /* public Troop(String name, String mission, long goldenMoney, Hero hero)
    {
        this.name=name;
        this.mission=mission;
        this.goldenMoney=goldenMoney;
        this.heroes.add(hero);
    }*/

     /*
     * Constructor with specified attributes (no-hero).
     *
     * @param name          Name of the troop.
     * @param mission       Troop's assigned mission
     * @param goldenMoney   Amount of assigned golden money
     */

    public Troop(String name, String mission, long goldenMoney)
    {
        this.name=name;
        this.mission=mission;
        this.goldenMoney=goldenMoney;
    }

    /*
     * Override of default Object.equals() method - it uses troop's name as a business key.
     *
     * @param Object    An object to be compared with.
     * @return          Returns true if the objects are equal.
     */

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof Troop)) return false;

        Troop troop = (Troop) o;

        return Objects.equals(name, troop.getName());
    }

    /*
     * Override of default Object method for generation of instance hashcode.
     *
     * @return Returns the value of the hash code.
     */

    @Override
    public int hashCode() {
        int hash = 17 + getName().hashCode();
        return hash;
    }

    /*
    /*
     * Getters and setters
     * --- BEGIN ---
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

    /*public List<Hero> getHeroes() {return heroes;}

    public void addHero(Hero hero) {heroes.add(hero);}*/

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public long getGoldenMoney() {
        return goldenMoney;
    }

    public void setGoldenMoney(Long goldenMoney) {
        this.goldenMoney = goldenMoney;
    }

    /*
     * Getters and setters
     * --- END ---
     */

}
