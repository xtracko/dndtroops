package cz.muni.fi.pa165.dndtroops.facade;


import cz.muni.fi.pa165.dndtroops.dto.HeroCreateDTO;
import cz.muni.fi.pa165.dndtroops.dto.HeroDTO;
import cz.muni.fi.pa165.dndtroops.dto.RoleDTO;
import cz.muni.fi.pa165.dndtroops.dto.TroopDTO;
import cz.muni.fi.pa165.dndtroops.entities.Hero;
import cz.muni.fi.pa165.dndtroops.entities.Role;
import cz.muni.fi.pa165.dndtroops.entities.Troop;
import cz.muni.fi.pa165.dndtroops.service.BeanMappingService;
import cz.muni.fi.pa165.dndtroops.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Martin Sestak
 */
@Service
@Transactional
public class HeroFacadeImpl implements HeroFacade {

    @Autowired
    private HeroService heroService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public HeroDTO createHero(HeroCreateDTO hero) {
        Hero mapped = beanMappingService.mapTo(hero, Hero.class);
        heroService.createHero(mapped);

        return beanMappingService.mapTo(mapped, HeroDTO.class);
    }

    @Override
    public void updateHero(HeroDTO hero) {
        heroService.updateHero(beanMappingService.mapTo(hero, Hero.class));
    }

    @Override
    public void deleteHero(HeroDTO hero) {
        heroService.deleteHero(heroService.getHeroById(hero.getId()));
    }

    @Override
    public HeroDTO getHeroById(Long heroId) {
        return beanMappingService.mapTo(heroService.getHeroById(heroId), HeroDTO.class);
    }

    @Override
    public HeroDTO getHeroByName(String name) {
        return beanMappingService.mapTo(heroService.getHeroByName(name), HeroDTO.class);
    }

    @Override
    public List<HeroDTO> getHeroesByRole(RoleDTO role) {
        Role mapped = beanMappingService.mapTo(role, Role.class);
        return beanMappingService.mapTo(heroService.getHeroesByRole(mapped), HeroDTO.class);
    }

    @Override
    public List<HeroDTO> getHeroesByTroop(TroopDTO troop) {
        Troop mapped = beanMappingService.mapTo(troop, Troop.class);
        return beanMappingService.mapTo(heroService.getHeroesByTroop(mapped), HeroDTO.class);
    }

    @Override
    public List<HeroDTO> getHeroesByXp(int xp) {
        return beanMappingService.mapTo(heroService.getHeroesByXp(xp), HeroDTO.class);
    }

    @Override
    public List<HeroDTO> getAllHeroes() {
        return beanMappingService.mapTo(heroService.getAllHeroes(), HeroDTO.class);
    }


    @Override
    public void changeTroop(HeroDTO hero, TroopDTO troop) {
        heroService.changeTroop(beanMappingService.mapTo(hero, Hero.class), beanMappingService.mapTo(troop, Troop.class));
    }

    @Override
    public void addRole(HeroDTO hero, RoleDTO role) {
        heroService.addRole(beanMappingService.mapTo(hero, Hero.class), beanMappingService.mapTo(role, Role.class));
    }

    @Override
    public void removeRole(HeroDTO hero, RoleDTO role) {
        heroService.removeRole(beanMappingService.mapTo(hero, Hero.class), beanMappingService.mapTo(role, Role.class));
    }

    @Override
    public void changeXp(HeroDTO hero, Integer xp) {
        heroService.changeXp(beanMappingService.mapTo(hero, Hero.class), xp);
    }

}
