package ro.InnovaTeam.cemeteryApp.controller.client;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ro.InnovaTeam.cemeteryApp.ClientDTO;
import ro.InnovaTeam.cemeteryApp.util.ValidatorUtil;

/**
 * Created by Catalin Sorecau on 11/23/2014.
 */
@Component
@Qualifier("clientValidator")
public class ClientValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ClientDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object object, Errors errors) {
        ClientDTO clientDTO = (ClientDTO) object;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "empty.field");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "empty.field");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cnp", "empty.field");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber", "empty.field");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "empty.field");
        if (ValidatorUtil.isValidCNP(clientDTO.getCnp())) {
            errors.rejectValue("cnp", "cnp.invalid");
        }
        if (ValidatorUtil.isValidPhoneNumber(clientDTO.getPhoneNumber())) {
            errors.rejectValue("phoneNumber", "phoneNumber.invalid");
        }
    }

}
