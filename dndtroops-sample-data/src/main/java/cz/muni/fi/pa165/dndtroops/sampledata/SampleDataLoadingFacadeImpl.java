package cz.muni.fi.pa165.dndtroops.sampledata;

import cz.muni.fi.pa165.dndtroops.entities.Role;
import cz.muni.fi.pa165.dndtroops.entities.Troop;
import cz.muni.fi.pa165.dndtroops.enums.Power;
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

    @Override
    @SuppressWarnings("unused")
    public void loadData() throws IOException {
        loadRoles();
        loadTroops();
    }

    @SuppressWarnings("unused")
    private void loadRoles() throws IOException {
        Role assassin = role("Assassin", "Attack Damage Carry", Power.MARTIAL_ARTS, 40, 7);
        Role marksman = role("Marksman", "Attack Damage Carry", Power.WEAPONS, 50, 2);
        Role tank = role("Tank", "Has tanking powers", Power.WEAPONS, 15, 10);

        log.info("Roles loaded.");
    }

    @SuppressWarnings("unused")
    private void loadTroops() throws IOException {
        Troop heroes = troop("Heroes", "Save the queen", 541);
        Troop peasants = troop("Peasants", "Save the king", 157);

        log.info("Troops loaded.");
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
}
