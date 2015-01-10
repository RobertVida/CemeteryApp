package ro.InnovaTeam.cemeteryApp.restClient;

import org.springframework.web.bind.annotation.PathVariable;
import ro.InnovaTeam.cemeteryApp.FilterDTO;
import ro.InnovaTeam.cemeteryApp.MonumentDTO;
import ro.InnovaTeam.cemeteryApp.MonumentList;

import java.util.List;

/**
 * Created by Catalin Sorecau on 12/27/2014.
 */
public class MonumentRestClient extends GenericRestClient {

    public static final String MONUMENT_URL = "/monument";
    public static final String MONUMENTS_URL = "/monuments";
    public static final String SPECIFIC_MONUMENT_URL = MONUMENT_URL + "/{monumentId}";

    public static List<MonumentDTO> getByFilter(FilterDTO monumentFilterDTO) {
        return getByFilter(monumentFilterDTO, BASE_URL + MONUMENTS_URL, MonumentList.class);
    }

    public static MonumentDTO findById(@PathVariable Integer monumentId) {
        return findById(monumentId, BASE_URL + SPECIFIC_MONUMENT_URL, MonumentDTO.class);
    }

    public static MonumentDTO update(@PathVariable Integer graveId, MonumentDTO graveDTO) {
        return update(graveId, BASE_URL + SPECIFIC_MONUMENT_URL, graveDTO, MonumentDTO.class);
    }

    public static void add(MonumentDTO graveDTO) {
        add(graveDTO, BASE_URL + MONUMENT_URL);
    }

    public static void delete(@PathVariable Integer graveId) {
        delete(graveId, BASE_URL + SPECIFIC_MONUMENT_URL);
    }

    public static Integer getMonumentCount(FilterDTO filterDTO) {
        return getCount(filterDTO, BASE_URL + MONUMENTS_URL + "/count");
    }
}
