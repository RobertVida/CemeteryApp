package ro.InnovaTeam.cemeteryApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.InnovaTeam.cemeteryApp.FilterDTO;
import ro.InnovaTeam.cemeteryApp.registers.*;
import ro.InnovaTeam.cemeteryApp.service.AuthenticationService;
import ro.InnovaTeam.cemeteryApp.service.SearchService;
import ro.InnovaTeam.cemeteryApp.util.registers.*;

import static ro.InnovaTeam.cemeteryApp.util.FilterUtil.toDB;

/**
 * Created by robert on 1/5/2015.
 */
@Controller
public class SearchController extends ExceptionHandledController {

    public static final String BURIAL_REGISTRY_URL = "/burialRegistry";
    public static final String GRAVE_REGISTRY_URL = "/graveRegistry";
    public static final String MONUMENT_REGISTRY_URL = "/monumentRegistry";
    public static final String DECEASED_REGISTRY_URL = "/deceasedRegistry/{nameOrder}/{diedOnOrder}";
    public static final String DECEASED_NO_CAREGIVER_REGISTRY_URL = "/deceasedRegistryNoCaregiver/{nameOrder}/{diedOnOrder}";
    public static final String REQUEST_REGISTRY_URL = "/requestRegistry";
    public static final String CONTRACT_REGISTRY_URL = "/contractRegistry";

    @Autowired
    private SearchService searchService;
    @Autowired
    private AuthenticationService authService;

    @RequestMapping(value = BURIAL_REGISTRY_URL, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public BurialRegistry getBurialRegistry(@RequestHeader("Authorization-Token") String token, @RequestBody FilterDTO filterDTO) {
        isLoggedIn(token);
        return new BurialRegistry(BurialRegistryUtil.toDTO(searchService.getBurialRegistry(toDB(filterDTO))));
    }

    @RequestMapping(value = BURIAL_REGISTRY_URL + "/count", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer getBurialRegistryCount(@RequestHeader("Authorization-Token") String token, @RequestBody FilterDTO filterDTO) {
        isLoggedIn(token);
        return searchService.getBurialRegistryCount(toDB(filterDTO));
    }

    @RequestMapping(value = GRAVE_REGISTRY_URL, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public GraveRegistry getGraveRegistry(@RequestHeader("Authorization-Token") String token, @RequestBody FilterDTO filterDTO) {
        isLoggedIn(token);
        return new GraveRegistry(GraveRegistryUtil.toDTO(searchService.getGraveRegistry(toDB(filterDTO))));
    }

    @RequestMapping(value = GRAVE_REGISTRY_URL + "/count", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer getGraveRegistryCount(@RequestHeader("Authorization-Token") String token, @RequestBody FilterDTO filterDTO) {
        isLoggedIn(token);
        return searchService.getGraveRegistryCount(toDB(filterDTO));
    }

    @RequestMapping(value = MONUMENT_REGISTRY_URL, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public MonumentRegistry getMonumentRegistry(@RequestHeader("Authorization-Token") String token, @RequestBody FilterDTO filterDTO) {
        isLoggedIn(token);
        return new MonumentRegistry(MonumentRegistryUtil.toDTO(searchService.getMonumentRegistry(toDB(filterDTO))));
    }

    @RequestMapping(value = MONUMENT_REGISTRY_URL + "/count", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer getMonumentRegistryCount(@RequestHeader("Authorization-Token") String token, @RequestBody FilterDTO filterDTO) {
        isLoggedIn(token);
        return searchService.getMonumentRegistryCount(toDB(filterDTO));
    }

    @RequestMapping(value = DECEASED_REGISTRY_URL, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public DeceasedRegistry getDeceasedRegistry(@RequestHeader("Authorization-Token") String token, @PathVariable String nameOrder, @PathVariable String diedOnOrder, @RequestBody FilterDTO filterDTO) {
        isLoggedIn(token);
        return new DeceasedRegistry(DeceasedRegistryUtil.toDTO(searchService.getDeceasedRegistry(toDB(filterDTO), nameOrder, diedOnOrder)));
    }

    @RequestMapping(value = DECEASED_REGISTRY_URL + "/count", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer getDeceasedRegistryCount(@RequestHeader("Authorization-Token") String token, @PathVariable String nameOrder, @PathVariable String diedOnOrder, @RequestBody FilterDTO filterDTO) {
        isLoggedIn(token);
        return searchService.getDeceasedRegistryCount(toDB(filterDTO), nameOrder, diedOnOrder);
    }

    @RequestMapping(value = DECEASED_NO_CAREGIVER_REGISTRY_URL, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public DeceasedNoCaregiverRegistry getDeceasedNoCaregiverRegistry(@RequestHeader("Authorization-Token") String token, @PathVariable String nameOrder, @PathVariable String diedOnOrder, @RequestBody FilterDTO filterDTO) {
        isLoggedIn(token);
        return new DeceasedNoCaregiverRegistry(DeceasedNoCaregiverRegistryUtil.toDTO(searchService.getDeceasedNoCaregiverRegistry(toDB(filterDTO), nameOrder, diedOnOrder)));
    }

    @RequestMapping(value = DECEASED_NO_CAREGIVER_REGISTRY_URL + "/count", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer getDeceasedNoCaregiverRegistryCount(@RequestHeader("Authorization-Token") String token, @PathVariable String nameOrder, @PathVariable String diedOnOrder, @RequestBody FilterDTO filterDTO) {
        isLoggedIn(token);
        return searchService.getDeceasedNoCaregiverRegistryCount(toDB(filterDTO), nameOrder, diedOnOrder);
    }

    @RequestMapping(value = REQUEST_REGISTRY_URL, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public RequestRegistry getRequestRegistry(@RequestHeader("Authorization-Token") String token, @RequestBody FilterDTO filterDTO) {
        isLoggedIn(token);
        return new RequestRegistry(RequestRegistryUtil.toDTO(searchService.getRequestRegistry(toDB(filterDTO))));
    }

    @RequestMapping(value = REQUEST_REGISTRY_URL + "/count", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer getRequestRegistryCount(@RequestHeader("Authorization-Token") String token, @RequestBody FilterDTO filterDTO) {
        isLoggedIn(token);
        return searchService.getRequestRegistryCount(toDB(filterDTO));
    }

    @RequestMapping(value = CONTRACT_REGISTRY_URL, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ContractRegistry getContractRegistry(@RequestHeader("Authorization-Token") String token, @RequestBody FilterDTO filterDTO) {
        isLoggedIn(token);
        return new ContractRegistry(ContractRegistryUtil.toDTO(searchService.getContractRegistry(toDB(filterDTO))));
    }

    @RequestMapping(value = CONTRACT_REGISTRY_URL + "/count", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer getContractRegistryCount(@RequestHeader("Authorization-Token") String token, @RequestBody FilterDTO filterDTO) {
        isLoggedIn(token);
        return searchService.getContractRegistryCount(toDB(filterDTO));
    }

    private void isLoggedIn(String token) {
        authService.hasGuestAccess(token);
    }
}
