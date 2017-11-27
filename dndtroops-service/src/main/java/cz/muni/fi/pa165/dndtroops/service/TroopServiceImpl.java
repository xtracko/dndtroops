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
    public List<Troop> listWealthiestTroops(){

        return null;
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
            List<Role> heroeRoles = new ArrayList<>(troopHeroes.get(i).getRoleList());
            for (int j = 0; j < heroeRoles.size(); j++){
                totalStrength += roleService.computeAttackingForce(heroeRoles.get(j)) + troopHeroes.get(i).getXp();
            }
        }

        return totalStrength;
    }


}
