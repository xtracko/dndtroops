package cz.muni.fi.pa165.dndtroops.dto;

/**
 * @author Jiří Novotný
 */

import cz.muni.fi.pa165.dndtroops.enums.Power;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class RoleDTO {
    @NotNull
    private Long id;

    @NotNull
    private String name;

    private String description;

    @NotNull
    private Power power;

    @Min(value = 1)
    private int damage;

    @Min(value = 0)
    private int cooldown;

    public RoleDTO() {
    }

    public RoleDTO(Long id, String name, String description, Power power, int damage, int cooldown) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.power = power;
        this.damage = damage;
        this.cooldown = cooldown;
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

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
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

    @Override
    public String toString() {
        return "RoleDTO{" + "id=" + id + ", name=" + name + ", description=" + description + ", power=" + power + ", damage=" + damage + ", cooldown=" + cooldown + '}';
    }

    
}
