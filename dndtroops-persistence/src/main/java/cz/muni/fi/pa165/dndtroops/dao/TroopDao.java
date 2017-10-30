package cz.muni.fi.pa165.dndtroops.dao;

import cz.muni.fi.pa165.dndtroops.entities.Troop;

import java.util.List;

/*
*@author Vojtech Duchoň
 */

public interface TroopDao {
    /*
    * Presist Troop.
    *
    * @param Troop to be persisted
     */
    public void create (Troop t );

    /*
    * Remove persisted Troop.
    *
    * @param Troop to be removed.
    */
    public void delete (Troop t );

    /*
    * Merge the state of the given entity into the current persistence context.
    *
    * @param Troop to be updated.
    */
    public void update (Troop t );

    /*
    * Finds troop by given troop ID. Returns null when no troop found with given ID.
    *
    * @param ID of the searched troop
    * @return Troop if troop with given ID exists, if not returns null
     */
    public Troop findById (Long id);

    /*
    * Finds troop by given name of the troop. If troop with given name is not persisted, returns null.
    *
    * @param String with name of the searched troop.
    * @return Troop with given name or null if not persisted.
     */
    public Troop findByName (String name);

    /*
   * Retrieves a list of all persisted troops.
   *
   * @return Troop with given name or null if not persisted.
    */
    public List<Troop> findAll();

}
