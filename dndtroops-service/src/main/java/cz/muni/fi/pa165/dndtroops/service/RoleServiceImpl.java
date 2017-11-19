package cz.muni.fi.pa165.dndtroops.service;

import cz.muni.fi.pa165.dndtroops.dao.HeroDao;
import cz.muni.fi.pa165.dndtroops.dao.RoleDao;
import cz.muni.fi.pa165.dndtroops.entities.Role;
import cz.muni.fi.pa165.dndtroops.enums.Power;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jiří Novotný
 *
 * Note: I would implement second non-trivial service method as stated in milestone 2 assignment,
 * but I can't think of anything which is related to Role entity from D&D Troops project assignment.
 */

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private HeroDao heroDao;

    @Override
    public void createRole(Role role) {
        roleDao.createRole(role);
    }

    @Override
    public void removeRole(Role role) {
        roleDao.deleteRole(role);
        heroDao.findHeroesByRole(role).forEach(hero -> {
            hero.removeRole(role);
        });
    }

    @Override
    public void editRole(Role role) {
        roleDao.updateRole(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDao.findAllRoles();
    }

    @Override
    public List<Role> getAllRolesByPower(Power power) {
        return roleDao.findAllRolesByPower(power);
    }
    
}
