package ro.InnovaTeam.cemeteryApp.validators.impl;

import ro.InnovaTeam.cemeteryApp.validators.TelephoneNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by robert on 1/8/2015.
 */
public class TelephoneNumberValidatorImpl implements ConstraintValidator<TelephoneNumber, String> {

    @Override
    public void initialize(TelephoneNumber constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.length() != 10) {
            return false;
        }

        for (int i = 0; i < value.length(); i++) {
            if (!Character.isDigit(value.charAt(i))) {
                return false;
            }
        }

        return true;
    }
}
