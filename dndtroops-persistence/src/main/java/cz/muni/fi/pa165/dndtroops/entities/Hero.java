package cz.muni.fi.pa165.dndtroops.entities;

import java.io.Serializable;

import java.util.*;

import javax.persistence.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author Martin Sestak and Jiří Novotný (changes to the non-trivial bussiness functionality)
 *
 * Class which represents entity hero
 */
@Entity
public class Hero implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable=false,unique=true)
    private String name;

    @NotNull
    @ManyToOne(optional=false, fetch=FetchType.LAZY, cascade={CascadeType.REFRESH, CascadeType.MERGE})
    private Troop troop;

    @Min(value = 1)
    private int health = 100;

    @Min(value = 0)
    private int xp = 0;

    @ManyToMany
    private List<Role> roles = new ArrayList<>();


    public Hero() {
    }

    /**
     * Consructor for Hero entity , none of the input parameters shouldn't be null
     *
     * @param name name of the hero
     * @param troop troop to which hero belongs to
     * @param health hero's health
     * @param xp hero's experience level
     * @param roles roles of hero(Mage, Knight, Hunter, ... )
     */
    public Hero(String name, Troop troop, int health, int xp, Role... roles) {
        this.name = name;
        this.troop = troop;
        this.health = health;
        this.xp = xp;

        Collections.addAll(this.roles, roles);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Troop getTroop() {
        return this.troop;
    }

    public void setTroop(Troop troop) {
        this.troop = troop;
    }

    public List<Role> getRoles() {
        return roles;
    }
    
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void removeRole(Role role) {
        roles.remove(role);
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getHealth() {
        return health;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Hero)) return false;

        Hero hero = (Hero) obj;
        return Objects.equals(getName(), hero.getName());
    }

    @Override
    public String toString() {
        return "Hero{id=" + id +
                ", name=" + name +
                ", troop=" + troop +
                ", health=" + health +
                ", xp=" + xp +
                ", roles=" + roles +
                "}";
    }
}