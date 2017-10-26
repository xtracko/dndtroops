/**
 * @author Jiří Novotný
 */

package cz.muni.fi.pa165.dndtroops.dao;

import cz.muni.fi.pa165.dndtroops.entities.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void createRole(Role role) {
        em.persist(role);
    }

    @Override
    public void deleteRole(Role role) {
        em.remove(role);
    }

    @Override
    public Role findRoleById(Long id) {
        return em.find(Role.class, id);
    }

    @Override
    public Role findRoleByName(String name) {
        try {
            return em.createQuery("select r from Role r where name = :name", Role.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public List<Role> findAllRoles() {
        return em.createQuery("select r from Role r", Role.class)
                .getResultList();
    }
}
