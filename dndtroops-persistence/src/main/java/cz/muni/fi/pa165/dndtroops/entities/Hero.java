package cz.muni.fi.pa165.dndtroops.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Column;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Class which represents entity hero
 * @author Martin Sestak
 */
@Entity
@Table(name="Hero")
public class Hero {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
        
        @NotNull
	@Column(nullable=false,unique=true)
	private String name;
        
        @ManyToOne(optional=false)
	@NotNull
	private Troop troop;
        
        @OneToMany()
	@JoinColumn(name="priceStaHero_FK")
	private List<Role> role = new ArrayList<Role>();
        
        @NotNull
        private Integer xp;
        
    public Hero() {
    }

    /**
     * Consructor for Hero entity , none of the input parameters shouldn't be null
     * @param name name of the hero
     * @param troop troop to which hero belongs to
     * @param xp level of experience
     * @param role role of hero(Mage, Knight, Hunter, ... )
     * @throws IllegalArgumentException when name, troop or role is null
     */
    public Hero( String name, Troop troop,Role role, Integer xp) {
        
        if(name == null){
            throw new IllegalArgumentException("Name cannot be null");
        }
        
        if(troop == null){
            throw new IllegalArgumentException("Troop cannot be null");
        }
        if(role == null){
            throw new IllegalArgumentException("Role cannot be null");
        }
        
        if(xp == null){
            this.xp = 0;
        }
        else{
            this.xp = xp;
        }
        
        this.role.add(role);
        this.name = name;
        this.troop = troop;
        
    }        
        
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Troop getTroop() {
        return this.troop;
    }

    public void setTroop(Troop troop) {
        this.troop = troop;
    }

    public List<Role> getRoleList() {
        return Collections.unmodifiableList(this.role);
    }

    public void addRole(Role role) {
        this.role.add(role);
    }

    public Integer getXp() {
        return this.xp;
    }

    public void setXp(Integer xp) {
        this.xp = xp;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + Objects.hashCode(this.troop);
        hash = 67 * hash + Objects.hashCode(this.role);
        hash = 67 * hash + Objects.hashCode(this.xp);
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
        final Hero other = (Hero) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.troop, other.troop)) {
            return false;
        }
        if (!Objects.equals(this.role, other.role)) {
            return false;
        }
        if (!Objects.equals(this.xp, other.xp)) {
            return false;
        }
        return true;
    }
        
}
