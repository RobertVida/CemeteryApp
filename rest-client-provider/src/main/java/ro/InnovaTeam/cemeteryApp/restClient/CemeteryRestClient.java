package ro.InnovaTeam.cemeteryApp.restClient;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import ro.InnovaTeam.cemeteryApp.CemeteryDTO;
import ro.InnovaTeam.cemeteryApp.CemeteryList;
import ro.InnovaTeam.cemeteryApp.FilterDTO;

import java.util.List;

/**
 * Created by Catalin Sorecau on 11/24/2014.
 */
public class CemeteryRestClient extends GenericRestClient {

    public static final String CEMETERY_URL = "/cemetery";
    public static final String CEMETERIES_URL = "/cemeteries";
    public static final String SPECIFIC_CEMETERY_URL = CEMETERY_URL + "/{cemeteryId}";
    public static final String SPECIFIC_USER_CEMETERY_URL = CEMETERY_URL + "/{userId}/{cemeteryId}";

    public static List<CemeteryDTO> getCemeteriesByFilter(FilterDTO cemeteryFilterDTO) {
        return getByFilter(cemeteryFilterDTO, BASE_URL + CEMETERIES_URL, CemeteryList.class);
    }

    public static CemeteryDTO findById(@PathVariable Integer cemeteryId) {
        return findById(cemeteryId, BASE_URL + SPECIFIC_CEMETERY_URL, CemeteryDTO.class);
    }

    public static CemeteryDTO update(@PathVariable Integer cemeteryId, CemeteryDTO cemeteryDTO) {
        return update(cemeteryId, BASE_URL + SPECIFIC_CEMETERY_URL, cemeteryDTO, CemeteryDTO.class);
    }

    public static void add(CemeteryDTO cemeteryDTO) {
        add(cemeteryDTO, BASE_URL + CEMETERY_URL);
    }

    public static void delete(@PathVariable Integer cemeteryId) {
        delete(cemeteryId, BASE_URL + SPECIFIC_USER_CEMETERY_URL);
    }
}
