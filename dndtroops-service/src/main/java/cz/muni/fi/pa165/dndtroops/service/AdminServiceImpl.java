package cz.muni.fi.pa165.dndtroops.service;

import cz.muni.fi.pa165.dndtroops.dao.AdministratorDao;
import cz.muni.fi.pa165.dndtroops.entities.Administrator;
import cz.muni.fi.pa165.dndtroops.entities.Hero;
import cz.muni.fi.pa165.dndtroops.entities.Troop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Miroslav Macor
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdministratorDao administratorDao;

    @Autowired
    private HeroService heroService;

    @Override
    public void createAdministrator(Administrator admin) {
        administratorDao.createAdministrator(admin);
    }

    @Override
    public void updateAdministrator(Administrator admin) {
        administratorDao.updateAdministrator(admin);
    }

    @Override
    public Administrator findAdministatorById(Long id) { return administratorDao.findAdministatorById(id); }

    @Override
    public Administrator findAdministratorByName(String name) { return administratorDao.findAdministratorByName(name);
    }

    @Override
    public List<Administrator> findAllAdministrators() {return administratorDao.findAllAdministrators();}

    @Override
    public void removeAdministrator(Administrator admin) {administratorDao.removeAdministrator(admin);}

    @Override
    public Troop complateMissionForTroop(Troop troop, int xpGained, int goldGained, String newMission){
        List<Hero> heroes = heroService.getHeroesByTroop(troop);
        for(Hero hero: heroes){
            hero.setXp(hero.getXp() + xpGained / heroes.size());
        }
        troop.setGoldenMoney(troop.getGoldenMoney() + goldGained);
        troop.setMission(newMission);
        return troop;
    }

    @Override
    public Troop createNewParty(List<Hero> heroes, String mission, String name, int pulledGold){
        Troop troop =  new Troop(name,mission,pulledGold);
        for (Hero hero: heroes){
            hero.setTroop(troop);
            hero.setHealth(100);
        }
        return troop;
    }
}
