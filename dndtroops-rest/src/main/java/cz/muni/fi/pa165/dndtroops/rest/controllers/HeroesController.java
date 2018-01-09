/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.dndtroops.rest.controllers;

import cz.muni.fi.pa165.dndtroops.dto.HeroCreateDTO;
import cz.muni.fi.pa165.dndtroops.dto.HeroDTO;
import cz.muni.fi.pa165.dndtroops.dto.RoleDTO;
import cz.muni.fi.pa165.dndtroops.dto.TroopDTO;
import cz.muni.fi.pa165.dndtroops.facade.HeroFacade;
import cz.muni.fi.pa165.dndtroops.facade.RoleFacade;
import cz.muni.fi.pa165.dndtroops.facade.TroopFacade;
import cz.muni.fi.pa165.dndtroops.rest.ApiUris;
import cz.muni.fi.pa165.dndtroops.rest.exceptions.InvalidParameterException;
import cz.muni.fi.pa165.dndtroops.rest.exceptions.ResourceAlreadyExistingException;
import cz.muni.fi.pa165.dndtroops.rest.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

/**
 *
 * @author Martin Sestak
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_HEROES)
public class HeroesController {
    private static final Logger logger = LoggerFactory.getLogger(HeroesController.class);
    @Inject
    private HeroFacade heroFacade;
    @Inject
    private RoleFacade roleFacade;
    @Inject
    private TroopFacade troopFacade;
    
    @RequestMapping(value = "/createHero", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final HeroDTO createHero(@RequestBody HeroCreateDTO hero) {
        logger.debug("REST createHero()");

        try {
            return heroFacade.createHero(hero);
        } catch (Exception ex) {
            throw new ResourceAlreadyExistingException();
        }
    }
    
    @RequestMapping(value = "/deleteHero/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteHero(@PathVariable("id") long id) {
        logger.debug("REST deleteHero({})", id);

        try {
            heroFacade.deleteHero(heroFacade.getHeroById(id));
        } catch (Exception ex) {
            logger.warn(ex.getMessage());
            throw  new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/editHero/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void editHero(@PathVariable("id") long id, @RequestBody HeroDTO hero) {
        logger.debug("REST editRole()");

        if (id != hero.getId()) {
            throw new InvalidParameterException();
        }
        try {
            heroFacade.updateHero(hero);
        } catch(Exception ex) {
            logger.warn(ex.getMessage());
            throw new ResourceNotFoundException();
        }
    }
    
    @RequestMapping(value = "/changeTroop/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void changeTroop(@PathVariable("id") long id, @RequestBody HeroDTO hero) {
        logger.debug("REST changeTroop({})",id);
        
        TroopDTO troop = troopFacade.findTroopById(id);
        if (troop == null) {
            throw new ResourceNotFoundException();
        }
        
        try {
            heroFacade.changeTroop(hero,troop);
        } catch(Exception ex) {
            logger.warn(ex.getMessage());
            throw new ResourceNotFoundException();
        }
    }
    
    @RequestMapping(value = "/addRole/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void addRole(@PathVariable("id") long id, @RequestBody HeroDTO hero) {
        logger.debug("REST addRole({})",id);
        
        RoleDTO role = roleFacade.findById(id);
        if (role == null) {
            throw new InvalidParameterException();
        }
        try {
            heroFacade.addRole(hero,role);
        } catch(Exception ex) {
            logger.warn(ex.getMessage());
            throw new ResourceNotFoundException();
        }
    }
    @RequestMapping(value = "/deleteRole/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteRole(@PathVariable("id") long id, @RequestBody HeroDTO hero) {
        logger.debug("REST deleteRole({})",id);
        
        RoleDTO role = roleFacade.findById(id);
        if (role == null) {
            throw new InvalidParameterException();
        }
        try {
            heroFacade.removeRole(hero,role);
        } catch(Exception ex) {
            logger.warn(ex.getMessage());
            throw new ResourceNotFoundException();
        }
    }
    @RequestMapping(value = "/changeXp/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void changeXp(@RequestParam(value = "xp", required = true) int xp, @RequestBody HeroDTO hero) {
        logger.debug("REST changeXp({})",xp);
        try {
            heroFacade.changeXp(hero, xp);
        } catch(Exception ex) {
            logger.warn(ex.getMessage());
            throw new ResourceNotFoundException();
        }
    }
    
     
    @RequestMapping(value = "/getHero/{id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final HeroDTO getHero(@PathVariable("id") long id) {
        logger.debug("REST getHero({})", id);
        HeroDTO heroDTO = heroFacade.getHeroById(id);
        if (heroDTO == null) {
            throw new ResourceNotFoundException();
        }
        return heroDTO;
    }
    
    @RequestMapping(value = "/getHero/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final HeroDTO getHero(@RequestParam(value = "name", required = true) String name) {
        logger.debug("REST getHero({})", name);
        HeroDTO heroDTO = heroFacade.getHeroByName(name);
        if (heroDTO == null) {
            throw new ResourceNotFoundException();
        }
        return heroDTO;
    }
    
    @RequestMapping(value = "/getAllHeroes",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<HeroDTO> getAllHeroes(){
        logger.debug("REST getAllHeroes()");     
        return heroFacade.getAllHeroes();
    }
    
    
    
    @RequestMapping(value = "/getHeroesByRole/{id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<HeroDTO> getHeroesByRole(@PathVariable("id") long id) {
        logger.debug("REST getHeroesByRole({})", id);
        List<HeroDTO> heroesDTO = heroFacade.getHeroesByRole(roleFacade.findById(id));
        if (heroesDTO.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return heroesDTO;
    }
    
    @RequestMapping(value = "/getHeroesByTroop/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<HeroDTO> getHeroesByTroop(@PathVariable("id") long id) {
        logger.debug("REST getHeroesByTroop({})", id);
        List<HeroDTO> heroesDTO = heroFacade.getHeroesByTroop(troopFacade.findTroopById(id));
        if (heroesDTO.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return heroesDTO;
    }
    
    @RequestMapping(value = "/getHeroesByXp/",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<HeroDTO> getHeroesByXp(@RequestParam(value = "xp") int xp) {
        logger.debug("REST getHeroesByXp({})", xp);
        List<HeroDTO> heroesDTO = heroFacade.getHeroesByXp(xp);
        if (heroesDTO.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return heroesDTO;
    }
    
}