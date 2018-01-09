package cz.muni.fi.pa165.dndtroops.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Martin Sestak
 */
public class HeroDTO implements Serializable {
    private Long id;

    private String name;
    
    private TroopDTO troop;

    private int health = 100;

    private int xp = 0;
    
    private List<RoleDTO> roles = new ArrayList<>();

    public HeroDTO() {
    }
    
    /**
     * Constructor for HeroDTO
     *
     * @param id id of a hero
     * @param name name of a hero
     * @param troop troop of a hero
     * @param xp experience level of a hero
     * @param roles roles of a hero
     */
    public HeroDTO(Long id, String name, TroopDTO troop, int health, int xp, RoleDTO... roles) {
        this.id = id;
        this.name = name;
        this.troop = troop;
        this.health = health;
        this.xp = xp;

        Collections.addAll(this.roles, roles);
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

    public TroopDTO getTroop() {
        return troop;
    }

    public void setTroop(TroopDTO troop) {
        this.troop = troop;
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }

    public void addRole(RoleDTO role) {
        roles.add(role);
    }

    public void removeRole(RoleDTO role) {
        roles.remove(role);
    }

    public boolean hasRole(RoleDTO role) {
        return roles.contains(role);
    }

    public int getHealth() {
        return health;
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

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof HeroDTO)) return false;

        HeroDTO hero = (HeroDTO) obj;
        return Objects.equals(getName(), hero.getName());
    }

    @Override
    public String toString() {
        return "HeroDTO{id=" + id +
                ", name=" + name +
                ", troop=" + troop +
                ", health=" + health +
                ", xp=" + xp +
                ", roles=" + roles +
                "}";
    }
}
