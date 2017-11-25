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
    void editRole(Role role);

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
     * @return attack value
     */
    float computeAttackingForce(Role role);

}
