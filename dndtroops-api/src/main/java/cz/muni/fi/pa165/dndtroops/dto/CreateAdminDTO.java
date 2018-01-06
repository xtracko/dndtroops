package cz.muni.fi.pa165.dndtroops.dto;

import java.util.Objects;

/**
 * @author Miroslav Macor
 */

public class CreateAdminDTO {
    private String name;
    private boolean isAdmin;
    
    private String passwordHash;
    
    public CreateAdminDTO(String name){
        this.name = name;
        this.isAdmin=false;
        
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
        hash = 47 * hash + Objects.hashCode(this.name);
        hash = 47 * hash + (this.isAdmin ? 1 : 0);
        hash = 47 * hash + Objects.hashCode(this.passwordHash);
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
        final CreateAdminDTO other = (CreateAdminDTO) obj;
        if (this.isAdmin != other.isAdmin) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.passwordHash, other.passwordHash)) {
            return false;
        }
        return true;
    }

    

}