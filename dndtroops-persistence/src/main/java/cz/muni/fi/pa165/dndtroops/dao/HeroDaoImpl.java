package cz.muni.fi.pa165.dndtroops.dao;

import cz.muni.fi.pa165.dndtroops.entities.Hero;
import cz.muni.fi.pa165.dndtroops.entities.Role;
import cz.muni.fi.pa165.dndtroops.entities.Troop;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Martin Sestak
 */
public class HeroDaoImpl implements HeroDao {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void createHero(Hero hero) {
        em.persist(hero);
    }

    @Override
    public void updateHero(Hero hero) {
        em.merge(hero);
    }

    @Override
    public void deleteHero(Hero hero) {
        em.remove(hero);
    }

    @Override
    public Hero findHeroById(Long id) {
       return em.find(Hero.class, id);
    }

    @Override
    public Hero findHeroByName(String name) {
       try {
            return em.createQuery("select h from Hero h where h.name = :name", Hero.class)
                .setParameter("name", name)
                .getSingleResult();
         } catch (NoResultException ex) {
            return null;
         }
    }

    @Override
    public List<Hero> findHeroesByRole(Role role) {
        try {
            return em.createQuery("select h from Hero h where h.role = :roleid", Hero.class)
                .setParameter("name", role)
                .getResultList();
         } catch (NoResultException ex) {
            return null;
         }
    }

    @Override
    public List<Hero> findHeroesByTroop(Troop troop) {
        try {
            return em.createQuery("select h from Hero h where h.troop = :troopid", Hero.class)
                .setParameter("name", troop)
                .getResultList();
         } catch (NoResultException ex) {
            return null;
         }
    }

    @Override
    public List<Hero> findHeroesByXp(int xp) {
        try {
            return em.createQuery("select h from Hero h where h.xp = :xp", Hero.class)
                .setParameter("name", xp)
                .getResultList();
         } catch (NoResultException ex) {
            return null;
         }
    }

    @Override
    public List<Hero> findAllHeroes() {
       try{
           return em.createQuery("select h from Hero h", Hero.class)
                .getResultList();
       } catch (NoResultException ex) {
           return null;
       }
    }
    
}
