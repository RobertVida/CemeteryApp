//package ro.InnovaTeam.cemeteryApp.validators;
//
//import javax.validation.ConstraintValidator;
//import javax.validation.ConstraintValidatorContext;
//
///**
// * Created by robert on 1/8/2015.
// */
//public class FullCnpValidator implements ConstraintValidator<FullCnp, String> {
//
//    private static final Integer[] rule = new Integer[]{2, 7, 9, 1, 4, 6, 3, 5, 8, 2, 7, 9};
//
//    @Override
//    public void initialize(FullCnp constraintAnnotation) {
//    }
//
//    @Override
//    public boolean isValid(String value, ConstraintValidatorContext context) {
//        if (value == null || value.length() != 13) {
//            return false;
//        }
//
//        Integer[] cnp = processCNP(value);
//        Long rest = calculateSum(value, cnp) % 11;
//
//        return !(rest < 10 && rest.intValue() == cnp[12]) && !(rest == 10 && cnp[12] == 1);
//
//    }
//
//    private Long calculateSum(String value, Integer[] cnp) {
//        Long sum = 0L;
//        for(int i = 0 ; i < cnp.length ; i++) {
//            sum += cnp[i] * rule[i];
//        }
//        return sum;
//    }
//
//    private Integer[] processCNP(String value) {
//        Integer[] cnp = new Integer[13];
//        for(int i = 0 ; i < value.length() ; i++) {
//            cnp[i] = Integer.parseInt(String.valueOf(value.charAt(i)));
//        }
//        return cnp;
//    }
//}
