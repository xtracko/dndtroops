package cz.muni.fi.pa165.dndtroops.mvc.controllers;

import cz.muni.fi.pa165.dndtroops.dto.AdminDTO;
import cz.muni.fi.pa165.dndtroops.dto.HeroDTO;
import cz.muni.fi.pa165.dndtroops.facade.HeroFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Jiří Novotný
 */

@Controller
public class RootController {
    private static final Logger log = LoggerFactory.getLogger(RootController.class);

    @Autowired
    private HeroFacade heroFacade;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model, HttpServletRequest req) {
        log.debug("home()");

        model.addAttribute("authenticatedUser", (AdminDTO) req.getSession().getAttribute("authenticatedUser"));

        return "home";
    }

    @RequestMapping(value = "/scores", method = RequestMethod.GET)
    public String scores(Model model,HttpServletRequest req) {
        log.debug("scores()");

        List<HeroDTO> heroes = heroFacade.getAllHeroes();

        heroes.sort(Collections.reverseOrder(
                Comparator.comparingLong(hero -> hero.getTroop().getGoldenMoney())
                )
        );
        model.addAttribute("authenticatedUser", (AdminDTO) req.getSession().getAttribute("authenticatedUser"));
        
        model.addAttribute("heroes", heroes);

        return "scores";
    }
}
