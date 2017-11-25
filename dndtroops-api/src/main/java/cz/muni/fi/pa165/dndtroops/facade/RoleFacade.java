package cz.muni.fi.pa165.dndtroops.facade;

import cz.muni.fi.pa165.dndtroops.dto.CreateRoleDTO;
import cz.muni.fi.pa165.dndtroops.dto.RoleDTO;
import cz.muni.fi.pa165.dndtroops.enums.Power;

import java.util.List;

/**
 * @author Jiří Novotný
 */

public interface RoleFacade {

    /**
     * Create Role
     *
     * @param role a Role creation data
     * @return fully initialized Role
     */
    RoleDTO createRole(CreateRoleDTO role);

    /**
     * Remove a Role from the system
     *
     * @param role a Role to romove
     */
    void removeRole(RoleDTO role);

    /**
     * Edit Role
     *
     * @param role a Role to edit
     */
    void editRole(RoleDTO role);

    /**
     * Retrieve all Roles
     *
     * @return all existing Roles
     */
    List<RoleDTO> getAllRoles();

    /**
     * Retrieve all Roles with a specific Power
     *
     * @param power a power of Roles to find by
     * @return all Roles with a specific power
     */
    List<RoleDTO> getAllRolesByPower(Power power);

}