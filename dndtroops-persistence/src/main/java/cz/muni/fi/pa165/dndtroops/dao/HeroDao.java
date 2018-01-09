package cz.muni.fi.pa165.dndtroops.dao;

import cz.muni.fi.pa165.dndtroops.entities.Hero;
import cz.muni.fi.pa165.dndtroops.entities.Role;
import cz.muni.fi.pa165.dndtroops.entities.Troop;

import java.util.List;

/**
 * @author Martin Sestak
 *
 * Data Access Object for Hero entity.
 */
public interface HeroDao {

    /**
     * Persist a hero.
     *
     * @param hero a Hero to persist
     */
    public void createHero(Hero hero);
    
    /**
      * Update persisted Hero.
      *
      * @param hero a persisted Hero to updateTroop
     */
    public Hero updateHero(Hero hero);
 
    /**
     * Delete persisted Hero.
     *
     * @param hero a persisted Hero to removeTroop
     */
    public void removeHero(Hero hero);
    
    /**
      * Find persisted Hero by it's unique ID. If no such hero is found null is returned.
      *
      * @param id an ID of a Hero to find
      * @return Herowith the required ID or null if such Hero does not exists
      */
    public Hero findHeroById(Long id);
    
    /**
      * Find persisted Hero by it's name If no heroes were found null is returned
      *
      * @param name of a Hero to find
      * @return Hero with the required name or null if such Hero does not exists
      */
    public Hero findHeroByName(String name);
    
    /**
      * Find list of persisted Heroes by it's role. If no heroes were found empty list is returned
      *
      * @param role role of a Hero to find
      * @return List of all persisted Heroes with required role or empty list if no heroes were found
      */
    public List<Hero> findHeroesByRole(Role role);
    
    /**
      * Find list of persisted Heroes by it's troop. If no heroes were found empty list is returned
      *
      * @param troop an troop of a Heroes to find
      * @return List of all persisted Heroes with required troop or empty list if no heroes were found
      */
    public List<Hero> findHeroesByTroop(Troop troop);

    /**
      * Find list of persisted Heroes by it's xp. If no heroes were found empty list is returned
      *
      * @param xp  xp of a Heroes to find
      * @return List of all persisted Heroes with required xp or empty list if no heroes were found
      */
    public List<Hero> findHeroesByXp(int xp);
    
    /**
      * Find all persisted Heroes and return them as a List. If no heroes were found empty list is returned
      *
      * @return List of all persisted Heroes
      */
    public List<Hero> findAllHeroes();

}
