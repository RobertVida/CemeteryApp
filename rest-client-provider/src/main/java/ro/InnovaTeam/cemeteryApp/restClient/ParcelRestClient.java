package ro.InnovaTeam.cemeteryApp.restClient;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import ro.InnovaTeam.cemeteryApp.*;

import java.util.List;

/**
 * Created by Catalin Sorecau on 11/27/2014.
 */
public class ParcelRestClient extends BaseRestClient{

    public static final String PARCEL_URL = "/parcel";
    public static final String PARCELS_URL = "/parcels";
    public static final String SPECIFIC_PARCEL_URL = PARCEL_URL + "/{parcelId}";
    public static final String SPECIFIC_CEMETERY_PARCELS_URL = PARCELS_URL + "/cemetery/{cemeteryId}";

    public static List<ParcelDTO> getParcelsByFilter(FilterDTO parcelFilterDTO) {
        RestTemplate restTemplate = getJSONRestTemplate();
        String endPointURL = BASE_URL + PARCELS_URL;

        ParcelList parcelList = restTemplate.postForObject(endPointURL, parcelFilterDTO, ParcelList.class);

        return parcelList.getContent();
    }

    public static ParcelDTO findById(@PathVariable Integer parcelId) {
        RestTemplate restTemplate = getJSONRestTemplate();
        String endPointURL = BASE_URL + SPECIFIC_PARCEL_URL;

        return restTemplate.getForObject(endPointURL, ParcelDTO.class, parcelId);
    }

    public static ParcelDTO update(@PathVariable Integer parcelId, ParcelDTO parcelDTO) {
        RestTemplate restTemplate = getJSONRestTemplate();
        String endPointURL = BASE_URL + SPECIFIC_PARCEL_URL;

        return restTemplate.postForObject(endPointURL, parcelDTO, ParcelDTO.class, parcelId);
    }

    public static void add(ParcelDTO parcelDTO) {
        RestTemplate restTemplate = getJSONRestTemplate();
        String endPointURL = BASE_URL + PARCEL_URL;

        restTemplate.put(endPointURL, parcelDTO);
    }

    public static void delete(@PathVariable Integer parcelId) {
        RestTemplate restTemplate = getJSONRestTemplate();
        String endPointURL = BASE_URL + SPECIFIC_PARCEL_URL;

        restTemplate.delete(endPointURL, parcelId);
    }
}
