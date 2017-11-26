package cz.muni.fi.pa165.dndtroops.dao;

import cz.muni.fi.pa165.dndtroops.entities.Hero;
import cz.muni.fi.pa165.dndtroops.entities.Troop;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/*

Implementation of DAO class for entity Troop.
Creted by: Vojtech Duchon (UCO:410007)

 */

@Repository
public class TroopDaoImpl implements TroopDao {

    @PersistenceContext
    private EntityManager em;


    @Override
    public void createTroop(Troop t ) {
        em.persist(t);
    }


    @Override
    public void deleteTroop(Troop t ) {
        em.remove(t);
    }


    @Override
    public void updateTroop(Troop t ){
        em.merge(t);
    }

    @Override
    public Troop findTroopById(Long id){
        return em.find(Troop.class, id);
    }

    @Override
    public Troop findTroopByName(String name){
        try {
            return
                    em.createQuery("select t from Troop t where t.name=:name" , Troop.class).setParameter("name", name).getSingleResult();
        } catch (NoResultException noresult) {
            return null;
        }
    }


    @Override
    public List<Troop> findAllTroops() {
        return em.createQuery("select t from Troop t", Troop.class).getResultList();
    }

    @Override
    public List<Hero> findHeroesOfTroop(Troop t){
        try {
            return em.createQuery("select h from Hero h where h.troop = :troopid", Hero.class)
                    .setParameter("troopid", t)
                    .getResultList();
        } catch (NoResultException ex) {
            return null;
        }
    }

}
