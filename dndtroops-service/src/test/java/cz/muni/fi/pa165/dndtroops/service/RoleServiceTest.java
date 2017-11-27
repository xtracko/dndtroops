package cz.muni.fi.pa165.dndtroops.service;

import cz.muni.fi.pa165.dndtroops.ServiceConfiguration;
import cz.muni.fi.pa165.dndtroops.entities.Role;
import cz.muni.fi.pa165.dndtroops.enums.Power;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author Martin Sestak
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class RoleServiceTest extends AbstractTransactionalTestNGSpringContextTests {    
    @Autowired
    @InjectMocks
    private RoleService roleService;
    
    private Role role1;
    private Role role2;
    private Role role3;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
        role1 = new Role("Knight", "Very good fighter with weapons, from a noble family", Power.WEAPONS, 80, 1);
        role2 = new Role("Druid", "Healer good at casting spells, healing and making potions", Power.MAGIC, 80, 1);
        role3 = new Role("Ninja", "Skilled rogue-ish warrior, trained by monks", Power.MARTIAL_ARTS, 0, 0);
    }
    
    @Test
    public void createRoleTest() {
        role1.setId(null);
        Assertions.assertThat(role1.getId()).isNull();
        
        roleService.createRole(role1);
        Assertions.assertThat(role1.getId()).isNotNull();
    }

    @Test
    public void editRoleTest() {
        role1.setId(null);
        roleService.createRole(role1);
        role1.setName("Death knight");
        
        
        roleService.editRole(role1);
        List<Role> roles = roleService.getAllRoles();
        Assertions.assertThat(roles.size()).isEqualTo(1);
        Assertions.assertThat(roles.get(0).getName()).isEqualTo("Death knight");
        
    }
    
    @Test
    public void removeRoleTest() {
        role1.setId(null);
        roleService.createRole(role1);
        
        List<Role> roles = roleService.getAllRoles();
        Assertions.assertThat(roles.size()).isEqualTo(1);  
        
        
        roleService.removeRole(role1);
        
        roles = roleService.getAllRoles();
        Assertions.assertThat(roles.size()).isEqualTo(0);  
        
    }
    
    @Test
    public void getAllRolesEmptyTest() {
        List<Role> roles = roleService.getAllRoles();
        Assertions.assertThat(roles.size()).isEqualTo(0);        
    }
    
    @Test
    public void getAllRolesNonEmptyTest() {
        role1.setId(null);
        roleService.createRole(role1);
        List<Role> roles = roleService.getAllRoles();
        Assertions.assertThat(roles.size()).isEqualTo(1);
        
        role2.setId(null);
        roleService.createRole(role2);
        roles = roleService.getAllRoles();
        Assertions.assertThat(roles.size()).isEqualTo(2);
        
    }
    
    @Test
    public void getAllRolesByPowerEmptyTest() {
        List<Role> roles = roleService.getAllRolesByPower(Power.MAGIC);
        Assertions.assertThat(roles.size()).isEqualTo(0);        
    }
    
     @Test
    public void getAllRolesByNonExistingPowerTest() {
        role1.setId(null);
        roleService.createRole(role1);
        List<Role> roles = roleService.getAllRolesByPower(Power.MAGIC);
        Assertions.assertThat(roles.size()).isEqualTo(0);        
    }
    
    @Test
    public void getAllRolesByPowerTest() {
        role2.setId(null);
        roleService.createRole(role2);
        List<Role> roles = roleService.getAllRolesByPower(Power.MAGIC);
        Assertions.assertThat(roles.size()).isEqualTo(1);
        
    }
    
    @Test
    public void computeAttackingForceTest() {
        float computedForce = roleService.computeAttackingForce(role1);
        Assertions.assertThat(computedForce).isNotEqualTo(0);
        Assertions.assertThat(computedForce).isNotNull();
        
    }
    
    @Test
    public void computeAttackingZeroForceTest() {
         float computedForce = roleService.computeAttackingForce(role3);
        Assertions.assertThat(computedForce).isEqualTo(0);
        Assertions.assertThat(computedForce).isNotNull();
        
    }
   
    
}
