package ro.InnovaTeam.cemeteryApp.validators.impl;

import ro.InnovaTeam.cemeteryApp.ContractDTO;
import ro.InnovaTeam.cemeteryApp.validators.ContractExpiresOn;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by robert on 1/9/2015.
 */
public class ContractExpiresOnValidatorImpl implements ConstraintValidator<ContractExpiresOn, ContractDTO> {

    @Override
    public void initialize(ContractExpiresOn constraintAnnotation) {
    }

    @Override
    public boolean isValid(ContractDTO value, ConstraintValidatorContext context) {
        return (value.getUpdatedOn() == null && value.getExpiresOn() == null) || (value.getUpdatedOn() != null && value.getExpiresOn() != null && value.getUpdatedOn().compareTo(value.getExpiresOn()) < 0);
    }
}
