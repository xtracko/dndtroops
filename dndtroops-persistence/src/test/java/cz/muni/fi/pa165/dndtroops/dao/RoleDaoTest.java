package cz.muni.fi.pa165.dndtroops.dao;
import cz.muni.fi.pa165.dndtroops.entities.Role;

import cz.muni.fi.pa165.dndtroops.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.dndtroops.enums.Power;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class RoleDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    public EntityManager em;

    @Autowired
    public RoleDao roleDao;
        
    private Role r1;
    private Role r2;
    private Role r3;
        
        @BeforeMethod
	public void createRoles() {
            r1 = new Role("Knight", "Very good fighter with weapons, from a noble family", Power.WEAPONS);
            r1 = new Role("Druid", "Healer good at casting spells, healing and making potions", Power.MAGIC);
            r1 = new Role("Ninja", "Skilled rogue-ish warrior, trained by monks", Power.MARTIAL_ARTS);
            
            roleDao.createRole(r1);
            roleDao.createRole(r2);
            roleDao.createRole(r3);
        
        }
        
        
        @Test
        public void findById(){
            Long testId = r1.getId();
            
            Assert.assertEquals(roleDao.findRoleById(testId),r1);
        }
        @Test
        public void findByNonExistingId(){
            Long testId = new Long(56);
            
            Assert.assertNull(roleDao.findRoleById(testId)); 
        }
        
        @Test
	public void findAll() {
		List<Role> found = roleDao.findAllRoles();
		Assert.assertEquals(found.size(), 3);
	}
        

}
