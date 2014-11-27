package ro.InnovaTeam.cemeteryApp.controller.cemetery;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ro.InnovaTeam.cemeteryApp.CemeteryDTO;

/**
 * Created by Catalin Sorecau on 11/24/2014.
 */
@Component
@Qualifier("cemeteryValidator")
public class CemeteryValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return CemeteryDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "empty.field");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "empty.field");
    }
}
