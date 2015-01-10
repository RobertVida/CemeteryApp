//package ro.InnovaTeam.cemeteryApp.validators;
//
//import javax.validation.Constraint;
//import javax.validation.Payload;
//import java.lang.annotation.Documented;
//import java.lang.annotation.Retention;
//import java.lang.annotation.Target;
//
//import static java.lang.annotation.ElementType.*;
//import static java.lang.annotation.RetentionPolicy.RUNTIME;
//
///**
// * Created by robert on 1/8/2015.
// */
//@Documented
//@Constraint(validatedBy = { FullCnpValidator.class })
//@Target({ FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
//@Retention(RUNTIME)
//public @interface FullCnp {
//
//    String message() default "{cnp invalid}";
//
//    Class<?>[] groups() default {};
//
//    Class<? extends Payload>[] payload() default {};
//}
