package cz.muni.fi.pa165.dndtroops.service;

import cz.muni.fi.pa165.dndtroops.dao.HeroDao;
import cz.muni.fi.pa165.dndtroops.entities.Hero;
import cz.muni.fi.pa165.dndtroops.entities.Role;
import cz.muni.fi.pa165.dndtroops.entities.Troop;
import cz.muni.fi.pa165.dndtroops.service.battle.HeroState;
import cz.muni.fi.pa165.dndtroops.service.battle.RoleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Martin Sestak and Jiří Novotný (changes to the non-trivial bussiness functionality)
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
    public Hero changeTroop(Hero hero, Troop troop) {
        hero.setTroop(troop);
        return heroDao.updateHero(hero);
    }

    @Override
    public void addRole(Hero hero, Role role) {
        hero.addRole(role);
        heroDao.updateHero(hero);
    }

    @Override
    public Hero removeRole(Hero hero, Role role) {
        hero.removeRole(role);
        return heroDao.updateHero(hero);
    }

    @Override
    public Hero changeXp(Hero hero, int xp) {
        hero.setXp(xp);
        return heroDao.updateHero(hero);
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
    public void fight(HeroState a, HeroState b) {
        if (!a.isAlive() || !b.isAlive())
            return;

        while (a.isAlive() && b.isAlive()) {
            b.wound(chooseAttack(a));
            a.wound(chooseAttack(b));
        }

        if (a.isAlive()) {
            learnRandomRoleOfAnotherHero(a.hero, b.hero);
        }

        if (b.isAlive()) {
            learnRandomRoleOfAnotherHero(b.hero, a.hero);
        }
    }

    private int chooseAttack(HeroState hero) {
        randomService.shuffle(hero.getRoleStates());

        for (RoleState roleState : hero.getRoleStates()) {
            roleState.decrementCooldown();
        }

        for (RoleState roleState : hero.getRoleStates()) {
            if (roleState.isCooldownActive()) {
                roleState.resetCooldown();
                return roleService.computeAttackingForce(roleState.role);
            }
        }

        return 1;
    }

    private void learnRandomRoleOfAnotherHero(Hero hero, Hero teacher) {
        List<Role> teacherRoles = teacher.getRoles();

        if (teacherRoles.isEmpty())
            return;

        int random = randomService.nextInt(teacherRoles.size());
        Role role = teacherRoles.get(random);

        hero.addRole(role);
        heroDao.updateHero(hero);
    }
}
