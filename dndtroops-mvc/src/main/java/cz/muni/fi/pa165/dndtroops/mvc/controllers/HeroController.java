package cz.muni.fi.pa165.dndtroops.mvc.controllers;

import cz.muni.fi.pa165.dndtroops.dto.CreateRoleDTO;
import cz.muni.fi.pa165.dndtroops.dto.HeroCreateDTO;
import cz.muni.fi.pa165.dndtroops.dto.HeroDTO;
import cz.muni.fi.pa165.dndtroops.dto.RoleDTO;
import cz.muni.fi.pa165.dndtroops.enums.Power;
import cz.muni.fi.pa165.dndtroops.facade.HeroFacade;
import cz.muni.fi.pa165.dndtroops.facade.RoleFacade;
import cz.muni.fi.pa165.dndtroops.facade.TroopFacade;
import cz.muni.fi.pa165.dndtroops.mvc.forms.CreateHeroDTOValidator;
import cz.muni.fi.pa165.dndtroops.mvc.forms.CreateRoleDTOValidator;
import cz.muni.fi.pa165.dndtroops.mvc.forms.HeroDTOValidator;
import cz.muni.fi.pa165.dndtroops.mvc.forms.RoleDTOValidator;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;
/**
 *
 * @author Martin Sestak
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
        if (binder.getTarget() instanceof HeroDTO) {
            binder.addValidators(new HeroDTOValidator());
        }
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        
            log.debug("list()");
            model.addAttribute("heroes", heroFacade.getAllHeroes());
        

        return "hero/list";
    }
    
     @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        log.debug("create()");
        
        model.addAttribute("roles", roleFacade.getAllRoles());
        try {
            model.addAttribute("troops", troopFacade.findAllTroops());
        } catch (Exception ex) {
            model.addAttribute("troops", new ArrayList<>());
            model.addAttribute("alert_danger", "Ooops - " + ex.getMessage());
        }
        
               
        
        model.addAttribute("data", new HeroCreateDTO());
       
        return "hero/create";
    }
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@RequestParam("troop") String troop,@RequestParam(value = "roles", required = false) String[] roles, @Valid @ModelAttribute("data")  HeroCreateDTO data, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        log.debug("create(data={})", data);
        
        long troopId = Long.valueOf(troop);        
        data.setTroop(troopFacade.findTroopById(troopId));
        
        if (roles != null){
            for (String id: roles) {           
                long roleId = Long.valueOf(id);//Do your stuff here
                data.addRole(roleFacade.findById(roleId));
            }
        
        }
        if (bindingResult.hasErrors() && (data.getRoles().isEmpty() || data.getTroop()== null || data.getName().isEmpty())) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) { 
                log.trace("ObjectError: {}", ge); 
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                if(fe.getField().toString().equalsIgnoreCase("name")){
                    model.addAttribute(fe.getField() + "_error","dsdsds");
                    log.trace("FieldError: {}", fe);
                }
                
            }
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
    
}
