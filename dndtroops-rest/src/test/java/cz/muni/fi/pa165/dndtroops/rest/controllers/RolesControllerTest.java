package cz.muni.fi.pa165.dndtroops.rest.controllers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.muni.fi.pa165.dndtroops.RootWebContext;
import cz.muni.fi.pa165.dndtroops.dto.CreateRoleDTO;
import cz.muni.fi.pa165.dndtroops.dto.RoleDTO;
import cz.muni.fi.pa165.dndtroops.enums.Power;
import cz.muni.fi.pa165.dndtroops.facade.RoleFacade;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
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
    public void getAllRoles() throws Exception {
        doReturn(Collections.unmodifiableList(this.createRoles())).when(roleFacade).findAllRoles();

        mockMvc.perform(get("/roles"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.[?(@.id==1)].name").value("Assassin"))
                .andExpect(jsonPath("$.[?(@.id==2)].name").value("Marksman"))
                .andExpect(jsonPath("$.[?(@.id==3)].name").value("Tank"));

        verify(roleFacade).findAllRoles();
    }

    @Test
    public void getRoleTest() throws Exception {
        List<RoleDTO> roles = createRoles();

        doReturn(roles.get(0)).when(roleFacade).findById(1L);
        doReturn(roles.get(2)).when(roleFacade).findById(3L);

        mockMvc.perform(get("/roles/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.name").value("Assassin"));

        mockMvc.perform(get("/roles/3"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.name").value("Tank"));

        verify(roleFacade).findById(1L);
        verify(roleFacade).findById(3L);
    }

    @Test
    public void deleteRoleTest() throws Exception {
        mockMvc.perform(delete("/roles/1"))
                .andExpect(status().isOk());

        verify(roleFacade).removeRole(1L);
    }

    @Test
    public void createRole() throws Exception {
        CreateRoleDTO createRoleDTO = new CreateRoleDTO("Assassin", "Attack Damage Carry", Power.MARTIAL_ARTS, 40, 30);

        doReturn(createRoles().get(0)).when(roleFacade).createRole(any());

        mockMvc.perform(post("/roles/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(createRoleDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Assassin"));

        verify(roleFacade).createRole(any());
    }

    @Test
    public void editRoleTest() throws Exception {
        RoleDTO roleDTO = new RoleDTO(1L, "Assassin", "Attack Damage Carry", Power.MARTIAL_ARTS, 40, 30);

        doReturn(roleDTO).when(roleFacade).updateRole(any());

        mockMvc.perform(put("/roles/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(roleDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Assassin"));

        verify(roleFacade).updateRole(any());
    }

    private List<RoleDTO> createRoles() {
        RoleDTO assassin = new RoleDTO(1L, "Assassin", "Attack Damage Carry", Power.MARTIAL_ARTS, 40, 30);
        RoleDTO marksman = new RoleDTO(2L, "Marksman", "Attack Damage Carry", Power.WEAPONS, 50, 10);
        RoleDTO tank = new RoleDTO(3L, "Tank", "Has tanking powers", Power.WEAPONS, 15, 20);

        return Arrays.asList(assassin, marksman, tank);
    }

    private static String convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsString(object);
    }
}
