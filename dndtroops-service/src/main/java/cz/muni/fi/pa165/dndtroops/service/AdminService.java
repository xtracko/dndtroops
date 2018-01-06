package cz.muni.fi.pa165.dndtroops.service;

import cz.muni.fi.pa165.dndtroops.entities.Administrator;
import cz.muni.fi.pa165.dndtroops.entities.Hero;
import cz.muni.fi.pa165.dndtroops.entities.Troop;

import java.util.List;

/**
 * @author Miroslav Macor
 */

public interface AdminService {

    /**
     * Creates new Administrator
     * @param admin
     */
    void createAdministrator(Administrator admin,  String password);

    /**
     * Changes Administrator with the same admin.id. Changes only values that differ
     * @param admin
     */
    void updateAdministrator(Administrator admin);

    /**
     * Returns Administrator with the id or null
     * @param id of Administrator
     * @return Administrator with sanme id
     */
    Administrator findAdministatorById(Long id);

    /**
     * Finds Administrator by name
     * @param name of Administrator
     * @return Administrator with same name or null
     */
    Administrator findAdministratorByName(String name);

    /**
     * Get all Administrators
     * @return all current Administrator
     */
    List<Administrator> findAllAdministrators();

    /**
     * If exist removes Administrator
     * @param admin to be removed
     */
    void removeAdministrator(Administrator admin);
    
    /**
     * Tries to authenticate user
     * @param user user
     * @param password password hash
     * @return true if user is authenticated, false otherwise
     */
    boolean authenticate(Administrator admin, String password);

    /**
     * Determines whether user is admin
     * @param user user
     * @return true if user is admin, false otherwise
     */
    boolean isAdmin(Administrator admin);

    /**
     * Complete mission for troop
     * @param troop to finish mission
     * @param xpGained gained for mission
     * @param goldGained gained for mission
     * @param newMission gained for mission
     * @return updated troop
     */
    Troop complateMissionForTroop(Troop troop, int xpGained, int goldGained, String newMission);

    /**
     * Creates new party for list of heroes
     * @param heroes for a new party
     * @param mission new mission
     * @param name of a party
     * @param pulledGold gold of all heroes combined
     * @return
     */
    Troop createNewParty(List<Hero> heroes, String mission, String name, int pulledGold);

}
