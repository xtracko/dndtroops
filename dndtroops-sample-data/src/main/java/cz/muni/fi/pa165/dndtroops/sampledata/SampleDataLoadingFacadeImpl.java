package cz.muni.fi.pa165.dndtroops.sampledata;

import cz.muni.fi.pa165.dndtroops.entities.Administrator;
import cz.muni.fi.pa165.dndtroops.entities.Hero;
import cz.muni.fi.pa165.dndtroops.entities.Role;
import cz.muni.fi.pa165.dndtroops.entities.Troop;
import cz.muni.fi.pa165.dndtroops.enums.Power;
import cz.muni.fi.pa165.dndtroops.service.AdminService;
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
    @Autowired
    private AdminService adminService;
    
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
        loadUsers();
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
        Hero batman = hero("Batman", heroes, 12, marksman);
        Hero superman = hero("Superman", heroes, 5, tank);
        Hero joker = hero("Joker", peasants, 10, tank, assassin);
        
        log.info("Heroes loaded.");
    }
    @SuppressWarnings("unused")
    private void loadUsers() throws IOException {    
        Administrator admin = admin("admin", "admin", true);
        Administrator user = admin("user", "user", false);
        
        log.info("Users loaded.");
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
    
    private Hero hero(String name, Troop troop, int xp, Role... roles){
        Hero hero = new Hero(name, troop, 100, xp, roles);
        heroService.createHero(hero);
        return hero;
    }
    private Administrator admin(String name, String password, boolean isAdmin){
        Administrator admin = new Administrator(name);
        admin.setIsAdmin(isAdmin);
        
        adminService.createAdministrator(admin, password);
        return admin;
    }
}
