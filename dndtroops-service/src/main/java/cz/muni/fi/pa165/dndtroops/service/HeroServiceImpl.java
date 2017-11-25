package cz.muni.fi.pa165.dndtroops.service;

import cz.muni.fi.pa165.dndtroops.dao.HeroDao;
import cz.muni.fi.pa165.dndtroops.dao.TroopDao;
import cz.muni.fi.pa165.dndtroops.entities.Hero;
import cz.muni.fi.pa165.dndtroops.entities.Role;
import cz.muni.fi.pa165.dndtroops.entities.Troop;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Martin Sestak
 */
@Service
@Transactional
public class HeroServiceImpl implements HeroService{

    @Autowired
    private HeroDao heroDao;
    
    @Autowired
    private TroopDao troopDao;
    
    @Override
    public Long createHero(Hero hero){
        if (hero == null) {
            throw new IllegalArgumentException("Hero cannot be null!");
        }
        heroDao.createHero(hero);
        
        return hero.getId(); 
    } 


    @Override
    public void updateHero(Hero hero) {
        if (hero == null) {
            throw new IllegalArgumentException("Hero cannot be null!");
        }
         heroDao.updateHero(hero);
    }

    @Override
    public void deleteHero(Hero hero) {
        if (hero == null) {
            throw new IllegalArgumentException("Hero cannot be null!");
        }
        heroDao.deleteHero(hero);
    }

    @Override
    public void changeTroop(Hero hero, Troop troop) {
        if (hero == null) {
            throw new IllegalArgumentException("Hero cannot be null!");
        }
        if (troop == null) {
            throw new IllegalArgumentException("Troop cannot be null!");
        }
        
        hero.setTroop(troop);
        heroDao.updateHero(hero);
   
    }

    @Override
    public void addRole(Hero hero, Role role) {
        if (hero == null) {
            throw new IllegalArgumentException("Hero cannot be null!");
        }
        if (role == null) {
            throw new IllegalArgumentException("Role cannot be null!");
        }
        
        hero.addRole(role);
        heroDao.updateHero(hero);
    }

    @Override
    public void deleteRole(Hero hero, Role role) {
        if (hero == null) {
            throw new IllegalArgumentException("Hero cannot be null!");
        }
        if (role == null) {
            throw new IllegalArgumentException("Role cannot be null!");
        }
        
        List<Role> roleList = hero.getRoleList();        
        roleList.remove(role);
        hero.setRoleList(roleList);
    }  

    @Override
    public void changeXp(Hero hero, Integer xp) {
        if (hero == null) {
            throw new IllegalArgumentException("Hero cannot be null!");
        }
        if (xp == null) {
            throw new IllegalArgumentException("Xp cannot be null!");
        }
        hero.setXp(xp);
        heroDao.updateHero(hero);
    }

    @Override
    public Hero getHeroById(Long heroId) {
         if (heroId == null) {
            throw new IllegalArgumentException("HeroId cannot be null!");
        }
         return heroDao.findHeroById(heroId);
    }

    @Override
    public Hero getHeroByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null!");
        }
         return heroDao.findHeroByName(name);
    }

    @Override
    public List<Hero> getHeroesByRole(Role role) {
        if (role == null) {
            throw new IllegalArgumentException("Role cannot be null!");
        }
        return heroDao.findHeroesByRole(role);
    }

    @Override
    public List<Hero> getHeroesByTroop(Troop troop) {
       if (troop == null) {
            throw new IllegalArgumentException("Troop cannot be null!");
        } 
       return heroDao.findHeroesByTroop(troop);
    }

    @Override
    public List<Hero> getHeroesByXp(int xp) {
        
       return heroDao.findHeroesByXp(xp);
    }

    @Override
    public List<Hero> getAllHeroes() {
        return heroDao.findAllHeroes();
    }

    @Override
    public void attackHero(Hero hero, Role role) {
        throw new UnsupportedOperationException("TODO Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void defensiveHero(Hero hero, Role role) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
