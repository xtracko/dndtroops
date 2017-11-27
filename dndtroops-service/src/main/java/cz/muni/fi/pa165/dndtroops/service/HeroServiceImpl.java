package cz.muni.fi.pa165.dndtroops.service;

import cz.muni.fi.pa165.dndtroops.dao.HeroDao;
import cz.muni.fi.pa165.dndtroops.entities.Hero;
import cz.muni.fi.pa165.dndtroops.entities.Role;
import cz.muni.fi.pa165.dndtroops.entities.Troop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Martin Sestak
 */
@Service
@Transactional
public class HeroServiceImpl implements HeroService {

    @Autowired
    private HeroDao heroDao;

    @Autowired
    private RandomService randomService;

    @Autowired
    private RoleService roleService;

    @Override
    public Long createHero(Hero hero) {
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
        hero.addRole(role);
        heroDao.updateHero(hero);
    }

    @Override
    public void removeRole(Hero hero, Role role) {
        hero.removeRole(role);
        heroDao.updateHero(hero);
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
    public boolean attackHero(Hero attacker, Hero victim, Role role) {
        float dmg = roleService.computeAttackingForce(role) * attacker.getXp() / victim.getXp();

        if (!attacker.isCooldown() && victim.getHealth() > 0 && attacker.getHealth() > 0) {
            attacker.setCooldown(true);
            this.defendHero(victim, dmg);

            heroDao.updateHero(attacker);
            return true;
        }
        return false;
    }

    @Override
    public void defendHero(Hero victim, float damage) {
        if (randomService.nextBoolean(0.1f))
            damage = 0; // it's a miss

        int health = victim.getHealth() - Math.round(damage);
        victim.setHealth(health > 0 ? health : 0);
        victim.setCooldown(false);

        heroDao.updateHero(victim);
    }

}
