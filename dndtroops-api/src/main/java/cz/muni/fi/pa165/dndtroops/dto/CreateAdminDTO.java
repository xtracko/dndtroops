package cz.muni.fi.pa165.dndtroops.dto;

import java.util.Objects;

/**
 * @author Miroslav Macor
 */

public class CreateAdminDTO {
    private String name;

    public CreateAdminDTO(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof AdminDTO)) return false;

        AdminDTO role = (AdminDTO) obj;
        return Objects.equals(getName(), role.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }

}