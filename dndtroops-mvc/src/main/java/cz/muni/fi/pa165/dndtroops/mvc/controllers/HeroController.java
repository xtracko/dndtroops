package cz.muni.fi.pa165.dndtroops.mvc.controllers;

import cz.muni.fi.pa165.dndtroops.dto.*;
import cz.muni.fi.pa165.dndtroops.facade.HeroFacade;
import cz.muni.fi.pa165.dndtroops.facade.RoleFacade;
import cz.muni.fi.pa165.dndtroops.facade.TroopFacade;
import cz.muni.fi.pa165.dndtroops.mvc.forms.CreateHeroDTOValidator;
import cz.muni.fi.pa165.dndtroops.mvc.forms.HeroDTOValidator;
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
import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Martin Sestak and Jiří Novotný (hero edit functionality)
 */
@Controller
@RequestMapping("/hero")
public class HeroController {
    private static final Logger log = LoggerFactory.getLogger(HeroController.class);

    @Autowired
    private HeroFacade heroFacade;

    @Autowired
    private RoleFacade roleFacade;

    @Autowired
    private TroopFacade troopFacade;


    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        if (binder.getTarget() instanceof HeroCreateDTO) {
            binder.addValidators(new CreateHeroDTOValidator());
        }
        if (binder.getTarget() instanceof HeroCreateDTO) {
            binder.addValidators(new CreateHeroDTOValidator());
            binder.registerCustomEditor(TroopDTO.class, new PropertyEditorSupport() {
                @Override
                public void setAsText(String text) throws IllegalArgumentException {
                    TroopDTO troop = troopFacade.findTroopById(Long.parseLong(text));
                    setValue(troop);
                }

                @Override
                public String getAsText() {
                    TroopDTO troop = (TroopDTO) getValue();
                    return (troop != null ? troop.getId().toString() : null);
                }
            });
            binder.registerCustomEditor(RoleDTO.class, new PropertyEditorSupport() {
                @Override
                public void setAsText(String text) throws IllegalArgumentException {
                    RoleDTO role = roleFacade.findById(Long.parseLong(text));
                    setValue(role);
                }

                @Override
                public String getAsText() {
                    RoleDTO role = (RoleDTO) getValue();
                    return (role != null ? role.getId().toString() : null);
                }
            });
        }

        if (binder.getTarget() instanceof HeroDTO) {
            binder.addValidators(new HeroDTOValidator());
            binder.registerCustomEditor(TroopDTO.class, new PropertyEditorSupport() {
                @Override
                public void setAsText(String text) throws IllegalArgumentException {
                    TroopDTO troop = troopFacade.findTroopById(Long.parseLong(text));
                    setValue(troop);
                }

                @Override
                public String getAsText() {
                    TroopDTO troop = (TroopDTO) getValue();
                    return (troop != null ? troop.getId().toString() : null);
                }
            });
            binder.registerCustomEditor(RoleDTO.class, new PropertyEditorSupport() {
                @Override
                public void setAsText(String text) throws IllegalArgumentException {
                    RoleDTO role = roleFacade.findById(Long.parseLong(text));
                    setValue(role);
                }

                @Override
                public String getAsText() {
                    RoleDTO role = (RoleDTO) getValue();
                    return (role != null ? role.getId().toString() : null);
                }
            });
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model,
                       HttpServletRequest req,
                       RedirectAttributes redirectAttributes) {
        log.debug("list()");

        model.addAttribute("authenticatedUser", (AdminDTO) req.getSession().getAttribute("authenticatedUser"));
        model.addAttribute("heroes", heroFacade.getAllHeroes());

        return "hero/list";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model, HttpServletRequest req,RedirectAttributes redirectAttributes) {
        log.debug("create()");

        model.addAttribute("authenticatedUser", (AdminDTO) req.getSession().getAttribute("authenticatedUser"));
        model.addAttribute("roles", roleFacade.getAllRoles());
        try {
            List<TroopDTO> troops = troopFacade.findAllTroops();
            
            if(troops.isEmpty()){
                redirectAttributes.addFlashAttribute("alert_danger", "No troop is currently created in the system, please create troop before creating new hero");
                return "redirect:/troop/create";
            }
            
            model.addAttribute("troops", troops);
        } catch (Exception ex) {
            model.addAttribute("troops", new ArrayList<>());
            model.addAttribute("alert_danger", "Ooops - " + ex.getMessage());
            return "redirect:/hero/list";
        }


        model.addAttribute("data", new HeroCreateDTO());

        return "hero/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("data") HeroCreateDTO data, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, HttpServletRequest req, UriComponentsBuilder uriBuilder) {
        log.debug("create(data={})", data);



        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {

                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            model.addAttribute("authenticatedUser", (AdminDTO) req.getSession().getAttribute("authenticatedUser"));
            model.addAttribute("roles", roleFacade.getAllRoles());
            model.addAttribute("troops", troopFacade.findAllTroops());
            return "hero/create";
        }

        try {
            HeroDTO hero = heroFacade.createHero(data);
            redirectAttributes.addFlashAttribute("alert_success", "Hero \"" + hero.getName() + "\" was created");
            return "redirect:/hero/list";
        } catch (Exception ex) {
            log.warn("cannot create hero");
            redirectAttributes.addFlashAttribute("alert_danger", "Cannot create hero. Reason: " + ex.getMessage());
            return "redirect:/hero/create";
        }
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable long id, Model model, RedirectAttributes redirectAttributes, HttpServletRequest req) {
        log.debug("edit(id={})", id);

        HeroDTO hero = heroFacade.getHeroById(id);

        if (hero == null) {
            redirectAttributes.addFlashAttribute("alert_danger", "Hero with ID " + id + " does not exists.");
            return "redirect:/hero/list";
        }
        model.addAttribute("authenticatedUser", (AdminDTO) req.getSession().getAttribute("authenticatedUser"));
        model.addAttribute("roles", roleFacade.getAllRoles());
        model.addAttribute("troops", troopFacade.findAllTroops());
        model.addAttribute("hero", hero);
        return "hero/edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable long id, @Valid @ModelAttribute("hero") HeroDTO hero, BindingResult bindingResult, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes, HttpServletRequest req) {
        log.debug("edit(id={}, data={})", id, hero);

        model.addAttribute("authenticatedUser", (AdminDTO) req.getSession().getAttribute("authenticatedUser"));

        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "hero/edit";
        }

        try {
            heroFacade.updateHero(hero);
            redirectAttributes.addFlashAttribute("alert_success", "Hero with ID " + id + " was successfully edited.");
        } catch (Exception ex) {
            log.warn("cannot edit role with ID {}", id);

            redirectAttributes.addFlashAttribute("alert_danger", "Cannot edit Hero with ID " + id + ". Reason: " + ex.getMessage());
            return "redirect:/hero/edit";
        }

        return "redirect:/hero/list";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes, HttpServletRequest req) {
        HeroDTO hero = heroFacade.getHeroById(id);
        heroFacade.deleteHero(hero);
        log.debug("delete({})", id);
        model.addAttribute("authenticatedUser", (AdminDTO) req.getSession().getAttribute("authenticatedUser"));
        redirectAttributes.addFlashAttribute("alert_success", "Hero \"" + hero.getName() + "\" was deleted.");
        return "redirect:" + uriBuilder.path("/hero/list").toUriString();
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

        return !shouldBeAdmin || authUser.isIsAdmin();
    }
}
