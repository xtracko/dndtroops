package cz.muni.fi.pa165.dndtroops.entities;

/**
 * Created by Miroslav Macor
 */
public class Administrator {
    private String name;
    private long id;


    public Administrator(String name, long id) {
        this.name = name;
        this.id = id;
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

    public void setId(long id) {
        this.id = id;
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
