package cz.muni.fi.pa165.dndtroops.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * @author Miroslav Macor
 */

@Entity
public class Administrator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String name;
    
    @NotNull
    @Column(nullable = false)
    private boolean isAdmin;
    
    private String passwordHash;


    public Administrator(String name) {
        this.name = name;
        this.isAdmin=false;
    }

    public Administrator() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Administrator)) return false;

        Administrator administrator = (Administrator) obj;
        return Objects.equals(name, administrator.getName());
    }

    @Override
    public String toString() {
        return "Administrator{" + "id=" + id + ", name=" + name + ", isAdmin=" + isAdmin + ", passwordHash=" + passwordHash + '}';
    }
}
