package ro.InnovaTeam.cemeteryApp.controller.grave;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ro.InnovaTeam.cemeteryApp.GraveDTO;
import ro.InnovaTeam.cemeteryApp.restClient.ParcelRestClient;

/**
 * Created by Catalin Sorecau on 11/30/2014.
 */
@Component
@Qualifier("graveValidator")
public class GraveValidator implements Validator{
    @Override
    public boolean supports(Class<?> clazz) {
        return GraveDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        GraveDTO graveDTO = (GraveDTO) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "parcelId", "empty.field");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "createdOn", "empty.field");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "width", "empty.field");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "length", "empty.field");

        if (ParcelRestClient.findById(graveDTO.getParcelId()) == null) {
            errors.rejectValue("parcelId", "number.non-existent");
        }
    }
}
