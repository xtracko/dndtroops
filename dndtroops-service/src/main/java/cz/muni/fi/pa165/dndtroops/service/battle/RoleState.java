package cz.muni.fi.pa165.dndtroops.service.battle;

import cz.muni.fi.pa165.dndtroops.entities.Role;

/**
 * @author Jiří Novotný
 */
public class RoleState {
    public final Role role;
    private int cooldownState;

    public RoleState(Role role) {
        this.role = role;
        this.cooldownState = 0;
    }

    public boolean isCooldownActive() {
        return cooldownState == 0;
    }

    public void resetCooldown() {
        cooldownState = role.getCooldown();
    }

    public void decrementCooldown() {
        cooldownState = (cooldownState > 0) ? (cooldownState - 1) : 0;
    }
}
