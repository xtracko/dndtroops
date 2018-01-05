package cz.muni.fi.pa165.dndtroops.sampledata;

import cz.muni.fi.pa165.dndtroops.entities.Hero;
import cz.muni.fi.pa165.dndtroops.entities.Role;
import cz.muni.fi.pa165.dndtroops.entities.Troop;
import cz.muni.fi.pa165.dndtroops.enums.Power;
import cz.muni.fi.pa165.dndtroops.service.HeroService;
import cz.muni.fi.pa165.dndtroops.service.RoleService;
import cz.muni.fi.pa165.dndtroops.service.TroopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * @author Jiří Novotný
 */

@Component
@Transactional
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {
    private static final Logger log = LoggerFactory.getLogger(SampleDataLoadingFacadeImpl.class);

    @Autowired
    private RoleService roleService;

    @Autowired
    private TroopService troopService;
    
    @Autowired
    private HeroService heroService;
    
    Role assassin;
    Role marksman;
    Role tank;
    
    Troop heroes;
    Troop peasants;
    

    @Override
    @SuppressWarnings("unused")
    public void loadData() throws IOException {
        loadRoles();
        loadTroops();
        loadHeroes();
    }

    @SuppressWarnings("unused")
    private void loadRoles() throws IOException {
        assassin = role("Assassin", "Attack Damage Carry", Power.MARTIAL_ARTS, 40, 7);
        marksman = role("Marksman", "Attack Damage Carry", Power.WEAPONS, 50, 2);
        tank = role("Tank", "Has tanking powers", Power.WEAPONS, 15, 10);

        log.info("Roles loaded.");
    }

    @SuppressWarnings("unused")
    private void loadTroops() throws IOException {
        heroes = troop("Heroes", "Save the queen", 541);
        peasants = troop("Peasants", "Save the king", 157);

        log.info("Troops loaded.");
    }
    
    @SuppressWarnings("unused")
    private void loadHeroes() throws IOException {    
        Hero batman = hero("Batman", marksman, heroes, 12);
        Hero superman = hero("Superman", tank, heroes, 5);
        Hero joker = hero("Joker", tank, peasants, 10);
        
        log.info("Heroes loaded.");
    }

    private Role role(String name, String description, Power power, int damage, int cooldown) {
        Role role = new Role(name, description, power, damage, cooldown);

        roleService.createRole(role);
        return role;
    }

    private Troop troop(String name, String mission, long money) {
        Troop troop = new Troop();
        troop.setName(name);
        troop.setMission(mission);
        troop.setGoldenMoney(money);

        troopService.createTroop(troop);
        return troop;
    }
    
    private Hero hero(String name, Role role, Troop troop,int xp){
        Hero hero = new Hero();
        hero.setName(name);
        hero.addRole(role);
        hero.setTroop(troop);
        hero.setXp(xp);
        hero.setHealth(100);  
    
        heroService.createHero(hero);
    return hero;
    }
}
