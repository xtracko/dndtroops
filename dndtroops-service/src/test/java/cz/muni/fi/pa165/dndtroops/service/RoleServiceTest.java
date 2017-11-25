package cz.muni.fi.pa165.dndtroops.service;

import cz.muni.fi.pa165.dndtroops.ServiceConfiguration;
import cz.muni.fi.pa165.dndtroops.entities.Role;
import cz.muni.fi.pa165.dndtroops.enums.Power;
import org.assertj.core.api.Assertions;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@ContextConfiguration(classes = ServiceConfiguration.class)
public class RoleServiceTest extends AbstractTransactionalTestNGSpringContextTests {
    @Autowired
    @InjectMocks
    private RoleService roleService;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void simpleTest() {
        Role role = new Role("name", "desc", Power.MAGIC, 80, 1);

        Assertions.assertThat(role.getId()).isNull();
        roleService.createRole(role);
        Assertions.assertThat(role.getId()).isNotNull();
    }
}
