package cz.muni.fi.pa165.dndtroops.facade;

import cz.muni.fi.pa165.dndtroops.dto.HeroDTO;
import cz.muni.fi.pa165.dndtroops.dto.TroopCreateDTO;
import cz.muni.fi.pa165.dndtroops.dto.TroopDTO;
import cz.muni.fi.pa165.dndtroops.entities.Troop;
import cz.muni.fi.pa165.dndtroops.service.BeanMappingService;
import cz.muni.fi.pa165.dndtroops.service.TroopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TroopFacadeImpl implements TroopFacade {

    @Autowired
    private TroopService troopService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public TroopDTO createTroop(TroopCreateDTO t) {
        Troop mappedTroop = beanMappingService.mapTo(t,Troop.class);
        troopService.createTroop(mappedTroop);

        return beanMappingService.mapTo(mappedTroop,TroopDTO.class);
    }

    @Override
    public void deleteTroop(Long id) {
        troopService.deleteTroop(troopService.findTroopById(id));
    }

    @Override
    public void updateTroop(TroopDTO t) {
        troopService.updateTroop(beanMappingService.mapTo(t,Troop.class));
    }

    @Override
    public TroopDTO findTroopById(Long id) {
        return beanMappingService.mapTo(troopService.findTroopById(id),TroopDTO.class);
    }

    @Override
    public TroopDTO findTroopByName(String name) {
        return beanMappingService.mapTo(troopService.findTroopByName(name), TroopDTO.class);
    }

    @Override
    public List<TroopDTO> findAllTroops() {
        return beanMappingService.mapTo(troopService.findAllTroops(),TroopDTO.class);

    }

    @Override
    public List<HeroDTO> findHeroesOfTroop(TroopDTO t) {
        Troop mappedTroop = beanMappingService.mapTo(t,Troop.class);
        return  beanMappingService.mapTo(troopService.findHeroesOfTroop(mappedTroop),HeroDTO.class);
    }

}