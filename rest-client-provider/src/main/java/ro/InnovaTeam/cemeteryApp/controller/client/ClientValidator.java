package ro.InnovaTeam.cemeteryApp.controller.client;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ro.InnovaTeam.cemeteryApp.ClientDTO;

import java.util.regex.Pattern;

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
        if (clientDTO.getCnp().length() != 13 || !isInteger(clientDTO.getCnp())) {
            errors.rejectValue("cnp", "cnp.invalid");
        }
        if (clientDTO.getPhoneNumber().length() != 10 || !isInteger(clientDTO.getPhoneNumber())) {
            errors.rejectValue("phoneNumber", "phoneNumber.invalid");
        }
    }

    private Boolean isInteger(String input) {
        return Pattern.matches("[0-9]+", input);
    }
}
