package ro.InnovaTeam.cemeteryApp.controller.deceased;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ro.InnovaTeam.cemeteryApp.DeceasedDTO;
import ro.InnovaTeam.cemeteryApp.restClient.GraveRestClient;
import ro.InnovaTeam.cemeteryApp.util.ValidatorUtil;

/**
 * Created by Catalin Sorecau on 11/30/2014.
 */
@Component
@Qualifier("deceasedValidator")
public class DeceasedValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return DeceasedDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        DeceasedDTO deceasedDTO = (DeceasedDTO) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "empty.field");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "empty.field");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cnp", "empty.field");

        if (!ValidatorUtil.isValidCNP(deceasedDTO.getCnp())) {
            errors.rejectValue("cnp", "cnp.invalid");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "religion", "empty.field");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "diedOn", "empty.field");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "burialDocumentId", "empty.field");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "structureId", "empty.field");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "burialOn", "empty.field");

        if (deceasedDTO.getStructureId() != null) {
            if (GraveRestClient.findById(deceasedDTO.getStructureId()) == null) {
                errors.rejectValue("structureId", "number.non-existent");
            }
        }
    }

}
