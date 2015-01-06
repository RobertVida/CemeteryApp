package ro.InnovaTeam.cemeteryApp.controller.structure_history;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ro.InnovaTeam.cemeteryApp.ClientDTO;
import ro.InnovaTeam.cemeteryApp.StructureHistoryEntryDTO;
import ro.InnovaTeam.cemeteryApp.restClient.GraveRestClient;
import ro.InnovaTeam.cemeteryApp.restClient.MonumentRestClient;
import ro.InnovaTeam.cemeteryApp.restClient.StructureHistoryRestClient;
import ro.InnovaTeam.cemeteryApp.util.ValidatorUtil;

/**
 * Created by Tudor on 1/6/2015.
 */
@Component
@Qualifier("structureValidator")
public class StructureHistoryValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return StructureHistoryEntryDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object object, Errors errors) {
        StructureHistoryEntryDTO structureHistoryEntryDTO = (StructureHistoryEntryDTO) object;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "empty.field");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "date", "empty.field");
       if (structureHistoryEntryDTO.getStructureId() != null) {
            if (GraveRestClient.findById(structureHistoryEntryDTO.getStructureId()) == null || MonumentRestClient.findById(structureHistoryEntryDTO.getStructureId()) == null) {
                errors.rejectValue("structureId", "number.non-existent");
            }
        }
    }
}
