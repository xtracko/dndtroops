package cz.muni.fi.pa165.dndtroops.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 *
 * @author Martin Sestak
 */
public class HeroCreateDTO {
    @NotNull
    @Size(min = 3, max = 255)
    private String name;
    
    @NotNull
    private TroopDTO troop;
    
    private List<RoleDTO> role = new ArrayList<>();
    
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

    public List<RoleDTO> getRoleList() {
        return Collections.unmodifiableList(role);
    }

    public void addRole(List<RoleDTO> role) {
         this.role.add((RoleDTO) role);
    }
    
    public void setRoleList(List<RoleDTO> role) {
        this.role = role;
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
        hash = 37 * hash + Objects.hashCode(this.role);
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
        if (!Objects.equals(this.role, other.role)) {
            return false;
        }
        if (!Objects.equals(this.xp, other.xp)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HeroCreateDTO{" + "name=" + name + ", troop=" + troop + ", role=" + role + ", xp=" + xp + '}';
    }
    
    
    
}
