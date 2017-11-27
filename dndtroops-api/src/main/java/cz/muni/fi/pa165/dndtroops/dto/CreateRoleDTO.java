package cz.muni.fi.pa165.dndtroops.dto;

import cz.muni.fi.pa165.dndtroops.enums.Power;

import java.util.Objects;

/**
 * @author Jiří Novotný
 */

public class CreateRoleDTO {

    private String name;
    private String description;
    private Power power;
    private float damageMean;
    private float damageVariance;

    public CreateRoleDTO(String name, String description, Power power, float damageMean, float damageVariance) {
        this.name = name;
        this.description = description;
        this.power = power;
        this.damageMean = damageMean;
        this.damageVariance = damageVariance;
    }

    public CreateRoleDTO() {
    }

    
    public CreateRoleDTO(String name, String description, Power power) {
        this.name = name;
        this.description = description;
        this.power = power;
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
