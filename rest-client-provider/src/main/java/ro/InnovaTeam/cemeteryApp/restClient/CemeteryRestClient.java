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
public class CemeteryRestClient extends BaseRestClient {

    public static final String CEMETERY_URL = "/cemetery";
    public static final String CEMETERIES_URL = "/cemeteries";
    public static final String SPECIFIC_CEMETERY_URL = CEMETERY_URL + "/{cemeteryId}";
    public static final String SPECIFIC_USER_CEMETERY_URL = CEMETERY_URL + "/{userId}/{cemeteryId}";

    public static List<CemeteryDTO> getCemeteriesByFilter(FilterDTO cemeteryFilterDTO) {
        RestTemplate restTemplate = getJSONRestTemplate();
        String endPointURL = BASE_URL + CEMETERIES_URL;

        CemeteryList cemeteryList = restTemplate.postForObject(endPointURL, cemeteryFilterDTO, CemeteryList.class);

        return cemeteryList.getContent();
    }

    public static CemeteryDTO findById(@PathVariable Integer cemeteryId) {
        RestTemplate restTemplate = getJSONRestTemplate();
        String endPointURL = BASE_URL + SPECIFIC_CEMETERY_URL;

        return restTemplate.getForObject(endPointURL, CemeteryDTO.class, cemeteryId);
    }

    public static CemeteryDTO update(@PathVariable Integer cemeteryId, CemeteryDTO cemeteryDTO) {
        RestTemplate restTemplate = getJSONRestTemplate();
        String endPointURL = BASE_URL + SPECIFIC_CEMETERY_URL;
        cemeteryDTO.setUserId(getLoggedInUserId());

        return restTemplate.postForObject(endPointURL, cemeteryDTO, CemeteryDTO.class, cemeteryId);
    }

    public static void add(CemeteryDTO cemeteryDTO) {
        RestTemplate restTemplate = getJSONRestTemplate();
        String endPointURL = BASE_URL + CEMETERY_URL;
        cemeteryDTO.setUserId(getLoggedInUserId());

        restTemplate.put(endPointURL, cemeteryDTO);
    }

    public static void delete(@PathVariable Integer cemeteryId) {
        RestTemplate restTemplate = getJSONRestTemplate();
        String endPointURL = BASE_URL + SPECIFIC_USER_CEMETERY_URL;

        restTemplate.delete(endPointURL, getLoggedInUserId(), cemeteryId);
    }
}
