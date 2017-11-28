/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.dndtroops.facade;

import cz.muni.fi.pa165.dndtroops.ServiceConfiguration;
import cz.muni.fi.pa165.dndtroops.dto.*;
import cz.muni.fi.pa165.dndtroops.enums.Power;
import org.assertj.core.api.SoftAssertions;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Martin Sestak
 */
@DirtiesContext
@ContextConfiguration(classes = ServiceConfiguration.class)
@Transactional
public class RoleFacadeTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private RoleFacade roleFacade;

    private RoleDTO rogue;
    private RoleDTO soldier;
    private RoleDTO mage;
    
    
    @BeforeMethod
    public void setupRoles() {
        CreateRoleDTO create_rogue= new CreateRoleDTO();
        create_rogue.setName("Ninja");
        create_rogue.setDescription("Skilled rogue-ish warrior, trained by monks");
        create_rogue.setPower( Power.MARTIAL_ARTS);
        create_rogue.setDamageMean(80);
        create_rogue.setDamageVariance(2);
        
        CreateRoleDTO create_soldier= new CreateRoleDTO();
        create_soldier.setName("Knight");
        create_soldier.setDescription("Very good fighter with weapons, from a noble family");        
        create_soldier.setPower(Power.WEAPONS);
        create_rogue.setDamageMean(65);
        create_rogue.setDamageVariance(2);
        
        CreateRoleDTO create_mage = new CreateRoleDTO();
        create_mage.setName("Druid");
        create_mage.setDescription("Healer good at casting spells healing and making potions");
        create_mage.setPower(Power.MAGIC);
        create_rogue.setDamageMean(70);
        create_rogue.setDamageVariance(1);
        
        rogue = roleFacade.createRole(create_rogue);
        soldier = roleFacade.createRole(create_soldier);
        mage = roleFacade.createRole(create_mage);
    }
    
    @Test
    @Rollback(true)     
    public void createRoleTest(){
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(rogue.getId()).isNotNull();
        softly.assertThat(soldier.getId()).isNotNull();
        softly.assertThat(mage.getId()).isNotNull();
        softly.assertAll();
    }
    
    @Test
    @Rollback(true) 
    public void updateRoleTest(){  
        rogue.setName("Samurai");
        roleFacade.editRole(rogue);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(roleFacade.findById(rogue.getId()))
                .isNotNull()
                .hasFieldOrPropertyWithValue("name", "Samurai");
        softly.assertAll();            
    }
    
    @Test
    @Transactional  
    public void deleteRoleTest() {
        roleFacade.removeRole(rogue);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(roleFacade.getAllRoles())
                .hasSize(2)
                .contains(soldier, mage);
        softly.assertAll();
    }
    
    @Test
    @Rollback(true)  
    public void getHeroByIdTest() {
        assertThat(roleFacade.findById(soldier.getId()))
                .isNotNull()
                .hasFieldOrPropertyWithValue("id", soldier.getId());
    }
    
    @Test
    @Rollback(true)   
    public void getAllRolesTest() {
        assertThat(roleFacade.getAllRoles())
                .hasSize(3)
                .contains(soldier, mage);
    }
    
    @Test
    @Rollback(true) 
    public void getAllRolesByPowerTest() {
        assertThat(roleFacade.getAllRolesByPower(Power.MAGIC))
                .hasSize(1)
                .contains(mage);
    }  
    
}
