package facade;

import cz.muni.fi.pa165.dndtroops.dto.TroopDto;
import cz.muni.fi.pa165.dndtroops.entities.Troop;
import cz.muni.fi.pa165.dndtroops.service.BeanMappingService;
import cz.muni.fi.pa165.dndtroops.service.TroopService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TroopFacadeImpl implements TroopFacade{

    @Autowired
    private TroopService troopService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public void createTroop(TroopDto t) {
        troopService.createTroop(beanMappingService.mapTo(t, Troop.class));
    }

    @Override
    public void deleteTroop(TroopDto t) {
        troopService.deleteTroop(beanMappingService.mapTo(t,Troop.class));
    }

    @Override
    public void updateTroop(TroopDto t) {
        troopService.updateTroop(beanMappingService.mapTo(t,Troop.class));
    }

    @Override
    public TroopDto findTroopById(Long id) {
        return beanMappingService.mapTo(troopService.findTroopById(id),TroopDto.class);
    }

    @Override
    public TroopDto findTroopByName(String name) {
        return beanMappingService.mapTo(troopService.findTroopByName(name), TroopDto.class);
    }

    @Override
    public List<TroopDto> findAllTroops() {
        return beanMappingService.mapTo(troopService.findAllTroops(),TroopDto.class);

    }
}
