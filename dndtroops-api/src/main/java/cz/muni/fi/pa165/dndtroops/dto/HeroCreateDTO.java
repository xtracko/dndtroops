package cz.muni.fi.pa165.dndtroops.dto;

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

    private Set<RoleDTO> roles = new HashSet<>();

    @NotNull
    private Integer xp;

    @NotNull
    private Integer health = 100;

    @NotNull
    private boolean cooldown = false;

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

    public Set<RoleDTO> getRoles() {
        return roles;
    }

    public void addRole(RoleDTO role) {
        roles.add(role);
    }

    public void setRoles(Set<RoleDTO> roles) {
        this.roles = roles;
    }

    public Integer getXp() {
        return xp;
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
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + Objects.hashCode(this.troop);
        hash = 37 * hash + Objects.hashCode(this.roles);
        hash = 37 * hash + Objects.hashCode(this.xp);
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
        final HeroCreateDTO other = (HeroCreateDTO) obj;
        if (!Objects.equals(this.name, other.name)) {
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
        return "HeroCreateDTO{" + "name=" + name + ", troop=" + troop + ", role=" + roles + ", xp=" + xp + '}';
    }


}
