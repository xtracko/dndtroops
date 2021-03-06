package cz.muni.fi.pa165.dndtroops.facade;

import cz.muni.fi.pa165.dndtroops.dto.CreateRoleDTO;
import cz.muni.fi.pa165.dndtroops.dto.RoleDTO;
import cz.muni.fi.pa165.dndtroops.entities.Role;
import cz.muni.fi.pa165.dndtroops.enums.Power;
import cz.muni.fi.pa165.dndtroops.service.BeanMappingService;
import cz.muni.fi.pa165.dndtroops.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Jiří Novotný
 */

@Service
@Transactional
public class RoleFacadeImpl implements RoleFacade {

    @Autowired
    private RoleService roleService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public RoleDTO createRole(CreateRoleDTO role) {
        Role mapped = beanMappingService.mapTo(role, Role.class);
        roleService.createRole(mapped);
        return beanMappingService.mapTo(mapped, RoleDTO.class);
    }

    @Override
    public void removeRole(long id) {
        roleService.removeRole(roleService.findRoleById(id));
    }

    @Override
    public RoleDTO updateRole(RoleDTO role) {
        return beanMappingService.mapTo(roleService.updateRole(beanMappingService.mapTo(role, Role.class)), RoleDTO.class);
    }
    
    @Override
    public RoleDTO findById(long id) {
        return beanMappingService.mapTo(roleService.findRoleById(id), RoleDTO.class);
    }

    @Override
    public List<RoleDTO> findAllRoles() {
        return beanMappingService.mapTo(roleService.findAllRoles(), RoleDTO.class);
    }

    @Override
    public List<RoleDTO> findAllRolesByPower(Power power) {
        return beanMappingService.mapTo(roleService.findRolesByPower(power), RoleDTO.class);
    }

    @Override
    public int computeAttackingForce(RoleDTO role) {
        return roleService.computeAttackingForce(beanMappingService.mapTo(role, Role.class));
    }
}
