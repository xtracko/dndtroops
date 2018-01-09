package cz.muni.fi.pa165.dndtroops.service;

import cz.muni.fi.pa165.dndtroops.entities.Troop;

import java.util.List;

/**
 * @author Vojtěch Duchoň
 */
public interface TroopService {
    /**
     * Creates new troop
     *
     * @param t a troop to create in database
     */
    void createTroop(Troop t );

    /**
     * Removes troop
     *
     * @param t troop to remove from database
     */
    void removeTroop(Troop t );

    /**
     * Updates troop
     *
     * @param t troop to update in database
     */
    void updateTroop(Troop t );

    /**
     * Finds troop by id
     *
     * @param id an ID of a Troop to find
     * @return a Troop with the given ID or null if no such Troop exists
     */
    Troop findTroopById(Long id);

    /**
     * Finds troop by name
     *
     * @param name a name of a Troop to find
     * @return a Troop with the given name or null if no such Troop exists
     */
    Troop findTroopByName(String name);

    /**
     * Find all troops
     * @return all persisted troops
     */
    List<Troop> findAllTroops();

    /**
     * Perform a battle among two troops
     *
     * @param a first troop
     * @param b second troop
     * @return victorious troop or null if the battle ended with draw
     */
    Troop battle(Troop a, Troop b);
}
