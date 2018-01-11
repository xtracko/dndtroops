package cz.muni.fi.pa165.dndtroops.service;

import cz.muni.fi.pa165.dndtroops.dao.HeroDao;
import cz.muni.fi.pa165.dndtroops.dao.RoleDao;
import cz.muni.fi.pa165.dndtroops.entities.Role;
import cz.muni.fi.pa165.dndtroops.enums.Power;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 
 * @author Jiří Novotný
 */

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Autowired
    private HeroDao heroDao;

    @Autowired
    private RandomService randomService;

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
    public Role updateRole(Role role) {
        return roleDao.updateRole(role);
    }

    @Override
    public Role findRoleById(long id) {
       return roleDao.findRoleById(id);
    }

    @Override
    public List<Role> findAllRoles() {
        return roleDao.findAllRoles();
    }

    @Override
    public List<Role> findRolesByPower(Power power) {
        return roleDao.findAllRolesByPower(power);
    }

    @Override
    public int computeAttackingForce(Role role) {
        // Note: the most difficult service method I could think off :(

        double mean = role.getDamage();
        double variance = .1 * role.getDamage();

        int attack = (int) (randomService.nextNormal() * variance + mean);
        return (attack > 1) ? attack : 1;
    }
}
