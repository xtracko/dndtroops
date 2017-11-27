package cz.muni.fi.pa165.dndtroops.dto;

import java.io.Serializable;
import java.util.*;

/**
 *
 * @author Martin Sestak
 */
public class HeroDTO implements Serializable {
    private Long id;

    private String name;
    
    private TroopDTO troop;
    
    private Set<RoleDTO> roles = new HashSet<>();
    
    private Integer xp;
    
    private Integer health = 100;

    private boolean cooldown = false;

    public HeroDTO() {
    }
    
    /**
     * Constructor for HeroDTo
     * @param name of hero
     * @param troop of hero
     * @param xp of hero
     */
    public HeroDTO(String name, TroopDTO troop, Integer xp) {
        this.name = name;
        this.troop = troop;
        this.xp = xp;
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

    public Set<RoleDTO> getRoles() {
        return roles;
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

    public void addRole(RoleDTO role) {
        roles.add(role);
    }

    public void removeRole(RoleDTO role) {
        roles.remove(role);
    }

    public boolean hasRole(RoleDTO role) {
        return roles.contains(role);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.name);
        hash = 31 * hash + Objects.hashCode(this.troop);
        hash = 31 * hash + Objects.hashCode(this.roles);
        hash = 31 * hash + Objects.hashCode(this.xp);
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
        final HeroDTO other = (HeroDTO) obj;
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
        return "HeroDTO{" + "id=" + id + ", name=" + name + ", troop=" + troop + ", roles=" + roles + ", xp=" + xp + '}';
    } 
    
}
