package cz.muni.fi.pa165.dndtroops.dao;

import cz.muni.fi.pa165.dndtroops.entities.Troop;

import java.util.List;

public interface TroopDao {

    public void create (Troop t );
    public void delete (Troop t );
    public void update (Troop t );
    public Troop findById (long id);
    public Troop findByName (String name);
    public List<Troop> findAll();

}
