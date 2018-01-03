package cz.muni.fi.pa165.dndtroops.service.battle;

import cz.muni.fi.pa165.dndtroops.entities.Hero;
import cz.muni.fi.pa165.dndtroops.entities.Role;

import java.util.ArrayList;
import java.util.List;

public class HeroState {
    public final Hero hero;
    private int healthState;
    private List<RoleState> roleStates;

    public HeroState(Hero hero) {
        this.hero = hero;
        this.healthState = hero.getHealth();
        this.roleStates = new ArrayList<>(hero.getRoles().size());

        for (Role role : hero.getRoles()) {
            roleStates.add(new RoleState(role));
        }
    }

    public boolean isAlive() {
        return healthState > 0;
    }

    public void wound(int damage) {
        healthState = (healthState - damage > 0) ? (healthState - damage) : 0;
    }

    public List<RoleState> getRoleStates() {
        return roleStates;
    }
}
