package ro.InnovaTeam.cemeteryApp.restClient;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import ro.InnovaTeam.cemeteryApp.*;

import java.util.List;

/**
 * Created by Catalin Sorecau on 11/30/2014.
 */
public class DeceasedRestClient extends BaseRestClient {

    public static final String DECEASED_URL = "/deceased";
    public static final String SPECIFIC_DECEASED_URL = DECEASED_URL + "/{deceasedId}";
    public static final String SPECIFIC_USER_DECEASED_URL = DECEASED_URL + "/{userId}/{deceasedId}";

    public static List<DeceasedDTO> findByFilter(FilterDTO deceasedFilterDTO) {
        RestTemplate restTemplate = getJSONRestTemplate();
        String endPointURL = BASE_URL + DECEASED_URL;

        DeceasedList deceasedList = restTemplate.postForObject(endPointURL, deceasedFilterDTO, DeceasedList.class);

        return deceasedList.getContent();
    }

    public static DeceasedDTO findById(@PathVariable Integer deceasedId) {
        RestTemplate restTemplate = getJSONRestTemplate();
        String endPointURL = BASE_URL + SPECIFIC_DECEASED_URL;

        return restTemplate.getForObject(endPointURL, DeceasedDTO.class, deceasedId);
    }

    public static DeceasedDTO update(@PathVariable Integer deceasedId, DeceasedDTO deceasedDTO) {
        RestTemplate restTemplate = getJSONRestTemplate();
        String endPointURL = BASE_URL + SPECIFIC_DECEASED_URL;
        deceasedDTO.setUserId(getLoggedInUserId());

        return restTemplate.postForObject(endPointURL, deceasedDTO, DeceasedDTO.class, deceasedId);
    }

    public static void add(DeceasedDTO deceasedDTO) {
        RestTemplate restTemplate = getJSONRestTemplate();
        String endPointURL = BASE_URL + DECEASED_URL;
        deceasedDTO.setUserId(getLoggedInUserId());

        restTemplate.put(endPointURL, deceasedDTO);
    }

    public static void delete(@PathVariable Integer deceasedId) {
        RestTemplate restTemplate = getJSONRestTemplate();
        String endPointURL = BASE_URL + SPECIFIC_USER_DECEASED_URL;

        restTemplate.delete(endPointURL, getLoggedInUserId(), deceasedId);
    }
}
