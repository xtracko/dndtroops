package cz.muni.fi.pa165.dndtroops.dto;

import cz.muni.fi.pa165.dndtroops.enums.Power;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * @author Jiří Novotný
 */

public class CreateRoleDTO {
    @NotNull
    private String name;

    private String description;

    @NotNull
    private Power power;

    @Min(value = 1)
    private int damage;

    @Min(value = 0)
    private int cooldown;

    public CreateRoleDTO() {
    }

    public CreateRoleDTO(String name, String description, Power power, int damage, int cooldown) {
        this.name = name;
        this.description = description;
        this.power = power;
        this.damage = damage;
        this.cooldown = cooldown;
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

}
