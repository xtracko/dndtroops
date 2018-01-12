package cz.muni.fi.pa165.dndtroops.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author Martin Sestak
 */
public class HeroCreateDTO {
    @NotNull
    private String name;

    @NotNull
    private TroopDTO troop;

    @Min(value = 1)
    private int health = 100;

    @Min(value = 0)
    private int xp = 0;

    private List<RoleDTO> roles = new ArrayList<>();
    

    public HeroCreateDTO() {}

    public HeroCreateDTO(String name, TroopDTO troop, int health, int xp, RoleDTO... roles) {
        this.name = name;
        this.troop = troop;
        this.health = health;
        this.xp = xp;

        Collections.addAll(this.roles, roles);
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
