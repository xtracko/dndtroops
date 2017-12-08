package cz.muni.fi.pa165.dndtroops.rest.controllers;

import cz.muni.fi.pa165.dndtroops.rest.ApiUris;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jiří Novotný
 */

@RestController
public class MainController {
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Map<String, String> getResources() {
        Map<String, String> resourcesMap = new HashMap<>();

        resourcesMap.put("roles_uri", ApiUris.ROOT_URI_ROLES);
        resourcesMap.put("users_uri", ApiUris.ROOT_URI_USERS);
        resourcesMap.put("heroes_uri", ApiUris.ROOT_URI_HEROES);
        resourcesMap.put("troops_uri", ApiUris.ROOT_URI_TROOPS);

        return Collections.unmodifiableMap(resourcesMap);
    }
}
