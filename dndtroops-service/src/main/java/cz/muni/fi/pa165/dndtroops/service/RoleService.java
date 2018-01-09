package cz.muni.fi.pa165.dndtroops.service;

import cz.muni.fi.pa165.dndtroops.entities.Role;
import cz.muni.fi.pa165.dndtroops.enums.Power;

import java.util.List;

/**
 * @author Jiří Novotný
 */

public interface RoleService {

    /**
     * Create a new Role on the system
     *
     * @param role a Role to create
     * @return id of created role
     */
    void createRole(Role role);

    /**
     * Remove a Role from the system and do a proper cleanup
     *
     * @param role
     */
    void removeRole(Role role);

    /**
     * Update Role data.
     *
     * @param role a Role to update
     */
    Role updateRole(Role role);

    /**
     * Find Role by an ID
     *
     * @param id ID to find
     * @return Role with ID 'id'
     */
    Role findRoleById(long id);

    /**
     * List all Roles
     *
     * @return all existing Roles
     */
    List<Role> findAllRoles();

    /**
     * List all Roles which have some specific Power
     *
     * @param power a Power of a Roles to find
     * @return list of Roles
     */
    List<Role> findRolesByPower(Power power);

    /**
     * Compute attacking force based on random attempt
     *
     * @param role a Role for which to compute random attacking force
     * @return attack value which is always bigger than 1
     */
    int computeAttackingForce(Role role);

}
