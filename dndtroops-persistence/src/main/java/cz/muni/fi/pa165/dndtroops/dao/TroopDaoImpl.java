package cz.muni.fi.pa165.dndtroops.dao;

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

    public void create (Troop t ) {
        em.persist(t);
    }

    public void delete (Troop t ) {
        em.remove(t);
    }

    public void update (Troop t ){
        em.merge(t);
    }

    public Troop findById (long id){
        try {
            return
                    em.createQuery("select t from Troop t where t.id=:id" , Troop.class).setParameter("id", id).getSingleResult();
        } catch (NoResultException noresult) {
            return null;
        }
    }

    public Troop findByName (String name){
        try {
            return
                    em.createQuery("select t from Troop t where t.name=:name" , Troop.class).setParameter("name", name).getSingleResult();
        } catch (NoResultException noresult) {
            return null;
        }
    }

    public List<Troop> findAll(){
        return
                em.createQuery("select t from Troop t", Troop.class).getResultList();
    }

}
