package ro.InnovaTeam.cemeteryApp.controller.deceased;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ro.InnovaTeam.cemeteryApp.DeceasedDTO;

/**
 * Created by Catalin Sorecau on 11/30/2014.
 */
@Component
@Qualifier("deceasedValidator")
public class DeceasedValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return DeceasedDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        //TODO
    }
}
