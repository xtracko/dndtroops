package cz.muni.fi.pa165.dndtroops.rest.controllers;

import cz.muni.fi.pa165.dndtroops.RootWebContext;
import cz.muni.fi.pa165.dndtroops.rest.ApiUris;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * @author Jiří Novotný
 */

@WebAppConfiguration
@ContextConfiguration(classes = {RootWebContext.class})
public class MainControllerTest extends AbstractTestNGSpringContextTests {
    private final MockMvc mockMvc = standaloneSetup(new MainController()).build();

    @Test
    public void getResourcesTest() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("roles_uri").value(ApiUris.ROOT_URI_ROLES))
                .andExpect(jsonPath("users_uri").value(ApiUris.ROOT_URI_USERS))
                .andExpect(jsonPath("heroes_uri").value(ApiUris.ROOT_URI_HEROES))
                .andExpect(jsonPath("troops_uri").value(ApiUris.ROOT_URI_TROOPS));
    }
}
