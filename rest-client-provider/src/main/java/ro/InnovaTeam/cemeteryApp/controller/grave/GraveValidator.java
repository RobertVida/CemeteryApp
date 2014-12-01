package ro.InnovaTeam.cemeteryApp.controller.grave;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Catalin Sorecau on 11/30/2014.
 */
@Component
@Qualifier("graveValidator")
public class GraveValidator implements Validator{
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
