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
public class GraveRestClient extends BaseRestClient {

    public static final String GRAVE_URL = "/grave";
    public static final String GRAVES_URL = "/graves";
    public static final String SPECIFIC_GRAVE_URL = GRAVE_URL + "/{graveId}";

    public static List<GraveDTO> getByFilter(FilterDTO graveFilterDTO) {
        RestTemplate restTemplate = getJSONRestTemplate();
        String endPointURL = BASE_URL + GRAVES_URL;

        GraveList graveList = restTemplate.postForObject(endPointURL, graveFilterDTO, GraveList.class);

        return graveList.getContent();
    }

    public static GraveDTO findById(@PathVariable Integer graveId) {
        RestTemplate restTemplate = getJSONRestTemplate();
        String endPointURL = BASE_URL + SPECIFIC_GRAVE_URL;

        return restTemplate.getForObject(endPointURL, GraveDTO.class, graveId);
    }

    public static GraveDTO update(@PathVariable Integer graveId, GraveDTO graveDTO) {
        RestTemplate restTemplate = getJSONRestTemplate();
        String endPointURL = BASE_URL + SPECIFIC_GRAVE_URL;

        return restTemplate.postForObject(endPointURL, graveDTO, GraveDTO.class, graveId);
    }

    public static void add(GraveDTO graveDTO) {
        RestTemplate restTemplate = getJSONRestTemplate();
        String endPointURL = BASE_URL + GRAVE_URL;

        restTemplate.put(endPointURL, graveDTO);
    }

    public static void delete(@PathVariable Integer graveId) {
        RestTemplate restTemplate = getJSONRestTemplate();
        String endPointURL = BASE_URL + SPECIFIC_GRAVE_URL;

        restTemplate.delete(endPointURL, graveId);
    }
}
