/**
 * @author Jiří Novotný
 */

package cz.muni.fi.pa165.dndtroops.entities;

import cz.muni.fi.pa165.dndtroops.enums.Power;
import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Power power;

    @Min(value = 1)
    private int damage = 1;

    @Min(value = 0)
    private int cooldown = 0;

    /**
     * No-arg constructor required by JPA specification.
     */
    public Role() {
    }

    /**
     * Construct Role with attributes set.
     *
     * @param name           name of a Role
     * @param description    description of a Role
     * @param power          Role's primary power
     * @param damage         attack damage value
     * @param cooldown       how many rounds will this ability be on a cooldown
     */
    public Role(String name, String description, Power power, int damage, int cooldown) {
        this.name = name;
        this.description = description;
        this.power = power;
        this.damage = damage;
        this.cooldown = cooldown;
    }

    /**
     * Perform equality check of Role using it's business key.
     * The business key consists of Role's name property (it is unique).
     *
     * @param obj object to compare with
     * @return true if business keys are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Role)) return false;

        Role role = (Role) obj;
        return Objects.equals(getName(), role.getName());
    }

    /**
     * Return hash code of Role.
     * Hash code is computed using business key stated in JavaDoc for equals method.
     *
     * @return hash code of Role
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
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
}
