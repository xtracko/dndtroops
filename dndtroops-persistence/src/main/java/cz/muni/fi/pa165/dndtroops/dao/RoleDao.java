/**
 * @author Jiří Novotný
 */

package cz.muni.fi.pa165.dndtroops.dao;

import cz.muni.fi.pa165.dndtroops.entities.Role;

import java.util.List;

/**
 * Data Access Object for Role entity.
 */
public interface RoleDao {

    /**
     * Persist a Role.
     *
     * @param role a Role to persist
     */
    void createRole(Role role);

    /**
     * Delete persisted Role.
     *
     * @param role a persisted Role to deleteTroop
     */
    void deleteRole(Role role);

    /**
     * Find persisted Role by it's unique ID. If no such role is found null is returned.
     *
     * @param id an ID of a Role to find
     * @return Role with the required ID or null if such Role does not exists
     */
    Role findRoleById(Long id);

    /**
     * Find persisted Role by it's unique name. If no such role is found null is returned.
     *
     * @param name a name of a Role to find
     * @return Role with the required name or null if such Role does not exists
     */
    Role findRoleByName(String name);


    /**
     * Find all persisted Roles and return them as a List.
     *
     * @return List of all persisted Roles
     */
    List<Role> findAllRoles();

}
