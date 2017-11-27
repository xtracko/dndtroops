package cz.muni.fi.pa165.dndtroops.service;


import cz.muni.fi.pa165.dndtroops.entities.Troop;
import cz.muni.fi.pa165.dndtroops.entities.Hero;
import cz.muni.fi.pa165.dndtroops.entities.Role;
import java.util.List;

/**
 *
 * @author Martin Sestak
 */
public interface HeroService {
    /**
     * Create a hero.
     *
     * @param hero
     * @return id of created hero
     */
    public Long createHero(Hero hero);
    
    /**
      * Update persisted Hero.
      *
      * @param hero persisted hero
     */
    public void updateHero(Hero hero);
 
    /**
     * deletee persisted Hero.
     *
     * @param hero persisted hero
     */
    public void deleteHero(Hero hero);
    
    /**
     * Attack Hero.
     *
     * @param attacker -hero who attacks
     * @param victim - hero who is vicitm
     * @param role- role whick will be selected for attack
     * @return true if attack was sucessfull false if aqttack was unsucessful
     */
    public boolean attackHero(Hero attacker,Hero victim, Role role);
    
    /**
     * Attack Hero.
     *
     * @param hero persisted hero
     * @param damage - amount of damage of attack
     */
    public void defendHero(Hero hero,float damage);
    
    /**
     * Change troop of Hero.
     *
     * @param hero persisted hero
     * @param troop - heros troop
     */
    public void changeTroop(Hero hero, Troop troop);
    
    /**
     * Add role to the Hero.
     *
     * @param hero persisted hero
     * @param role - role of hero
     */
    public void addRole(Hero hero, Role role);
    /**
     * Add role to the Hero.
     *
     * @param hero persisted hero
     * @param role - role of hero
     */
    public void removeRole(Hero hero, Role role);
    
    /**
     * CHange xp of Hero. 
     * 
     * @param hero persisted hero
     * @param xp - xp of the hero to be set
     */
    public void changeXp(Hero hero, Integer xp);
    
    /**
      * Find persisted Hero by it's unique ID. If no such hero is found null is returned.
      *
      * @param heroId an ID of a Hero to get
      * @return Herowith the required ID or null if such Hero does not exists
      */
    public Hero getHeroById(Long heroId);
    
    /**
      * Find persisted Hero by it's name If no heroes were found null is returned
      *
      * @param name of a Hero to get
      * @return Hero with the required name or null if such Hero does not exists
      */
    public Hero getHeroByName(String name);
    
    /**
      * Find list of persisted Heroes by it's role. If no heroes were found null is returned
      *
      * @param role- heros role 
      * @return List of all persisted Heroes with required role or null if no heroes were found
      */
    public List<Hero> getHeroesByRole(Role role);
    
    /**
      * Find list of persisted Heroes by it's troop. If no heroes were found null is returned
      *
      * @param troop - heros troop
      * @return List of all persisted Heroes with required troop or null if no heroes were found
      */
    public List<Hero> getHeroesByTroop(Troop troop);
    
    /**
      * Find list of persisted Heroes by it's xp. If no heroes were found null is returned
      *
      * @param xp  xp of a Heroes to get
      * @return List of all persisted Heroes with required xp or null if no heroes were found
      */
    public List<Hero> getHeroesByXp(int xp);
    
    /**
      * Find all persisted Heroes and return them as a List. If no heroes were found null is returned
      *
      * @return List of all persisted Heroes
      */
    public List<Hero> getAllHeroes();
    
}
