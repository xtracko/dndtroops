package cz.muni.fi.pa165.dndtroops.service;

import cz.muni.fi.pa165.dndtroops.dao.TroopDao;
import cz.muni.fi.pa165.dndtroops.entities.Hero;
import cz.muni.fi.pa165.dndtroops.entities.Role;
import cz.muni.fi.pa165.dndtroops.entities.Troop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TroopServiceImpl implements TroopService {


    @Autowired
    private TroopDao troopDao;

    @Autowired
    private RoleService roleService;

    @Override
    public void createTroop(Troop t) {

        troopDao.createTroop(t);
    }

    @Override
    public void deleteTroop(Troop t) {
        troopDao.deleteTroop(t);
    }

    @Override
    public void updateTroop(Troop t) {
        troopDao.updateTroop(t);
    }

    @Override
    public Troop findTroopById(Long id) { return troopDao.findTroopById(id); }

    @Override
    public Troop findTroopByName(String name) {
        return troopDao.findTroopByName(name);
    }

    @Override
    public List<Troop> findAllTroops() {
        return troopDao.findAllTroops();
    }

    @Override
    public List<Hero> findHeroesOfTroop( Troop t){
        return troopDao.findHeroesOfTroop(t);
    }

    @Override
    public float computeTroopStrength(Troop t){
        float totalStrength = 0;
        List<Hero> troopHeroes = new ArrayList<>(findHeroesOfTroop(t));

        for (int i =0; i < troopHeroes.size(); i++){
            List<Role> heroRoles = new ArrayList<>(troopHeroes.get(i).getRoleList());
            for (int j = 0; j < heroRoles.size(); j++){
                totalStrength += roleService.computeAttackingForce(heroRoles.get(j)) + troopHeroes.get(i).getXp();
            }
        }

        return totalStrength;
    }

    @Override
    public Troop troopBattle(Troop t1, Troop t2){
        if (computeTroopStrength(t1) > computeTroopStrength(t2)) {
            return t1;
        } else if (computeTroopStrength(t1) < computeTroopStrength(t2)) {
            return t2;
        }
        else return troopBattle(t1,t2);
    }


}
