/**
 * Created by Miroslav Macor
 */

package cz.muni.fi.pa165.dndtroops.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Administrator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String name;


    public Administrator(String name) {
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof  Administrator)) return false;

        Administrator admin = (Administrator) o;

        if (!getName().equals(admin.getName())) return false;
        return  (getId() == (admin.getId()));
    }

    @Override
    public int hashCode() {
        return 67 * getName().hashCode();
    }
}
