package ro.InnovaTeam.cemeteryApp.controller.request;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ro.InnovaTeam.cemeteryApp.RestingPlaceRequestDTO;
import ro.InnovaTeam.cemeteryApp.restClient.ClientRestClient;

/**
 * Created by Catalin Sorecau on 12/27/2014.
 */
@Component
@Qualifier("requestValidator")
public class RestingPlaceRequestValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return RestingPlaceRequestDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RestingPlaceRequestDTO requestDTO = (RestingPlaceRequestDTO) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "clientId", "empty.field");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "createdOn", "empty.field");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "infocetNumber", "empty.field");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "status", "empty.field");

        if (requestDTO.getClientId() != null && ClientRestClient.findById(requestDTO.getClientId()) == null) {
            errors.rejectValue("clientId", "number.non-existent");
        }
    }
}
