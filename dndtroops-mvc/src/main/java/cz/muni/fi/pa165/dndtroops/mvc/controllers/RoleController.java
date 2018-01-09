package cz.muni.fi.pa165.dndtroops.mvc.controllers;

import cz.muni.fi.pa165.dndtroops.dto.AdminDTO;
import cz.muni.fi.pa165.dndtroops.dto.CreateRoleDTO;
import cz.muni.fi.pa165.dndtroops.dto.RoleDTO;
import cz.muni.fi.pa165.dndtroops.enums.Power;
import cz.muni.fi.pa165.dndtroops.facade.RoleFacade;
import cz.muni.fi.pa165.dndtroops.mvc.forms.CreateRoleDTOValidator;
import cz.muni.fi.pa165.dndtroops.mvc.forms.RoleDTOValidator;
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
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;

/**
 * @author Jiří Novotný
 */

@Controller
@RequestMapping("/role")
public class RoleController {
    private static final Logger log = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleFacade roleFacade;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        if (binder.getTarget() instanceof CreateRoleDTO) {
            binder.addValidators(new CreateRoleDTOValidator());
        }
        if (binder.getTarget() instanceof RoleDTO) {
            binder.addValidators(new RoleDTOValidator());
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(@RequestParam(required = false) String power, Model model,HttpServletRequest req) {
        model.addAttribute("authenticatedUser", (AdminDTO) req.getSession().getAttribute("authenticatedUser"));
        
        if (power == null) {
            log.debug("list()");
            model.addAttribute("roles", roleFacade.findAllRoles());
        } else if (Power.contains(power)) {
            log.debug("list(power={})", power);
            model.addAttribute("roles", roleFacade.findAllRolesByPower(Power.valueOf(power)));
        } else {
            model.addAttribute("roles", new ArrayList<>());
            model.addAttribute("alert_danger", "Unknown power " + power + ".");
        }

        return "role/list";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model,HttpServletRequest req) {
        log.debug("create()");
        
        model.addAttribute("authenticatedUser", (AdminDTO) req.getSession().getAttribute("authenticatedUser"));
        model.addAttribute("data", new CreateRoleDTO());
        return "role/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("data") CreateRoleDTO data, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder,HttpServletRequest req) {
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
            return "role/create";
        }

        try {
            RoleDTO role = roleFacade.createRole(data);
            redirectAttributes.addFlashAttribute("alert_success", "Role \"" + role.getName() + "\" was created");
            return "redirect:/role/list";
        } catch (Exception ex) {
            log.warn("cannot create role");
            redirectAttributes.addFlashAttribute("alert_danger", "Cannot create role. Reason: " + ex.getMessage());
            return "redirect:/role/create";
        }
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable long id, Model model, RedirectAttributes redirectAttributes,HttpServletRequest req) {
        log.debug("edit(id={})", id);
        model.addAttribute("authenticatedUser", (AdminDTO) req.getSession().getAttribute("authenticatedUser"));
        RoleDTO role = roleFacade.findById(id);

        if (role == null) {
            redirectAttributes.addFlashAttribute("alert_danger", "Role with ID " + id + " does not exists.");
            return "redirect:/role/list";
        }

        model.addAttribute("role", role);
        return "role/edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable long id, @Valid @ModelAttribute("data") RoleDTO data, BindingResult bindingResult, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes,HttpServletRequest req) {
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
            return "role/edit";
        }

        try {
            roleFacade.updateRole(data);
            redirectAttributes.addFlashAttribute("alert_success", "Role with ID " + id + " was successfully edited.");
        } catch (Exception ex) {
            log.warn("cannot edit role with ID {}", id);
            redirectAttributes.addFlashAttribute("alert_danger", "Cannot edit Role with ID " + id + ". Reason: " + ex.getMessage());
        }

        return "redirect:/role/list";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes,HttpServletRequest req) {
        log.debug("delete(id={})", id);
        model.addAttribute("authenticatedUser", (AdminDTO) req.getSession().getAttribute("authenticatedUser"));
        try {
            roleFacade.removeRole(id);
            redirectAttributes.addFlashAttribute("alert_success", "Role successfully was deleted.");
        } catch (Exception ex) {
            log.warn("cannot delete role with ID {}", id);
            redirectAttributes.addFlashAttribute("alert_danger", "Cannot delete role with ID " + id + ". Reason: " + ex.getMessage());
        }

        return "redirect:/role/list";
    }
}
