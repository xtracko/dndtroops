package cz.muni.fi.pa165.dndtroops.rest.controllers;

import cz.muni.fi.pa165.dndtroops.RootWebContext;
import cz.muni.fi.pa165.dndtroops.facade.RoleFacade;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * @author Jiří Novotný
 */

@WebAppConfiguration
@ContextConfiguration(classes = {RootWebContext.class})
public class RolesControllerTest extends AbstractTestNGSpringContextTests {
    @Mock
    private RoleFacade roleFacade;

    @InjectMocks
    private RolesController rolesController = new RolesController();

    private MockMvc mockMvc;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(rolesController).setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    @Test
    public void getAllRoles() {
        Assert.fail("not yet implemented");
    }
}
