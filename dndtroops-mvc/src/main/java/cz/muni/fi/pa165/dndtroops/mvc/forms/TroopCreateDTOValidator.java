package cz.muni.fi.pa165.dndtroops.mvc.forms;

import cz.muni.fi.pa165.dndtroops.dto.TroopCreateDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Jiří Novotný
 *
 * Before the 2nd milestone we have lost a teamate who was responsible for Troop entity.I am only doing the minimal
 * possible work to get the project running. Please be mindfull of this, when you are writing your evaluation.
 */

public class TroopCreateDTOValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return TroopCreateDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required");

        TroopCreateDTO troopCreateDTO = (TroopCreateDTO) target;

        if (troopCreateDTO.getGoldenMoney() < 0) {
            errors.rejectValue("goldenMoney", "field.non-negative");
        }
    }
}
