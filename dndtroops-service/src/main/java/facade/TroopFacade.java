package facade;

import cz.muni.fi.pa165.dndtroops.dto.TroopDto;

import java.util.List;

public interface TroopFacade {

    /*
     * Presist Troop.
     *
     * @param Troop to be persisted
      */
    void createTroop(TroopDto t );

    /*
    * Remove persisted Troop.
    *
    * @param Troop to be removed.
    */
    void deleteTroop(TroopDto t );

    /*
    * Merge the state of the given entity into the current persistence context.
    *
    * @param Troop to be updated.
    */
    void updateTroop(TroopDto t );

    /*
    * Finds troop by given troop ID. Returns null when no troop found with given ID.
    *
    * @param ID of the searched troop
    * @return Troop if troop with given ID exists, if not returns null
     */
    TroopDto findTroopById(Long id);

    /*
    * Finds troop by given name of the troop. If troop with given name is not persisted, returns null.
    *
    * @param String with name of the searched troop.
    * @return Troop with given name or null if not persisted.
     */
    TroopDto findTroopByName(String name);

    /*
   * Retrieves a list of all persisted troops.
   *
   * @return Troop with given name or null if not persisted.
    */
    List<TroopDto> findAllTroops();

}
