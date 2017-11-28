package cz.muni.fi.pa165.dndtroops.service;

import cz.muni.fi.pa165.dndtroops.entities.Hero;
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
     * @param role a Role to remove
     */
    void removeRole(Role role);

    /**
     * Edit Role data
     *
     * @param role a Role to edit
     */
    Role editRole(Role role);

    /**
     * Find Role by an ID
     *
     * @param id ID to find
     * @return Role with ID 'id'
     */
    Role findRoleById(Long id);

    /**
     * List all Roles
     *
     * @return all existing Roles
     */
    List<Role> getAllRoles();

    /**
     * List all Roles which have some specific Power
     *
     * @param power a Power of a Roles to find
     * @return list of Roles
     */
    List<Role> getAllRolesByPower(Power power);

    /**
     * Compute attacking force based on random attempt
     *
     * @param role a Role for which to compute random attacking force
     * @return attack value
     */
    float computeAttackingForce(Role role);

}
