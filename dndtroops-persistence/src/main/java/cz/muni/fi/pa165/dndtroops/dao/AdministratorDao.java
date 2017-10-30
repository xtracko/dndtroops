package cz.muni.fi.pa165.dndtroops.dao;

import cz.muni.fi.pa165.dndtroops.entities.Administrator;

import java.util.List;

/**
 * Creted by Miroslav Macor
 */

public interface AdministratorDao {

    /**
     * Creates new Administrator
     * @param admin
     */
    void createAdministrator(Administrator admin);

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

}
