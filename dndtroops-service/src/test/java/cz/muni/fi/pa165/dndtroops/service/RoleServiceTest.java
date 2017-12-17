package cz.muni.fi.pa165.dndtroops.service;

import cz.muni.fi.pa165.dndtroops.ServiceConfiguration;
import cz.muni.fi.pa165.dndtroops.dao.HeroDao;
import cz.muni.fi.pa165.dndtroops.dao.RoleDao;
import cz.muni.fi.pa165.dndtroops.entities.Hero;
import cz.muni.fi.pa165.dndtroops.entities.Role;
import cz.muni.fi.pa165.dndtroops.enums.Power;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import static org.assertj.core.api.Assertions.assertThat;
import org.assertj.core.api.SoftAssertions;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author Martin Sestak
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class RoleServiceTest extends AbstractTransactionalTestNGSpringContextTests {    
    
    @InjectMocks
    private RoleServiceImpl roleService;
    
    @Mock
    private RandomService randomService;
    
    @Mock
    private RoleDao roleDao;

    @Mock
    private HeroDao heroDao;
    
    private Role rogue;
    private Role soldier;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }
    
    @BeforeMethod
    public void prepareRoles() {
        rogue = new Role("Ninja", "Skilled rogue-ish warrior, trained by monks", Power.MARTIAL_ARTS, 0, 0);    
        soldier = new Role("Knight", "Very good fighter with weapons, from a noble family", Power.WEAPONS, 80, 1);
    }
    
    @Test
    public void createRoleTest() {
        doAnswer(invocation -> {
            rogue.setId(1L);
            return null;
        }).when(roleDao).createRole(rogue);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(rogue.getId()).isNull();

        roleService.createRole(rogue);

        softly.assertThat(rogue.getId()).isNotNull();
        softly.assertAll();

        verify(roleDao).createRole(rogue);
    }

    @Test
    public void editRoleTest() {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(rogue.getName()).isEqualTo("Ninja");
        softly.assertThat(rogue.getDescription()).isEqualTo("Skilled rogue-ish warrior, trained by monks");
        softly.assertThat(rogue.getPower()).isEqualTo(Power.MARTIAL_ARTS);

        rogue.setName("Samurai");
        roleService.editRole(rogue);

        softly.assertThat(rogue.getName()).isEqualTo("Samurai");
        softly.assertThat(rogue.getDescription()).isEqualTo("Skilled rogue-ish warrior, trained by monks");
        softly.assertThat(rogue.getPower()).isEqualTo(Power.MARTIAL_ARTS);
        softly.assertAll();

        verify(roleDao).updateRole(rogue);        
    }


    @Test
    public void deleteRoleTest() {
        roleService.deleteRole(soldier);

        verify(roleDao).deleteRole(soldier);
        verify(heroDao).findHeroesByRole(soldier);
    }


    @Test
    public void getAllRolesNonEmptyTest() {
        when(roleDao.findAllRoles()).thenAnswer(invocation -> {
            List<Role> roles = new ArrayList<>();
            roles.add(rogue);
            roles.add(soldier);
            return roles;
        });

        assertThat(roleService.getAllRoles())
                .hasSize(2)
                .contains(rogue, soldier);
        
    }
   
     @Test
    public void getAllRolesByNonExistingPowerTest() {
        when(roleDao.findAllRolesByPower(Power.MAGIC)).thenAnswer(invocation -> {
            List<Role> roles = new ArrayList<>();
            return roles;
        });

        assertThat(roleService.getAllRoles())
                .hasSize(0);     
    }
    
    @Test
    public void getAllRolesByPowerTest() {
        when(roleDao.findAllRolesByPower(Power.MARTIAL_ARTS)).thenAnswer(invocation -> {
            List<Role> roles = new ArrayList<>();
            
            roles.add(rogue);
            return roles;
        });

        assertThat(roleService.getAllRolesByPower(Power.MARTIAL_ARTS))
                .hasSize(1)
                .contains(rogue);
    }
    
    @Test
    public void computeAttackingForceTest() {
        float computedForce = roleService.computeAttackingForce(soldier);
        Assertions.assertThat(computedForce).isNotEqualTo(0);
        Assertions.assertThat(computedForce).isNotNull();
        
    }
    
    @Test
    public void computeAttackingZeroForceTest() {
         float computedForce = roleService.computeAttackingForce(rogue);
        Assertions.assertThat(computedForce).isEqualTo(0);
        Assertions.assertThat(computedForce).isNotNull();
        
    }
   
    
}
