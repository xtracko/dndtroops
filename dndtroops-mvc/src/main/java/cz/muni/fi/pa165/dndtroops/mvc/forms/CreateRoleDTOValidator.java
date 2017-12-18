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
        if (Float.compare(createRoleDTO.getDamageVariance(), 0) < 0)
            errors.rejectValue("damageVariance", "field.non-negative");
    }
}
