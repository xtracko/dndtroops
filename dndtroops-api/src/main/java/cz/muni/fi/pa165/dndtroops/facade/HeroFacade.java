package cz.muni.fi.pa165.dndtroops.facade;

import cz.muni.fi.pa165.dndtroops.dto.HeroCreateDTO; 
import cz.muni.fi.pa165.dndtroops.dto.HeroDTO; 
import cz.muni.fi.pa165.dndtroops.dto.RoleDTO;
import cz.muni.fi.pa165.dndtroops.dto.TroopDTO;

import java.util.List;

/**
 *
 * @author Martin Sestak
 */
public interface HeroFacade {   
    /**
     * Persist a hero.
     *
     * @param h - hero a HeroDTO to persist
     * @return id of created hero
     */
    public HeroDTO createHero(HeroCreateDTO h);
    
    /**
      * Update persisted Hero.
      *
      * @param hero updated hero
     */
    public void updateHero(HeroDTO hero);
 
    /**
     * delete persisted Hero.
     *
      * @param hero persisted hero
     */
    public void deleteHero(HeroDTO hero);
    
    /**
     * Change troop of Hero.
     *
     * @param hero persisted hero
     * @param troop - troop of hero
     */
    public void changeTroop(HeroDTO hero,TroopDTO troop);
    
    /**
     * Add role to the Hero.
     *
     * @param hero persisted hero
     * @param role - role of hero
     */
    public void addRole(HeroDTO hero,RoleDTO role);
    /**
     * Remove role to the Hero.
     *
     * @param hero persisted hero
     * @param role - role of hero
     */
    public void removeRole(HeroDTO hero, RoleDTO role);
    
    /**
     * CHange xp of Hero. 
     * 
     * @param hero persisted hero
     * @param xp - xp of the hero to be set
     */
    public void changeXp(HeroDTO hero, Integer xp);
    
    /**
      * Find persisted HeroDTO by it's unique ID. If no such hero is found null is returned.
      *
      * @param heroId an ID of a HeroDTO to get
      * @return Herowith the required ID or null if such HeroDTO does not exists
      */
    public HeroDTO getHeroById(Long heroId);
    
    /**
      * Find persisted HeroDTO by it's name If no heroes were found null is returned
      *
      * @param name of a HeroDTO to get
      * @return HeroDTO with the required name or null if such HeroDTO does not exists
      */
    public HeroDTO getHeroByName(String name);
    
    /**
      * Find list of persisted Heroes by it's role. If no heroes were found null is returned
      *
      * @param role - role of hero
      * @return List of all persisted Heroes with required role or null if no heroes were found
      */
    public List<HeroDTO> getHeroesByRole(RoleDTO role);
    
    /**
      * Find list of persisted Heroes by it's troop. If no heroes were found null is returned
      *
      * @param troop - troop of hero
      * @return List of all persisted Heroes with required troop or null if no heroes were found
      */
    public List<HeroDTO> getHeroesByTroop(TroopDTO troop);
    
    /**
      * Find list of persisted Heroes by it's xp. If no heroes were found null is returned
      *
      * @param xp  xp of a Heroes to get
      * @return List of all persisted Heroes with required xp or null if no heroes were found
      */
    public List<HeroDTO> getHeroesByXp(int xp);
    
    /**
      * Find all persisted Heroes and return them as a List. If no heroes were found null is returned
      *
      * @return List of all persisted Heroes
      */
    public List<HeroDTO> getAllHeroes();

}
