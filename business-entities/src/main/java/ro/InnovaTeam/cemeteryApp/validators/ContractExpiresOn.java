package ro.InnovaTeam.cemeteryApp.validators;

import ro.InnovaTeam.cemeteryApp.validators.impl.ContractExpiresOnValidatorImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by robert on 1/9/2015.
 */
@Documented
@Constraint(validatedBy = {ContractExpiresOnValidatorImpl.class})
@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface ContractExpiresOn {

    String message() default "{contract invalid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
