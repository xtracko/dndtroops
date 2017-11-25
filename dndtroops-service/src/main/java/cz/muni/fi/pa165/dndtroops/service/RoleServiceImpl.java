package cz.muni.fi.pa165.dndtroops.service;

import cz.muni.fi.pa165.dndtroops.dao.HeroDao;
import cz.muni.fi.pa165.dndtroops.dao.RoleDao;
import cz.muni.fi.pa165.dndtroops.entities.Role;
import cz.muni.fi.pa165.dndtroops.enums.Power;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
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

    @Override
    public float computeAttackingForce(Role role) {
        // Note: the most difficult service method I could think off :(
        return randomService.nextNormal() * role.getDamageVariance() + role.getDamageMean();
    }

}
