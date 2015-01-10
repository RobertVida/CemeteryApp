package ro.InnovaTeam.cemeteryApp.restClient;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import ro.InnovaTeam.cemeteryApp.*;

import java.util.List;

/**
 * Created by Catalin Sorecau on 11/27/2014.
 */
public class ParcelRestClient extends GenericRestClient{

    public static final String PARCEL_URL = "/parcel";
    public static final String PARCELS_URL = "/parcels";
    public static final String SPECIFIC_PARCEL_URL = PARCEL_URL + "/{parcelId}";
    public static final String SPECIFIC_CEMETERY_PARCELS_URL = PARCELS_URL + "/cemetery/{cemeteryId}";

    public static List<ParcelDTO> getParcelsByFilter(FilterDTO parcelFilterDTO) {
        return getByFilter(parcelFilterDTO, BASE_URL + PARCELS_URL, ParcelList.class);
    }

    public static ParcelDTO findById(@PathVariable Integer parcelId) {
        return findById(parcelId, BASE_URL + SPECIFIC_PARCEL_URL, ParcelDTO.class);
    }

    public static ParcelDTO update(@PathVariable Integer parcelId, ParcelDTO parcelDTO) {
        return update(parcelId, BASE_URL + SPECIFIC_PARCEL_URL, parcelDTO, ParcelDTO.class);
    }

    public static void add(ParcelDTO parcelDTO) {
        add(parcelDTO, BASE_URL + PARCEL_URL);
    }

    public static void delete(@PathVariable Integer parcelId) {
        delete(parcelId, BASE_URL + SPECIFIC_PARCEL_URL);
    }

    public static Integer getParcelCount(FilterDTO filterDTO) {
        return getCount(filterDTO, BASE_URL + PARCELS_URL + "/count");
    }
}
