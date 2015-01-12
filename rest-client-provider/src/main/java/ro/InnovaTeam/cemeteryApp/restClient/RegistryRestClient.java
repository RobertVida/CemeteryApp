package ro.InnovaTeam.cemeteryApp.restClient;

import org.springframework.http.HttpEntity;
import org.springframework.util.LinkedMultiValueMap;
import ro.InnovaTeam.cemeteryApp.FilterDTO;
import ro.InnovaTeam.cemeteryApp.registers.*;

import java.util.List;

/**
 * Created by Catalin Sorecau on 1/7/2015.
 */
public class RegistryRestClient extends GenericRestClient{

    public static final String BURIAL_REGISTRY_URL = "/burialRegistry";
    public static final String GRAVE_REGISTRY_URL = "/graveRegistry";
    public static final String MONUMENT_REGISTRY_URL = "/monumentRegistry";
    public static final String DECEASED_REGISTRY_URL = "/deceasedRegistry/{nameOrder}/{diedOnOrder}";
    public static final String DECEASED_NO_CAREGIVER_REGISTRY_URL = "/deceasedRegistryNoCaregiver/{nameOrder}/{diedOnOrder}";
    public static final String REQUEST_REGISTRY_URL = "/requestRegistry";
    public static final String CONTRACT_REGISTRY_URL = "/contractRegistry";

    public static List<BurialRegistryEntryDTO> getBurialRegistry(FilterDTO filterDTO) {
        BurialRegistry burialRegistry = getJSONRestTemplate().postForObject(BASE_URL + BURIAL_REGISTRY_URL, authorizationWrapper(filterDTO), BurialRegistry.class);
        return burialRegistry.getContent();
    }

    public static Integer getBurialRegistryCount(FilterDTO filterDTO) {
        return getCount(filterDTO, BASE_URL + BURIAL_REGISTRY_URL + "/count");
    }

    public static List<GraveRegistryEntryDTO> getGraveRegistry(FilterDTO filterDTO) {
        GraveRegistry graveRegistry = getJSONRestTemplate().postForObject(BASE_URL + GRAVE_REGISTRY_URL, authorizationWrapper(filterDTO), GraveRegistry.class);
        return graveRegistry.getContent();
    }

    public static Integer getGraveRegistryCount(FilterDTO filterDTO) {
        return getCount(filterDTO, BASE_URL + GRAVE_REGISTRY_URL + "/count");
    }

    private static HttpEntity<Object> authorizationWrapper(Object entity) {
        return new HttpEntity<Object>(entity, new LinkedMultiValueMap<String, String>(){{
            add("Content-Type", "application/json");
            add("Authorization-Token", getLoggedInUserToken());
        }});
    }

    public static List<MonumentRegistryEntryDTO> getMonumentRegistry(FilterDTO filterDTO) {
        MonumentRegistry monumentRegistry = getJSONRestTemplate().postForObject(BASE_URL + MONUMENT_REGISTRY_URL, authorizationWrapper(filterDTO), MonumentRegistry.class);
        return monumentRegistry.getContent();
    }

    public static Integer getMonumentRegistryCount(FilterDTO filterDTO) {
        return getCount(filterDTO, BASE_URL + MONUMENT_REGISTRY_URL + "/count");
    }

    public static List<DeceasedRegistryEntryDTO> getDeceasedRegistry(FilterDTO filterDTO, String nameOrder, String diedOnOrder) {
        DeceasedRegistry deceasedRegistry = getJSONRestTemplate().postForObject(BASE_URL + DECEASED_REGISTRY_URL, authorizationWrapper(filterDTO), DeceasedRegistry.class, nameOrder, diedOnOrder);
        return deceasedRegistry.getContent();
    }

    public static Integer getDeceasedRegistryCount(FilterDTO filterDTO, String nameOrder, String diedOnOrder) {
        return getJSONRestTemplate().postForObject(BASE_URL + DECEASED_REGISTRY_URL + "/count", authorizationWrapper(filterDTO), Integer.class, nameOrder, diedOnOrder);
    }

    public static List<DeceasedNoCaregiverRegistryEntryDTO> getDeceasedNoCaregiverRegistry(FilterDTO filterDTO, String nameOrder, String diedOnOrder) {
        DeceasedNoCaregiverRegistry deceasedNoCaregiverRegistry = getJSONRestTemplate().postForObject(BASE_URL + DECEASED_NO_CAREGIVER_REGISTRY_URL, authorizationWrapper(filterDTO), DeceasedNoCaregiverRegistry.class, nameOrder, diedOnOrder);
        return deceasedNoCaregiverRegistry.getContent();
    }

    public static Integer getDeceasedNoCaregiverRegistryCount(FilterDTO filterDTO, String nameOrder, String diedOnOrder) {
        return getJSONRestTemplate().postForObject(BASE_URL + DECEASED_NO_CAREGIVER_REGISTRY_URL + "/count", authorizationWrapper(filterDTO), Integer.class, nameOrder, diedOnOrder);
    }

    public static List<RequestRegistryEntryDTO> getRequestRegistry(FilterDTO filterDTO) {
        RequestRegistry requestRegistry = getJSONRestTemplate().postForObject(BASE_URL + REQUEST_REGISTRY_URL, authorizationWrapper(filterDTO), RequestRegistry.class);
        return requestRegistry.getContent();
    }

    public static Integer getRequestRegistryCount(FilterDTO filterDTO) {
        return getCount(filterDTO, BASE_URL + REQUEST_REGISTRY_URL + "/count");
    }

    public static List<ContractRegistryEntryDTO> getContractRegistry(FilterDTO filterDTO) {
        ContractRegistry contractRegistry = getJSONRestTemplate().postForObject(BASE_URL + CONTRACT_REGISTRY_URL, authorizationWrapper(filterDTO), ContractRegistry.class);
        return contractRegistry.getContent();
    }

    public static Integer getContractRegistryCount(FilterDTO filterDTO) {
        return getCount(filterDTO, BASE_URL + CONTRACT_REGISTRY_URL + "/count");
    }
}
