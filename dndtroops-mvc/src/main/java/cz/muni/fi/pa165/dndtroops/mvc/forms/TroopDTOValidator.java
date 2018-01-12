package cz.muni.fi.pa165.dndtroops.mvc.forms;

import cz.muni.fi.pa165.dndtroops.dto.TroopDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Class for validation of TroopDTO
 * @author Jiří Novotný
 *
 * Before the 2nd milestone we have lost a teamate who was responsible for Troop entity.I am only doing the minimal
 * possible work to get the project running. Please be mindfull of this, when you are writing your evaluation.
 */

public class TroopDTOValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return TroopDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "id", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required");

        TroopDTO troopDTO = (TroopDTO) target;

        if (troopDTO.getGoldenMoney() < 0) {
            errors.rejectValue("goldenMoney", "field.value_non-negative");
        }
    }
}
