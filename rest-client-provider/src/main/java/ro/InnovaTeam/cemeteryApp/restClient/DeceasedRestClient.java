package ro.InnovaTeam.cemeteryApp.restClient;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import ro.InnovaTeam.cemeteryApp.*;

import java.util.List;

/**
 * Created by Catalin Sorecau on 11/30/2014.
 */
public class DeceasedRestClient extends GenericRestClient {

    public static final String DECEASED_URL = "/deceased";
    public static final String SPECIFIC_DECEASED_URL = DECEASED_URL + "/{deceasedId}";
    public static final String SPECIFIC_USER_DECEASED_URL = DECEASED_URL + "/{userId}/{deceasedId}";

    public static List<DeceasedDTO> findByFilter(FilterDTO deceasedFilterDTO) {
        return getByFilter(deceasedFilterDTO, BASE_URL + DECEASED_URL, DeceasedList.class);
    }

    public static DeceasedDTO findById(@PathVariable Integer deceasedId) {
        return findById(deceasedId, BASE_URL + SPECIFIC_DECEASED_URL, DeceasedDTO.class);
    }

    public static DeceasedDTO update(@PathVariable Integer deceasedId, DeceasedDTO deceasedDTO) {
        return update(deceasedId, BASE_URL + SPECIFIC_DECEASED_URL, deceasedDTO, DeceasedDTO.class);
    }

    public static void add(DeceasedDTO deceasedDTO) {
        add(deceasedDTO, BASE_URL + DECEASED_URL);
    }

    public static void delete(@PathVariable Integer deceasedId) {
        delete(deceasedId, BASE_URL + SPECIFIC_USER_DECEASED_URL);
    }

    public static Integer getDeceasedCount(FilterDTO filterDTO) {
        return getCount(filterDTO, BASE_URL + DECEASED_URL + "/count");
    }
}
