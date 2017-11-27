/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.dndtroops.facade;

import cz.muni.fi.pa165.dndtroops.ServiceConfiguration;
import cz.muni.fi.pa165.dndtroops.dto.CreateRoleDTO;
import cz.muni.fi.pa165.dndtroops.dto.RoleDTO;
import cz.muni.fi.pa165.dndtroops.enums.Power;
import java.util.List;
import javax.inject.Inject;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Martin
 */
@DirtiesContext
@ContextConfiguration(classes = ServiceConfiguration.class)
public class RoleFacadeTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private RoleFacade roleFacade;

    private CreateRoleDTO role1;
    private CreateRoleDTO role2;
    private CreateRoleDTO role3;
    
    
    /*@BeforeMethod
    public void setup() {
        role1 = new CreateRoleDTO("Knight", "Very good fighter with weapons, from a noble family", Power.WEAPONS);        
        role2 = new CreateRoleDTO("Druid", "Healer good at casting spells, healing and making potions", Power.MAGIC);
        role3 = new CreateRoleDTO("Ninja", "Skilled rogue-ish warrior, trained by monks", Power.MARTIAL_ARTS);
        

    }*/
    
    @Test    
    public void createRoleTest(){
        RoleDTO role = roleFacade.createRole(new CreateRoleDTO("Dark Ninja", "Skilled rogue-ish warrior, trained by monks", Power.MARTIAL_ARTS));
        Assertions.assertThat(role.getId()).isNotNull();
        Assertions.assertThat(roleFacade.findById(role.getId()).getName()).isEqualTo("Dark Ninja");
        Assertions.assertThat(roleFacade.findById(role.getId()).getDescription()).isEqualTo("Skilled rogue-ish warrior, trained by monks");
        Assertions.assertThat(roleFacade.findById(role.getId()).getPower()).isEqualTo(Power.MARTIAL_ARTS);
    }
    
    /*@Test
    public void updateRoleTest(){         
        RoleDTO role = roleFacade.createRole(new CreateRoleDTO("Dark Ninja", "Skilled rogue-ish warrior, trained by monks", Power.MARTIAL_ARTS));
        role.setName("Bright Ninja");
        
        Assert.assertEquals( roleFacade.getAllRoles().size(), 1);
        roleFacade.editRole(role);
        Assert.assertEquals( roleFacade.getAllRoles().size(), 1);
        Assert.assertEquals( roleFacade.findById(role.getId()).getName(), "Bright Ninja");
        
        
    }

   @Test
    public void FindByIdTest(){
         
        RoleDTO roleC = roleFacade.createRole(new CreateRoleDTO("Dark Ninja", "Skilled rogue-ish warrior, trained by monks", Power.MARTIAL_ARTS));
        RoleDTO roleD = roleFacade.createRole(new CreateRoleDTO("Darkd Ninja", "Skilled rogue-ish warrior,d trained by monks", Power.MARTIAL_ARTS));
         
         Assert.assertEquals(roleFacade.getAllRoles().size(), 2);
    }*/
    
    
}
