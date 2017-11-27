package cz.muni.fi.pa165.dndtroops.entities;

import java.io.Serializable;

import java.util.*;

import javax.persistence.*;

import javax.validation.constraints.NotNull;

/**
 * Class which represents entity hero
 * @author Martin Sestak
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
    @ManyToOne(optional=false, cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    private Troop troop;

    @ManyToMany
    private Set<Role> roles = new HashSet<>();

    @NotNull
    private Integer xp;
    @NotNull
    private Integer health = 100;
    @NotNull
    private boolean cooldown = false;
    

    public Hero() {
    }

    /**
     * Consructor for Hero entity , none of the input parameters shouldn't be null
     * @param name name of the hero
     * @param troop troop to which hero belongs to
     * @param xp level of experience
     * @param roles roles of hero(Mage, Knight, Hunter, ... )
     * @throws IllegalArgumentException when name, troop or roles is null
     */
    public Hero(String name, Troop troop, Role roles, Integer xp) {

        if(name == null){
            throw new IllegalArgumentException("Name cannot be null");
        }

        if(troop == null){
            throw new IllegalArgumentException("Troop cannot be null");
        }
        if(roles == null){
            throw new IllegalArgumentException("Role cannot be null");
        }

        if(xp == null){
            this.xp = 0;
        }
        else{
            this.xp = xp;
        }
        this.roles.add(roles);
        this.troop = troop;
        
        this.name = name; 

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

    public Set<Role> getRoles() {
        return roles;
    }
    
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void removeRole(Role role) {
        roles.remove(role);
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public Integer getXp() {
        return this.xp;
    }

    public void setXp(Integer xp) {
        this.xp = xp;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public boolean isCooldown() {
        return cooldown;
    }

    public void setCooldown(boolean cooldown) {
        this.cooldown = cooldown;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + Objects.hashCode(this.troop);
        hash = 67 * hash + Objects.hashCode(this.roles);
        hash = 67 * hash + Objects.hashCode(this.xp);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Hero other = (Hero) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.troop, other.troop)) {
            return false;
        }
        if (!Objects.equals(this.roles, other.roles)) {
            return false;
        }
        if (!Objects.equals(this.xp, other.xp)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Hero{" + "id=" + id + ", name=" + name + ", troop=" + troop + ", roles=" + roles + ", xp=" + xp + '}';
    }
}