package ro.InnovaTeam.cemeteryApp.validators.impl;

import ro.InnovaTeam.cemeteryApp.validators.SimpleCnp;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by robert on 1/8/2015.
 */
public class SimpleCnpValidatorImpl implements ConstraintValidator<SimpleCnp, String> {

    @Override
    public void initialize(SimpleCnp constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !(value == null || value.length() != 13);
    }
}
