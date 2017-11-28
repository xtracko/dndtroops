package cz.muni.fi.pa165.dndtroops.service;

import cz.muni.fi.pa165.dndtroops.entities.Hero;
import cz.muni.fi.pa165.dndtroops.entities.Troop;

import java.util.List;

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
     * returns all heroes for troop
     * @param t
     * @return
     */
    List<Hero> findHeroesOfTroop(Troop t);

    /**
     * compute strength of a troop
     * @param t
     * @return
     */
    float computeTroopStrength(Troop t);

    /**
     * battles 2 troops
     * @param t1
     * @param t2
     * @return
     */
    Troop troopBattle(Troop t1, Troop t2);

}
