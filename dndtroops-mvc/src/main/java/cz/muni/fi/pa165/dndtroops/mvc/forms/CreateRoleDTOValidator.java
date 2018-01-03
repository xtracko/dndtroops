package cz.muni.fi.pa165.dndtroops.mvc.forms;

import cz.muni.fi.pa165.dndtroops.dto.CreateRoleDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Jiří Novotný
 */

public class CreateRoleDTOValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return CreateRoleDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required");

        CreateRoleDTO createRoleDTO = (CreateRoleDTO) target;

        if (Float.compare(createRoleDTO.getDamage(), 1) < 0) {
            errors.rejectValue("damage", "field.value_at_least_one");
        }

        if (Float.compare(createRoleDTO.getCooldown(), 0) < 0) {
            errors.rejectValue("cooldown", "field.value_non-negative");
        }
    }
}
