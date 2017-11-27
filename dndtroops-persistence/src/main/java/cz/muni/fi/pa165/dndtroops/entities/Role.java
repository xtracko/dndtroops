/**
 * @author Jiří Novotný
 */

package cz.muni.fi.pa165.dndtroops.entities;

import cz.muni.fi.pa165.dndtroops.enums.Power;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Power power;

    private float damageMean;
    private float damageVariance;

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
     * @param damageMean     attack damage mean value
     * @param damageVariance attack damage variance
     */
    public Role(String name, String description, Power power, float damageMean, float damageVariance) {
        this.name = name;
        this.description = description;
        this.power = power;
        this.damageMean = damageMean;
        this.damageVariance = damageVariance;
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

    public float getDamageMean() {
        return damageMean;
    }

    public void setDamageMean(float damageMean) {
        this.damageMean = damageMean;
    }

    public float getDamageVariance() {
        return damageVariance;
    }

    public void setDamageVariance(float demageVariance) {
        this.damageVariance = demageVariance;
    }
}
