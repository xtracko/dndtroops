package cz.muni.fi.pa165.dndtroops.service;

import cz.muni.fi.pa165.dndtroops.dao.TroopDao;
import cz.muni.fi.pa165.dndtroops.entities.Troop;
import cz.muni.fi.pa165.dndtroops.service.battle.HeroState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

/**
 *  @author Vojtěch Duchoň and Jiří Novotný (changes to the non-trivial bussiness functionality)
 */
@Service
public class TroopServiceImpl implements TroopService {
    @Autowired
    private TroopDao troopDao;

    @Autowired
    private RandomService randomService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private HeroService heroService;

    @Override
    public void createTroop(Troop t) {
        troopDao.createTroop(t);
    }

    @Override
    public void deleteTroop(Troop t) {
        troopDao.removeTroop(t);
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

    @Override
    public Troop battle(Troop a, Troop b) {
        if (Objects.equals(a, b))
            throw new IllegalArgumentException("Cannot battle among same troops");

        List<HeroState> aStates = heroService.getHeroesByTroop(a).stream().map(HeroState::new).collect(toList());
        List<HeroState> bStates = heroService.getHeroesByTroop(b).stream().map(HeroState::new).collect(toList());

        randomService.shuffle(aStates);
        randomService.shuffle(bStates);

        Iterator<HeroState> aIter = aStates.iterator();
        Iterator<HeroState> bIter = bStates.iterator();

        HeroState aHero = null;
        HeroState bHero = null;

        while (hasAnyAlive(aHero, aIter) && hasAnyAlive(bHero, bIter)) {
            if (aHero == null || !aHero.isAlive())
                aHero = aIter.next();
            if (bHero == null || !bHero.isAlive())
                bHero = bIter.next();

            heroService.fight(aHero, bHero);
        }

        if (hasAnyAlive(aHero, aIter)) {
            stealMoney(a, b);
            return a;
        }
        if (hasAnyAlive(bHero, bIter)) {
            stealMoney(b, a);
            return b;
        }
        return null;
    }

    private boolean hasAnyAlive(HeroState state, Iterator<HeroState> iterator) {
        return (state != null && state.isAlive()) || iterator.hasNext();
    }

    private void stealMoney(Troop winner, Troop looser) {
        long amount = looser.getGoldenMoney() / 2;
        looser.setGoldenMoney(looser.getGoldenMoney() - amount);
        winner.setGoldenMoney(winner.getGoldenMoney() + amount);

        troopDao.updateTroop(looser);
        troopDao.updateTroop(winner);
    }
}
