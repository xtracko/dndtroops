package cz.muni.fi.pa165.dndtroops.facade;

import cz.muni.fi.pa165.dndtroops.dto.TroopCreateDTO;
import cz.muni.fi.pa165.dndtroops.dto.TroopDTO;

import java.util.List;

/**
 * @author Vojtěch Duchoň
 */
public interface TroopFacade {
    /*
     * Presist Troop.
     *
     * @param Troop to be persisted
      */
    TroopDTO createTroop(TroopCreateDTO t );

    /*
    * Remove persisted Troop.
    *
    * @param Troop to be removed.
    */
    void removeTroop(Long id);

    /*
    * Merge the state of the given entity into the current persistence context.
    *
    * @param Troop to be updated.
    */
    void updateTroop(TroopDTO t );

    /*
    * Finds troop by given troop ID. Returns null when no troop found with given ID.
    *
    * @param ID of the searched troop
    * @return Troop if troop with given ID exists, if not returns null
     */
    TroopDTO findTroopById(Long id);

    /*
    * Finds troop by given name of the troop. If troop with given name is not persisted, returns null.
    *
    * @param String with name of the searched troop.
    * @return Troop with given name or null if not persisted.
     */
    TroopDTO findTroopByName(String name);

    /*
   * Retrieves a list of all persisted troops.
   *
   * @return Troop with given name or null if not persisted.
    */
    List<TroopDTO> findAllTroops();

    /**
     * Perform a battle among two troops
     *
     * @param a first troop
     * @param b second troop
     * @return victorious troop or null if the battle ended with draw
     */
    TroopDTO battle(TroopDTO a, TroopDTO b);
}
