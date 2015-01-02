package ro.InnovaTeam.cemeteryApp.restClient;

import org.springframework.web.bind.annotation.PathVariable;
import ro.InnovaTeam.cemeteryApp.FilterDTO;
import ro.InnovaTeam.cemeteryApp.RestingPlaceRequestDTO;
import ro.InnovaTeam.cemeteryApp.RestingPlaceRequestList;

import java.util.List;

/**
 * Created by Catalin Sorecau on 12/27/2014.
 */
public class RestingPlaceRequestRestClient extends GenericRestClient {

    public static final String REQUEST_URL = "/request";
    public static final String REQUESTS_URL = "/requests";
    public static final String REQUESTS_FOR_STATUS_URL = REQUESTS_URL + "/{status}";
    public static final String SPECIFIC_REQUEST_URL = REQUEST_URL + "/{requestId}";
    public static final String SPECIFIC_USER_REQUEST_URL = REQUEST_URL + "/{userId}/{requestId}";

    public static List<RestingPlaceRequestDTO> getRequestsByFilter(FilterDTO requestFilterDTO) {
        return getByFilter(requestFilterDTO, BASE_URL + REQUESTS_URL, RestingPlaceRequestList.class);
    }

    public static RestingPlaceRequestDTO findById(@PathVariable Integer requestId) {
        return findById(requestId, BASE_URL + SPECIFIC_REQUEST_URL, RestingPlaceRequestDTO.class);
    }

    public static RestingPlaceRequestDTO update(@PathVariable Integer requestId, RestingPlaceRequestDTO requestDTO) {
        return update(requestId, BASE_URL + SPECIFIC_REQUEST_URL, requestDTO, RestingPlaceRequestDTO.class);
    }

    public static void add(RestingPlaceRequestDTO requestDTO) {
        add(requestDTO, BASE_URL + REQUEST_URL);
    }

    public static void delete(@PathVariable Integer requestId) {
        delete(requestId, BASE_URL + SPECIFIC_USER_REQUEST_URL);
    }

    public static Integer getRequestCount(FilterDTO filterDTO) {
        return getCount(filterDTO, BASE_URL + REQUESTS_URL + "/count");
    }

    public static Integer countRequestByFilterAndStatus(FilterDTO filterDTO, String status) {
        return getJSONRestTemplate().postForObject(BASE_URL + REQUESTS_FOR_STATUS_URL + "/count",
                filterDTO, Integer.class, status);
    }

    public static List<RestingPlaceRequestDTO> findRequestByFilterAndStatus(FilterDTO filterDTO, String status) {
        RestingPlaceRequestList list = getJSONRestTemplate().postForObject(BASE_URL + REQUESTS_FOR_STATUS_URL,
                filterDTO, RestingPlaceRequestList.class, status);

        return list.getContent();
    }
}
