package ro.InnovaTeam.cemeteryApp.restClient;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import ro.InnovaTeam.cemeteryApp.FilterDTO;
import ro.InnovaTeam.cemeteryApp.GraveDTO;
import ro.InnovaTeam.cemeteryApp.GraveList;

import java.util.List;

/**
 * Created by Catalin Sorecau on 11/30/2014.
 */
public class GraveRestClient extends GenericRestClient {

    public static final String GRAVE_URL = "/grave";
    public static final String GRAVES_URL = "/graves";
    public static final String SPECIFIC_GRAVE_URL = GRAVE_URL + "/{graveId}";
    public static final String SPECIFIC_USER_GRAVE_URL = GRAVE_URL + "/{userId}/{graveId}";

    public static List<GraveDTO> getByFilter(FilterDTO graveFilterDTO) {
        return getByFilter(graveFilterDTO, BASE_URL + GRAVES_URL, GraveList.class);
    }

    public static GraveDTO findById(@PathVariable Integer graveId) {
        return findById(graveId, BASE_URL + SPECIFIC_GRAVE_URL, GraveDTO.class);
    }

    public static GraveDTO update(@PathVariable Integer graveId, GraveDTO graveDTO) {
        return update(graveId, BASE_URL + SPECIFIC_GRAVE_URL, graveDTO, GraveDTO.class);
    }

    public static void add(GraveDTO graveDTO) {
        add(graveDTO, BASE_URL + GRAVE_URL);
    }

    public static void delete(@PathVariable Integer graveId) {
        delete(graveId, BASE_URL + SPECIFIC_USER_GRAVE_URL);
    }
}
