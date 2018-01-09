package cz.muni.fi.pa165.dndtroops.rest.controllers;

import cz.muni.fi.pa165.dndtroops.dto.CreateRoleDTO;
import cz.muni.fi.pa165.dndtroops.dto.RoleDTO;
import cz.muni.fi.pa165.dndtroops.enums.Power;
import cz.muni.fi.pa165.dndtroops.facade.RoleFacade;
import cz.muni.fi.pa165.dndtroops.rest.ApiUris;
import cz.muni.fi.pa165.dndtroops.rest.exceptions.InvalidParameterException;
import cz.muni.fi.pa165.dndtroops.rest.exceptions.ResourceAlreadyExistingException;
import cz.muni.fi.pa165.dndtroops.rest.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Jiří Novotný
 */

@RestController
@RequestMapping(ApiUris.ROOT_URI_ROLES)
public class RolesController {
    private static final Logger logger = LoggerFactory.getLogger(RolesController.class);

    @Inject
    private RoleFacade roleFacade;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<RoleDTO> getRoles(@RequestParam(value = "power", required = false) String power) {
        logger.debug("REST getRoles({})", (power != null) ? power : "");

        if (power == null) {
            return roleFacade.getAllRoles();
        }

        if (!Power.contains(power)) {
            throw new InvalidParameterException();
        }

        return roleFacade.getAllRolesByPower(Power.valueOf(power));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final RoleDTO getRole(@PathVariable("id") long id) {
        logger.debug("REST getRole({})", id);

        RoleDTO roleDTO = roleFacade.findById(id);
        if (roleDTO == null) {
            throw new ResourceNotFoundException();
        }
        return roleDTO;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteRole(@PathVariable("id") long id) {
        logger.debug("REST removeRole({})", id);

        try {
            roleFacade.deleteRole(id);
        } catch (Exception ex) {
            logger.warn(ex.getMessage());
            throw  new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final RoleDTO createRole(@RequestBody CreateRoleDTO role) {
        logger.debug("REST createRole()");

        try {
            return roleFacade.createRole(role);
        } catch (Exception ex) {
            throw new ResourceAlreadyExistingException();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final RoleDTO editRole(@PathVariable("id") long id, @RequestBody RoleDTO role) {
        logger.debug("REST editRole()");

        if (id != role.getId()) {
            throw new InvalidParameterException();
        }

        try {
            return roleFacade.editRole(role);
        } catch(Exception ex) {
            logger.warn(ex.getMessage());
            throw new ResourceNotFoundException();
        }
    }
}
