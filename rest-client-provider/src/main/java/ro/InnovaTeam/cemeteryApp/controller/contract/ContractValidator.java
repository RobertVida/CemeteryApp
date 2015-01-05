package ro.InnovaTeam.cemeteryApp.controller.contract;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ro.InnovaTeam.cemeteryApp.ContractDTO;

import java.util.Date;

/**
 * Created by Cata on 1/5/2015.
 */
@Component
@Qualifier("contractValidator")
public class ContractValidator implements Validator{
    @Override
    public boolean supports(Class<?> aClass) {
        return ContractDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ContractDTO contractDTO = (ContractDTO) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "structureId", "empty.field");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "requestId", "empty.field");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "signedOn", "empty.field");

        Date signedOn = contractDTO.getSignedOn();
        Date expiresOn = contractDTO.getExpiresOn();
        Date updatedOn = contractDTO.getUpdatedOn();
        if (signedOn != null && expiresOn != null && updatedOn != null) {
            if (signedOn.after(updatedOn)) {
                errors.rejectValue("updatedOn", "contract.signedOn.graterThanUpdatedOn");
            }
            if (updatedOn.after(expiresOn)) {
                errors.rejectValue("expiresOn", "contract.updatedOn.graterThanExpiresOn");
            }
        }
    }
}
