package cz.muni.fi.pa165.dndtroops.mvc.controllers;

import cz.muni.fi.pa165.dndtroops.dto.HeroDTO;
import cz.muni.fi.pa165.dndtroops.facade.HeroFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Jiří Novotný
 */

@Controller
public class HighscoresController {
    private static final Logger log = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private HeroFacade heroFacade;

    @RequestMapping(value = "/highscores", method = RequestMethod.GET)
    public String highscores(Model model) {
        List<HeroDTO> heroes = heroFacade.getAllHeroes();

        heroes.sort(Collections.reverseOrder(Comparator.comparingLong(hero -> hero.getTroop().getGoldenMoney())));
        model.addAttribute("heroes", heroes);

        return "highscores";
    }
}
