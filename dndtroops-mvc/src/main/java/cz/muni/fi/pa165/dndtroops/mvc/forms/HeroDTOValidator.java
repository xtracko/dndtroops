package cz.muni.fi.pa165.dndtroops.mvc.forms;

import cz.muni.fi.pa165.dndtroops.dto.HeroDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Class for validation of HeroDTO
 * @author Martin
 */
public class HeroDTOValidator implements Validator{

    @Override
    public boolean supports(Class<?> type) {
        return HeroDTO.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required");
      

        HeroDTO heroDTO = (HeroDTO) o;
        if (Float.compare(heroDTO.getHealth(), 0) < 0)
            errors.rejectValue("health", "field.value_non-negative");
    }
    
}
