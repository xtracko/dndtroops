package cz.muni.fi.pa165.dndtroops.dto;

import java.util.Objects;

/**
 * @author Miroslav Macor
 */

public class AdminDTO {
    private Long id;
    private String name;

    public AdminDTO(Long id, String name){
        this.id = id;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof AdminDTO)) return false;

        AdminDTO admin = (AdminDTO) o;

        return Objects.equals(name, admin.getName());
    }

    @Override
    public int hashCode() {
        int hash = 17 + getName().hashCode();
        return hash;
    }

}
