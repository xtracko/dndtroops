package cz.muni.fi.pa165.dndtroops.facade;

import cz.muni.fi.pa165.dndtroops.dto.CreateRoleDTO;
import cz.muni.fi.pa165.dndtroops.dto.RoleDTO;
import cz.muni.fi.pa165.dndtroops.enums.Power;

import javax.management.relation.Role;
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
     * @param id
     */
     void deleteRole(long id);

    /**
     * Edit Role
     *
     * @param role a Role to edit
     */
    RoleDTO editRole(RoleDTO role);

    /**
     * Find role by its ID
     *
     * @param id of a Role
     * @return roleDTO
     */
    RoleDTO findById(long id);
    
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

    /**
     * Compute attacking force based on random attempt
     *
     * @param role a Role for which to compute random attacking force
     * @return attack value
     */
    int computeAttackingForce(RoleDTO role);
}
