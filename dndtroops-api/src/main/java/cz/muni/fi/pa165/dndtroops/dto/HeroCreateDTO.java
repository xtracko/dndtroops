package cz.muni.fi.pa165.dndtroops.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

/**
 * @author Martin Sestak
 */
public class HeroCreateDTO {
    @NotNull
    @Size(min = 3, max = 255)
    private String name;

    @NotNull
    private TroopDTO troop;

    @Min(value = 1)
    private int health = 100;

    @Min(value = 0)
    private int xp = 0;

    private List<RoleDTO> roles = new ArrayList<>();
    
    long roleId;
    
    long troopId;


    public HeroCreateDTO() {}

    public HeroCreateDTO(String name, TroopDTO troop, int health, int xp, RoleDTO... roles) {
        this.name = name;
        this.troop = troop;
        this.health = health;
        this.xp = xp;

        Collections.addAll(this.roles, roles);
    }
    public HeroCreateDTO(String name, long troop, int health, int xp, long role) {
        this.name = name;
        this.troopId = troop;
        this.health = health;
        this.xp = xp;
        this.roleId=role;

        //Collections.addAll(this.roles, roles);
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

    public void addRole(RoleDTO role) {
        roles.add(role);
    }
    public void setTroopId(String id) {
        long val = Long.valueOf(id);
        this.troopId = val;
    }
    public long getTroopId() {
        return this.troopId;
    }

    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
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
        return "HeroCreateDTO{name=" + name +
                ", troop=" + troop +
                ", health=" + health +
                ", xp=" + xp +
                ", roles=" + roles +
                "}";
    }
}
