package ro.InnovaTeam.cemeteryApp.controller.monument;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ro.InnovaTeam.cemeteryApp.MonumentDTO;
import ro.InnovaTeam.cemeteryApp.restClient.ParcelRestClient;

/**
 * Created by Catalin Sorecau on 12/27/2014.
 */
@Component
@Qualifier("monumentValidator")
public class MonumentValidator implements Validator{
    @Override
    public boolean supports(Class<?> clazz) {
        return MonumentDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MonumentDTO monumentDTO = (MonumentDTO) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "parcelId", "empty.field");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "createdOn", "empty.field");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "width", "empty.field");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "length", "empty.field");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "empty.field");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "empty.field");

        if (monumentDTO.getParcelId() != null && ParcelRestClient.findById(monumentDTO.getParcelId()) == null) {
            errors.rejectValue("parcelId", "number.non-existent");
        }
    }
}
