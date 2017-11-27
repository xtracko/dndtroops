package cz.muni.fi.pa165.dndtroops.service;

import cz.muni.fi.pa165.dndtroops.entities.Hero;
import cz.muni.fi.pa165.dndtroops.entities.Troop;

import java.util.List;

public interface TroopService {

    void createTroop(Troop t );

    void deleteTroop(Troop t );

    void updateTroop(Troop t );

    Troop findTroopById(Long id);

    Troop findTroopByName(String name);

    List<Troop> findAllTroops();

    List<Troop> listWealthiestTroops();

    List<Hero> findHeroesOfTroop(Troop t);

    float computeTroopStrength(Troop t);

    Troop troopBattle(Troop t1, Troop t2);

}
