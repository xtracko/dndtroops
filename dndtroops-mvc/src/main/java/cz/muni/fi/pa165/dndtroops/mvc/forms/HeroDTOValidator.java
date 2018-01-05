package cz.muni.fi.pa165.dndtroops.mvc.forms;

import cz.muni.fi.pa165.dndtroops.dto.HeroDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author Martin
 */
public class HeroDTOValidator implements Validator{

    @Override
    public boolean supports(Class<?> type) {
        return HeroDTO.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        System.out.println("*************hero******"); 
      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required");
      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roles", "fielxxxxxxxxxxx");
      

        HeroDTO heroDTO = (HeroDTO) o;
        System.out.println("************"+o.toString()+"*******"); 
        if (Float.compare(heroDTO.getHealth(), 0) < 0)
            errors.rejectValue("damageVariance", "field.non-negative");
    }
    
}
