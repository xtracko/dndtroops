package cz.muni.fi.pa165.dndtroops.mvc.controllers;

import cz.muni.fi.pa165.dndtroops.dto.AdminDTO;
import cz.muni.fi.pa165.dndtroops.dto.TroopCreateDTO;
import cz.muni.fi.pa165.dndtroops.dto.TroopDTO;
import cz.muni.fi.pa165.dndtroops.facade.HeroFacade;
import cz.muni.fi.pa165.dndtroops.facade.TroopFacade;
import cz.muni.fi.pa165.dndtroops.mvc.forms.TroopCreateDTOValidator;
import cz.muni.fi.pa165.dndtroops.mvc.forms.TroopDTOValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Jiří Novotný
 *
 * Before the 2nd milestone we have lost a teamate who was responsible for Troop entity.I am only doing the minimal
 * possible work to get the project running. Please be mindfull of this, when you are writing your evaluation.
 */

@Controller
@RequestMapping("/troop")
public class TroopController {
    private static final Logger log = LoggerFactory.getLogger(TroopController.class);

    @Autowired
    private TroopFacade troopFacade;

    @Autowired
    private HeroFacade heroFacade;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        if (binder.getTarget() instanceof TroopCreateDTO) {
            binder.addValidators(new TroopCreateDTOValidator());
        }
        if (binder.getTarget() instanceof TroopDTO) {
            binder.addValidators(new TroopDTOValidator());
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model,HttpServletRequest req, RedirectAttributes redirectAttributes) {
        log.debug("list()");
        
        model.addAttribute("authenticatedUser", (AdminDTO) req.getSession().getAttribute("authenticatedUser"));
        
        if(!isAuthenticated(req, redirectAttributes, true)){
                redirectAttributes.addFlashAttribute("alert_danger", "You dont have rights for this action. Please login.");
                return "redirect:/auth/login";
        }
        
        try {
            model.addAttribute("troops", troopFacade.findAllTroops());
        } catch (Exception ex) {
            model.addAttribute("troops", new ArrayList<>());
            model.addAttribute("alert_danger", "Ooops - " + ex.getMessage());
        }
        return "troop/list";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model,HttpServletRequest req, RedirectAttributes redirectAttributes) {
        log.debug("create()");
        
        model.addAttribute("authenticatedUser", (AdminDTO) req.getSession().getAttribute("authenticatedUser"));
        
        if(!isAuthenticated(req, redirectAttributes, true)){
                redirectAttributes.addFlashAttribute("alert_danger", "You dont have rights for this action. Please login as administrator.");
                return "redirect:/auth/login";
        }
        
        model.addAttribute("data", new TroopCreateDTO());
        return "troop/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("data") TroopCreateDTO data, BindingResult bindingResult, Model model,
                         RedirectAttributes redirectAttributes,HttpServletRequest req) {
        log.debug("create(data={})", data);

        model.addAttribute("authenticatedUser", (AdminDTO) req.getSession().getAttribute("authenticatedUser"));
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "troop/create";
        }

        try {
            TroopDTO troop = troopFacade.createTroop(data);
            redirectAttributes.addFlashAttribute("alert_success", "Role \"" + troop.getName() + "\" was created");
            return "redirect:/troop/list";
        } catch (Exception ex) {
            log.warn("cannot create troop");
            redirectAttributes.addFlashAttribute("alert_danger", "Cannot create troop. Reason: " + ex.getMessage());
            return "redirect:/troop/create";
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, Model model, RedirectAttributes redirectAttributes,HttpServletRequest req) {
        log.debug("delete(id={})", id);
        
        model.addAttribute("authenticatedUser", (AdminDTO) req.getSession().getAttribute("authenticatedUser"));
        if(!isAuthenticated(req, redirectAttributes, true)){
                redirectAttributes.addFlashAttribute("alert_danger", "You dont have rights for this action. Please login as administrator.");
                return "redirect:/auth/login";
        }
        try {
            troopFacade.removeTroop(id);
            redirectAttributes.addFlashAttribute("alert_success", "Troop was successfully deleted.");
        } catch (Exception ex) {
            log.warn("cannot delete troop with ID {}", id);
            redirectAttributes.addFlashAttribute("alert_danger", "Cannot delete troop with ID " + id + ". This also could mean that the Troop owns some heroes and you should remove the heroes before deleting the troop. Reason: " + ex.getMessage());
        }

        return "redirect:/troop/list";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable long id, Model model, RedirectAttributes redirectAttributes,HttpServletRequest req) {
        log.debug("edit(id={})", id);

        TroopDTO troop = troopFacade.findTroopById(id);
        model.addAttribute("authenticatedUser", (AdminDTO) req.getSession().getAttribute("authenticatedUser"));
        
        if(!isAuthenticated(req, redirectAttributes, true)){
                redirectAttributes.addFlashAttribute("alert_danger", "You dont have rights for this action. Please login as administrator.");
                return "redirect:/auth/login";
        }
        if (troop == null) {
            redirectAttributes.addFlashAttribute("alert_danger", "Troop with ID " + id + " does not exists.");
            return "redirect:/troop/list";
        }

        model.addAttribute("data", troop);
        return "troop/edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable long id, @Valid @ModelAttribute("data") TroopDTO data, BindingResult bindingResult,
                       Model model, RedirectAttributes redirectAttributes,HttpServletRequest req) {
        log.debug("edit(id={}, data={})", id, data);
        
        model.addAttribute("authenticatedUser", (AdminDTO) req.getSession().getAttribute("authenticatedUser"));
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "troop/edit";
        }

        try {
            troopFacade.updateTroop(data);
            redirectAttributes.addFlashAttribute("alert_success", "Troop with ID " + id + " was successfully edited.");
        } catch (Exception ex) {
            log.warn("cannot edit troop with ID {}", id);
            redirectAttributes.addFlashAttribute("alert_danger", "Cannot edit troop with ID " + id + ". Reason: " + ex.getMessage());
        }

        return "redirect:/troop/list";
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model, RedirectAttributes redirectAttributes,HttpServletRequest req) {
        log.debug("view(id={})", id);

        TroopDTO troop = troopFacade.findTroopById(id);
        
        model.addAttribute("authenticatedUser", (AdminDTO) req.getSession().getAttribute("authenticatedUser"));
        
        if(!isAuthenticated(req, redirectAttributes, true)){
                redirectAttributes.addFlashAttribute("alert_danger", "You dont have rights for this action. Please login.");
                return "redirect:/auth/login";
        }
        
        if (troop == null) {
            redirectAttributes.addFlashAttribute("alert_danger", "Cannot troop with ID " + id + " does not exists");
            return "redirect:/troop/list";
        }

        model.addAttribute("troop", troop);
        model.addAttribute("heroes", heroFacade.findHeroesByTroop(troop));

        return "troop/view";
    }
    
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