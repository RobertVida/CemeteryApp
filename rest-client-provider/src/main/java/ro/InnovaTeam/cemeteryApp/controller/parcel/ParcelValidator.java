package ro.InnovaTeam.cemeteryApp.controller.parcel;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ro.InnovaTeam.cemeteryApp.ParcelDTO;
import ro.InnovaTeam.cemeteryApp.restClient.CemeteryRestClient;

/**
 * Created by Catalin Sorecau on 11/24/2014.
 */
@Component
@Qualifier("parcelValidator")
public class ParcelValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ParcelDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ParcelDTO parcelDTO = (ParcelDTO) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "empty.field");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cemeteryId", "empty.field");

        if (parcelDTO.getCemeteryId() != null && CemeteryRestClient.findById(parcelDTO.getCemeteryId()) == null) {
            errors.rejectValue("cemeteryId", "number.non-existent");
        }
    }
}
