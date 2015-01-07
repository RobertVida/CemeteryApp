package ro.InnovaTeam.cemeteryApp.restClient;

import ro.InnovaTeam.cemeteryApp.FilterDTO;
import ro.InnovaTeam.cemeteryApp.registers.BurialRegistry;
import ro.InnovaTeam.cemeteryApp.registers.BurialRegistryEntryDTO;

import java.util.List;

/**
 * Created by Catalin Sorecau on 1/7/2015.
 */
public class RegistryRestClient extends GenericRestClient{

    public static final String BURIAL_REGISTRY_URL = "/burialRegistry";
    public static final String GRAVE_REGISTRY_URL = "/graveRegistry";
    public static final String MONUMENT_REGISTRY_URL = "/monumentRegistry";

    public static List<BurialRegistryEntryDTO> getBurialRegistry(FilterDTO filterDTO) {
        BurialRegistry burialRegistry = getJSONRestTemplate().postForObject(BASE_URL + BURIAL_REGISTRY_URL, filterDTO, BurialRegistry.class);
        return burialRegistry.getContent();
    }

    public static Integer getBurialRegistryCount(FilterDTO filterDTO) {
        return getCount(filterDTO, BASE_URL + BURIAL_REGISTRY_URL + "/count");
    }
}
