package cz.muni.fi.pa165.dndtroops.dao;

import cz.muni.fi.pa165.dndtroops.entities.Troop;

import java.util.List;

/*

DAO class for entity Troop.
Creted by: Vojtech Duchon (UCO:410007)

 */

public interface TroopDao {

    public void create (Troop t );
    public void delete (Troop t );
    public void update (Troop t );
    public Troop findById (Long id);
    public Troop findByName (String name);
    public List<Troop> findAll();

}
