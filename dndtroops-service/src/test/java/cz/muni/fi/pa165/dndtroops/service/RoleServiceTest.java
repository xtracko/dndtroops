package cz.muni.fi.pa165.dndtroops.service;

import cz.muni.fi.pa165.dndtroops.ServiceConfiguration;
import cz.muni.fi.pa165.dndtroops.dao.RoleDao;
import cz.muni.fi.pa165.dndtroops.entities.Role;
import cz.muni.fi.pa165.dndtroops.enums.Power;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author Martin Sestak
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class RoleServiceTest extends AbstractTransactionalTestNGSpringContextTests {    
    @Autowired
    @InjectMocks
    private RoleService roleService;
    
    @Mock
    private RoleDao roleDao;
    
    private Role role1;
    private Role role2;
    private Role role3;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
        role3 = new Role("Ninja", "Skilled rogue-ish warrior, trained by monks", Power.MARTIAL_ARTS, 0, 0);
    }
    
    @BeforeMethod
    public void prepareRoles() {
        role1 = new Role("Knight", "Very good fighter with weapons, from a noble family", Power.WEAPONS, 80, 1);
        role1.setId(1L);

        role2 = new Role("Druid", "Healer good at casting spells, healing and making potions", Power.MAGIC, 0, 0);
        role2.setId(2L);
    }
    
    @Test
    public void createRoleTest() {
        
        roleService.createRole(role1);
        Long id= role1.getId();
        
        when(roleDao.findRoleById(1L)).thenReturn(role1);
        
        Role roleA = roleService.findRoleById(id);
        Assert.assertNotNull(id);
        Assert.assertNotNull(role1);
        Assert.assertNotNull(roleA);
        Assert.assertEquals(role1, roleA);
    }

    @Test
    public void editRoleTest() {
        roleService.createRole(role1);
        role1.setName("Death knight");
        Long id= role1.getId();
        when(roleDao.findRoleById(1L)).thenReturn(role1);
        roleService.editRole(role1);
        
        Role roleA = roleService.findRoleById(id);
        Assert.assertNotNull(id);
        Assert.assertNotNull(role1);
        Assert.assertNotNull(roleA);
        Assert.assertEquals(role1.getName(), roleA.getName());
        
    }
    
    @Test
    public void removeRoleTest() {
        roleService.createRole(role1);        
        
        roleService.removeRole(role1);
        Assertions.assertThat(roleService.getAllRoles().size()).isEqualTo(0);  
        
    }
    
    @Test
    public void getAllRolesEmptyTest() {
        List<Role> roles = roleService.getAllRoles();
        Assertions.assertThat(roles.size()).isEqualTo(0);        
    }
    
    /*@Test
    public void getAllRolesNonEmptyTest() {
        roleService.createRole(role1);
        List<Role> roles = roleService.getAllRoles();
        Assertions.assertThat(roles.size()).isEqualTo(1);
        
        roleService.createRole(role2);
        roles = roleService.getAllRoles();
        Assertions.assertThat(roles.size()).isEqualTo(2);
        
    }*/
    
    @Test
    public void getAllRolesByPowerEmptyTest() {
        List<Role> roles = roleService.getAllRolesByPower(Power.MAGIC);
        Assertions.assertThat(roles.size()).isEqualTo(0);        
    }
    
     @Test
    public void getAllRolesByNonExistingPowerTest() {
        //
        roleService.createRole(role1);
        List<Role> roles = roleService.getAllRolesByPower(Power.MAGIC);
        Assertions.assertThat(roles.size()).isEqualTo(0);        
    }
    
    /*@Test
    public void getAllRolesByPowerTest() {
        
        role3.setId(null);
        roleService.createRole(role3);
        List<Role> roles = roleService.getAllRolesByPower(Power.MARTIAL_ARTS);
        Assertions.assertThat(roles.size()).isEqualTo(1);
        
    }*/
    
    @Test
    public void computeAttackingForceTest() {
        float computedForce = roleService.computeAttackingForce(role1);
        Assertions.assertThat(computedForce).isNotEqualTo(0);
        Assertions.assertThat(computedForce).isNotNull();
        
    }
    
    @Test
    public void computeAttackingZeroForceTest() {
         float computedForce = roleService.computeAttackingForce(role2);
        Assertions.assertThat(computedForce).isEqualTo(0);
        Assertions.assertThat(computedForce).isNotNull();
        
    }
   
    
}
