package cz.muni.fi.pa165.dndtroops.service;

import cz.muni.fi.pa165.dndtroops.dao.TroopDao;
import cz.muni.fi.pa165.dndtroops.entities.Troop;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TroopServiceImpl implements TroopService {


    @Autowired
    private TroopDao troopDao;

    @Override
    public void createTroop(Troop t) {
        troopDao.createTroop(t);
    }

    @Override
    public void deleteTroop(Troop t) {
        troopDao.deleteTroop(t);
    }

    @Override
    public void updateTroop(Troop t) {
        troopDao.updateTroop(t);
    }

    @Override
    public Troop findTroopById(Long id) { return troopDao.findTroopById(id); }

    @Override
    public Troop findTroopByName(String name) {
        return troopDao.findTroopByName(name);
    }

    @Override
    public List<Troop> findAllTroops() {
        return troopDao.findAllTroops();
    }
}
