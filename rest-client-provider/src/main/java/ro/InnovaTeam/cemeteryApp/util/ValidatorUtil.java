package ro.InnovaTeam.cemeteryApp.util;

import java.util.regex.Pattern;

/**
 * Created by Catalin Sorecau on 12/22/2014.
 */
public class ValidatorUtil {

    public static Boolean isInteger(String input) {
        return Pattern.matches("[0-9]+", input);
    }

    public static Boolean isValidCNP(String input) {
        return isInteger(input) && input.length() == 13;
    }

    public static Boolean isValidPhoneNumber(String input) {
        return isInteger(input) && input.length() == 10;
    }
}
