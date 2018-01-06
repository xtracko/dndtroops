package cz.muni.fi.pa165.dndtroops.service;

import cz.muni.fi.pa165.dndtroops.ServiceConfiguration;
import cz.muni.fi.pa165.dndtroops.entities.Administrator;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


@ContextConfiguration(classes = ServiceConfiguration.class)
public class AdminServiceTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    @InjectMocks
    private AdminService adminService;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testExample() throws Exception {
        String name = "Peter";
        Administrator admin = new Administrator(name);
        adminService.createAdministrator(admin,"pass");
        assertEquals(admin, adminService.findAdministratorByName(name));
    }




    @Test
    public void testCreateAdministrator() throws Exception {
    }

    @Test
    public void testUpdateAdministrator() throws Exception {
    }

    @Test
    public void testFindAdministatorById() throws Exception {
    }

    @Test
    public void testFindAdministratorByName() throws Exception {
    }

    @Test
    public void testFindAllAdministrators() throws Exception {
    }

    @Test
    public void testRemoveAdministrator() throws Exception {
    }

}