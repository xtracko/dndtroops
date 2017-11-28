package cz.muni.fi.pa165.dndtroops.dto;

/**
 * @author Jiří Novotný
 */

import cz.muni.fi.pa165.dndtroops.enums.Power;

import java.util.Objects;

public class RoleDTO {

    private Long id;
    private String name;
    private String description;
    private Power power;
    private float damageMean;
    private float damageVariance;

    public RoleDTO() {
    }

    public RoleDTO(Long id, String name, String description, Power power, float damageMean, float damageVariance) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.power = power;
        this.damageMean = damageMean;
        this.damageVariance = damageVariance;
    }

    public RoleDTO() {
    }

    public RoleDTO(Long id, String name, String description, Power power, float damageMean, float damageVariance) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.power = power;
        this.damageMean = damageMean;
        this.damageVariance = damageVariance;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Power getPower() {
        return power;
    }

    public void setPower(Power power) {
        this.power = power;
    }

    public float getDamageMean() {
        return damageMean;
    }

    public void setDamageMean(float damageMean) {
        this.damageMean = damageMean;
    }

    public float getDamageVariance() {
        return damageVariance;
    }

    public void setDamageVariance(float damageVariance) {
        this.damageVariance = damageVariance;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof RoleDTO)) return false;

        RoleDTO role = (RoleDTO) obj;
        return Objects.equals(getName(), role.getName());
    }


    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }

}
