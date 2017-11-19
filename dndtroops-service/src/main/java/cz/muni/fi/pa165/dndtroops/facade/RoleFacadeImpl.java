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
    public void removeRole(RoleDTO role) {
        roleService.removeRole(beanMappingService.mapTo(role, Role.class));
    }

    @Override
    public void editRole(RoleDTO role) {
        roleService.editRole(beanMappingService.mapTo(role, Role.class));
    }

    @Override
    public List<RoleDTO> getAllRoles() {
        return beanMappingService.mapTo(roleService.getAllRoles(), RoleDTO.class);
    }

    @Override
    public List<RoleDTO> getAllRolesByPower(Power power) {
        return beanMappingService.mapTo(roleService.getAllRolesByPower(power), RoleDTO.class);
    }

}
