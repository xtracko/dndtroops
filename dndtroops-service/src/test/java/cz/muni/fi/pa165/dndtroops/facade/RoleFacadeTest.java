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
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
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

    private RoleDTO role1;
    private RoleDTO role2;
    private RoleDTO role3;
    
    
    /*@BeforeMethod
    public void setup() {
        role1 = roleFacade.createRole(new CreateRoleDTO("Knight", "Very good fighter with weapons, from a noble family", Power.WEAPONS));        
        role2 = roleFacade.createRole(new CreateRoleDTO("Druid", "Healer good at casting spells, healing and making potions", Power.MAGIC));
        role3 = roleFacade.createRole(new CreateRoleDTO("Ninja", "Skilled rogue-ish warrior, trained by monks", Power.MARTIAL_ARTS));
        

    }*/
    
    /*@Test
    public void createRoleTest(){
        RoleDTO role = roleFacade.createRole(new CreateRoleDTO("Ninja", "Skilled rogue-ish warrior, trained by monks", Power.MARTIAL_ARTS));
        Assertions.assertThat(role.getId()).isNotNull();
        Assertions.assertThat(role.getName()).isEqualTo("Ninja");
        Assertions.assertThat(role.getDescription()).isEqualTo("Skilled rogue-ish warrior, trained by monks");
        Assertions.assertThat(role.getPower()).isEqualTo(Power.MARTIAL_ARTS);
    }*/
    
    @Test
    public void deleteRoleTest(){
        //List<RoleDTO> beginningRoles = roleFacade.getAllRoles();
        //int roleCount = beginningRoles.size();
         
        RoleDTO role = roleFacade.createRole(new CreateRoleDTO("Dark Ninja", "Skilled rogue-ish warrior, trained by monks", Power.MARTIAL_ARTS));
        Assert.assertEquals( roleFacade.getAllRoles().size(), 1);
        roleFacade.removeRole(role);
        Assert.assertEquals( roleFacade.getAllRoles().size(), 0);
        
    }

    /*@Test
    public void FindByIdTest(){
         List<RoleDTO> roles = roleFacade.getAllRoles();
         Assert.assertEquals(roles.size(), 3);
    }*/
}
