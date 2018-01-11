package cz.muni.fi.pa165.dndtroops.mvc.controllers;

import cz.muni.fi.pa165.dndtroops.dto.AdminDTO;
import cz.muni.fi.pa165.dndtroops.dto.UserAuthenticateDTO;
import cz.muni.fi.pa165.dndtroops.facade.AdminFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Martin Sestak
 */
@Controller
@RequestMapping("/auth")
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    
    @Autowired
    private AdminFacade adminFacade;
    
    /**
     * Method for login page initialization
     * @param req
     * @return initialized page
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String authForm(HttpServletRequest req) {
      log.info("GET request: /auth/login");
        HttpSession session = req.getSession(true);
        if (session.getAttribute("authenticatedUser") != null) {
            return null;
        }

        return "auth/login";
    }
    
    /**
     * Method for login processing
     * @param username
     * @param password
     * @param redirectAttributes
     * @param req
     * @return hero list page after sucessfull authentication 
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String authenticate(
            @RequestParam String username,
            @RequestParam String password,
            RedirectAttributes redirectAttributes,
            HttpServletRequest req) {
        log.info("POST request: /auth/login");
        UserAuthenticateDTO userAuthenticateDTO = new UserAuthenticateDTO();
        userAuthenticateDTO.setPasswordHash(password);
        userAuthenticateDTO.setName(username);
        
        if (adminFacade.findAdministratorByName(username) == null || !adminFacade.authenticate(userAuthenticateDTO)) {
            redirectAttributes.addFlashAttribute("alert_info", "Invalid login email or password.");
            return "redirect:/auth/login";
        }

        HttpSession session = req.getSession(true);
        AdminDTO adminDTO = adminFacade.findAdministratorByName(username);
        boolean ad = adminFacade.isAdmin(adminDTO);
        adminDTO.setIsAdmin(ad);
        session.setAttribute("authenticatedUser", adminDTO);
        redirectAttributes.addFlashAttribute("alert_info", "You have been logged in.");


        return adminDTO.isIsAdmin() ? "redirect:/hero/list" : "redirect:/hero/list";
    }
    
    /**
     * Method for logout
     * @param req
     * @param redirectAttributes
     * @return home directory
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest req,
                         RedirectAttributes redirectAttributes) {
        log.info("GET request: /auth/logout");
        HttpSession session = req.getSession(true);
        session.removeAttribute("authenticatedUser");
        redirectAttributes.addFlashAttribute("alert_info", "You have been successfully logged out.");
        return "redirect:/";
    }
}
