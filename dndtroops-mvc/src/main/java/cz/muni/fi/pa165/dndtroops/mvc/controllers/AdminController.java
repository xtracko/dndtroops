package cz.muni.fi.pa165.dndtroops.mvc.controllers;

import cz.muni.fi.pa165.dndtroops.dto.AdminDTO;
import cz.muni.fi.pa165.dndtroops.dto.TroopDTO;
import cz.muni.fi.pa165.dndtroops.facade.TroopFacade;
import cz.muni.fi.pa165.dndtroops.mvc.models.BattleModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Jiří Novotný
 */

@Controller
@RequestMapping("/admin")
public class AdminController {
    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private TroopFacade troopFacade;

    /**
     * Method for battle page initialization
     * @param model
     * @param redirectAttributes
     * @param req
     * @return initialized page
     */
    @RequestMapping(value = "/battle", method = RequestMethod.GET)
    public String battle(Model model, RedirectAttributes redirectAttributes,HttpServletRequest req) {
        log.debug("battle()");
        model.addAttribute("authenticatedUser", (AdminDTO) req.getSession().getAttribute("authenticatedUser"));
        
        if(!isAuthenticated(req, redirectAttributes, true)){
                redirectAttributes.addFlashAttribute("alert_danger", "You dont have rights for this action. Please login as administrator.");
                return "redirect:/auth/login";
        }
        List<TroopDTO> troops = troopFacade.findAllTroops();

        if (troops.isEmpty()) {
            redirectAttributes.addFlashAttribute("alert_danger", "No troops to battle. Create a couple of troops first.");
        }
        
        model.addAttribute("notroops", troops.isEmpty());
        model.addAttribute("troops", troops);
        model.addAttribute("battle", new BattleModel());

        return "admin/battle";
    }

    /**
     * Method for troop battle
     * @param battle
     * @param model
     * @param redirectAttributes
     * @param req
     * @return path to page with scores
     */
    @RequestMapping(value = "/battle", method = RequestMethod.POST)
    public String battle(@ModelAttribute("battle") BattleModel battle, Model model, RedirectAttributes redirectAttributes, HttpServletRequest req) {
        try {
            log.debug("battle(troop1={}, troop2={})", battle.getTroop1(), battle.getTroop2());

            TroopDTO troop1 = troopFacade.findTroopById(battle.getTroop1());
            TroopDTO troop2 = troopFacade.findTroopById(battle.getTroop2());
            model.addAttribute("authenticatedUser", (AdminDTO) req.getSession().getAttribute("authenticatedUser"));
            
            if (troop1 == null) {
                redirectAttributes.addFlashAttribute("alert_danger", "Troop with ID " + battle.getTroop1() + " does not exists.");
                return "redirect:/admin/battle";
            }
            if (troop2 == null) {
                redirectAttributes.addFlashAttribute("alert_danger", "Troop with ID " + battle.getTroop2() + " does not exists.");
                return "redirect:/admin/battle";
            }

            if (troop1.getId().equals(troop2.getId())) {
                redirectAttributes.addFlashAttribute("alert_danger", "Troop " + troop1.getName() + " cannot battle with itself.");
                return "redirect:/admin/battle";
            }

            TroopDTO winner = troopFacade.battle(troop1, troop2);
            redirectAttributes.addFlashAttribute("alert_success", "Battle between " + troop1.getName() + " and " + troop2.getName() + " has been successfully  executed. The winner is " + winner.getName() + ".");
        }
        catch(Exception ex) {
            log.warn("cannot perform battle");
            redirectAttributes.addFlashAttribute("alert_danger", "Battle between troops with IDs " + battle.getTroop1() + " and " + battle.getTroop2() + " was not performed.");
            return "redirect:/admin/battle";
        }

        return "redirect:/scores";
    }
    /**
     * Method for checking if user is authenticated and if have correct access rights
     * @param shouldBeAdmin true if method should check admin rights
     * @param redirectAttributes
     * @param req
     * @return true if user is authenticated and ahve correct access rigths
     */
    private boolean isAuthenticated(HttpServletRequest req, RedirectAttributes redirectAttributes,
                                    Boolean shouldBeAdmin) {
        AdminDTO authUser = (AdminDTO) req.getSession().getAttribute("authenticatedUser");
        if (authUser == null) {
            if (redirectAttributes != null) {
                redirectAttributes.addFlashAttribute("alert_danger", "Admin role required");
            }
            

            log.error("user should be authenticated or admin for this operation");
            return false;
        }

        return shouldBeAdmin? authUser.isIsAdmin():true;
    }
}