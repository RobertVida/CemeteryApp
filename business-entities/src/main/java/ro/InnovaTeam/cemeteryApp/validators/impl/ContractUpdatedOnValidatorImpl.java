package ro.InnovaTeam.cemeteryApp.validators.impl;

import ro.InnovaTeam.cemeteryApp.ContractDTO;
import ro.InnovaTeam.cemeteryApp.validators.ContractUpdatedOn;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by robert on 1/9/2015.
 */
public class ContractUpdatedOnValidatorImpl implements ConstraintValidator<ContractUpdatedOn, ContractDTO> {

    @Override
    public void initialize(ContractUpdatedOn constraintAnnotation) {
    }

    @Override
    public boolean isValid(ContractDTO value, ConstraintValidatorContext context) {
        return value.getUpdatedOn() == null || value.getSignedOn().compareTo(value.getUpdatedOn()) < 0;
    }
}
