package cz.muni.fi.pa165.dndtroops.dto;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * @author Miroslav Macor
 */

public class AdminDTO {
    @NotNull
    private Long id;

    @NotNull
    private String name;
    
    private boolean isAdmin = false;

    private String passwordHash;

    public AdminDTO() {
    }

    public AdminDTO(Long id, String name){
        this.id = id;
        this.name = name;
        this.isAdmin=false;
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
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + (this.isAdmin ? 1 : 0);
        hash = 89 * hash + Objects.hashCode(this.passwordHash);
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
        final AdminDTO other = (AdminDTO) obj;
        if (this.isAdmin != other.isAdmin) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.passwordHash, other.passwordHash)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    

}
