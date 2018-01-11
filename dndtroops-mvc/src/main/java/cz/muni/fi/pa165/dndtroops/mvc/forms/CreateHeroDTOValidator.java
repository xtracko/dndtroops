package cz.muni.fi.pa165.dndtroops.mvc.forms;


import cz.muni.fi.pa165.dndtroops.dto.HeroCreateDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
/**
 *  Class for validation of CreateHeroDTO
 * @author Martin Sestak
 */
public class CreateHeroDTOValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return HeroCreateDTO.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required");

        HeroCreateDTO heroCreateDTO = (HeroCreateDTO) o;
        if (Float.compare(heroCreateDTO.getHealth(), 0) < 0)
            errors.rejectValue("damageVariance", "field.non-negative");
    }
    
}
