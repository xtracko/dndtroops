package cz.muni.fi.pa165.dndtroops.service;

import cz.muni.fi.pa165.dndtroops.entities.Troop;

import java.util.List;

/**
 * @author Vojtěch Duchoň
 */
public interface TroopService {
    /**
     * creates new troop
     * @param t
     */
    void createTroop(Troop t );

    /**
     * deletes troop
     * @param t
     */
    void deleteTroop(Troop t );

    /**
     * updates troop
     * @param t
     */
    void updateTroop(Troop t );

    /**
     * finds troop by id
     * @param id
     * @return
     */
    Troop findTroopById(Long id);

    /**
     * finds troop by name
     * @param name
     * @return
     */
    Troop findTroopByName(String name);

    /**
     * find all troops
     * @return
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
