package cz.muni.fi.pa165.dndtroops.sampledata;

import cz.muni.fi.pa165.dndtroops.entities.Role;
import cz.muni.fi.pa165.dndtroops.enums.Power;
import cz.muni.fi.pa165.dndtroops.service.RoleService;
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

    @Override
    @SuppressWarnings("unused")
    public void loadData() throws IOException {
        Role assassin = role("Assassin", "Attack Damage Carry", Power.MARTIAL_ARTS, 40, 30);
        Role marksman = role("Marksman", "Attack Damage Carry", Power.WEAPONS, 50, 10);
        Role tank = role("Tank", "Has tanking powers", Power.WEAPONS, 15, 20);

        log.info("Roles loaded.");
    }

    private Role role(String name, String description, Power power, float dmgMean, float dmgVariance) {
        Role role = new Role(name, description, power, dmgMean, dmgVariance);

        roleService.createRole(role);
        return role;
    }
}
