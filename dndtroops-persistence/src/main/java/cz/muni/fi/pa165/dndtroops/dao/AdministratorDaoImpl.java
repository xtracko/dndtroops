/**
 * @author Miroslav Macor
 */
package cz.muni.fi.pa165.dndtroops.dao;

import cz.muni.fi.pa165.dndtroops.entities.Administrator;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class AdministratorDaoImpl implements AdministratorDao {

    @PersistenceContext
    private EntityManager em;


    @Override
    public void createAdministrator(Administrator admin) {
        em.persist(admin);

    }

    @Override
    public void removeAdministrator(Administrator admin) {
        em.remove(admin);
    }

    @Override
    public void updateAdministrator(Administrator admin) {
        em.merge(admin);
    }

    @Override
    public Administrator findAdministatorById(Long id) {
        try {
            return em.createQuery("SELECT a FROM Administrator a WHERE id = :id", Administrator.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }


    }

    @Override
    public Administrator findAdministratorByName(String name) {
        return em.createQuery("SELECT a FROM Administrator a WHERE name = :name", Administrator.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public List<Administrator> findAllAdministrators() {
        return em.createQuery("SELECT a FROM Administrator a", Administrator.class)
                .getResultList();
    }
}
