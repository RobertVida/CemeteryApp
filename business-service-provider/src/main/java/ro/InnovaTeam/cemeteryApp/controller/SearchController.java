package ro.InnovaTeam.cemeteryApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.InnovaTeam.cemeteryApp.FilterDTO;
import ro.InnovaTeam.cemeteryApp.registers.*;
import ro.InnovaTeam.cemeteryApp.service.SearchService;
import ro.InnovaTeam.cemeteryApp.util.registers.*;

import static ro.InnovaTeam.cemeteryApp.util.FilterUtil.toDB;

/**
 * Created by robert on 1/5/2015.
 */
@Controller
public class SearchController {

    public static final String BURIAL_REGISTRY_URL = "/burialRegistry";
    public static final String GRAVE_REGISTRY_URL = "/graveRegistry";
    public static final String MONUMENT_REGISTRY_URL = "/monumentRegistry";
    public static final String DECEASED_REGISTRY_URL = "/deceasedRegistry/{nameOrder}/{diedOnOrder}";
    public static final String DECEASED_NO_CAREGIVER_REGISTRY_URL = "/deceasedRegistryNoCaregiver/{nameOrder}/{diedOnOrder}";
    public static final String REQUEST_REGISTRY_URL = "/requestRegistry";
    public static final String CONTRACT_REGISTRY_URL = "/contractRegistry";

    @Autowired
    private SearchService searchService;

    @RequestMapping(value = BURIAL_REGISTRY_URL, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public BurialRegistry getBurialRegistry(@RequestBody FilterDTO filterDTO) {
        return new BurialRegistry(BurialRegistryUtil.toDTO(searchService.getBurialRegistry(toDB(filterDTO))));
    }

    @RequestMapping(value = BURIAL_REGISTRY_URL + "/count", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer getBurialRegistryCount(@RequestBody FilterDTO filterDTO) {
        return searchService.getBurialRegistryCount(toDB(filterDTO));
    }

    @RequestMapping(value = GRAVE_REGISTRY_URL, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public GraveRegistry getGraveRegistry(@RequestBody FilterDTO filterDTO) {
        return new GraveRegistry(GraveRegistryUtil.toDTO(searchService.getGraveRegistry(toDB(filterDTO))));
    }

    @RequestMapping(value = GRAVE_REGISTRY_URL + "/count", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer getGraveRegistryCount(@RequestBody FilterDTO filterDTO) {
        return searchService.getGraveRegistryCount(toDB(filterDTO));
    }

    @RequestMapping(value = MONUMENT_REGISTRY_URL, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public MonumentRegistry getMonumentRegistry(@RequestBody FilterDTO filterDTO) {
        return new MonumentRegistry(MonumentRegistryUtil.toDTO(searchService.getMonumentRegistry(toDB(filterDTO))));
    }

    @RequestMapping(value = MONUMENT_REGISTRY_URL + "/count", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer getMonumentRegistryCount(@RequestBody FilterDTO filterDTO) {
        return searchService.getMonumentRegistryCount(toDB(filterDTO));
    }

    @RequestMapping(value = DECEASED_REGISTRY_URL, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public DeceasedRegistry getDeceasedRegistry(@PathVariable String nameOrder, @PathVariable String diedOnOrder, @RequestBody FilterDTO filterDTO) {
        return new DeceasedRegistry(DeceasedRegistryUtil.toDTO(searchService.getDeceasedRegistry(toDB(filterDTO), nameOrder, diedOnOrder)));
    }

    @RequestMapping(value = DECEASED_REGISTRY_URL + "/count", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer getDeceasedRegistryCount(@PathVariable String nameOrder, @PathVariable String diedOnOrder, @RequestBody FilterDTO filterDTO) {
        return searchService.getDeceasedRegistryCount(toDB(filterDTO), nameOrder, diedOnOrder);
    }

    @RequestMapping(value = DECEASED_NO_CAREGIVER_REGISTRY_URL, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public DeceasedNoCaregiverRegistry getDeceasedNoCaregiverRegistry(@PathVariable String nameOrder, @PathVariable String diedOnOrder, @RequestBody FilterDTO filterDTO) {
        return new DeceasedNoCaregiverRegistry(DeceasedNoCaregiverRegistryUtil.toDTO(searchService.getDeceasedNoCaregiverRegistry(toDB(filterDTO), nameOrder, diedOnOrder)));
    }

    @RequestMapping(value = DECEASED_NO_CAREGIVER_REGISTRY_URL + "/count", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer getDeceasedNoCaregiverRegistryCount(@PathVariable String nameOrder, @PathVariable String diedOnOrder, @RequestBody FilterDTO filterDTO) {
        return searchService.getDeceasedNoCaregiverRegistryCount(toDB(filterDTO), nameOrder, diedOnOrder);
    }

    @RequestMapping(value = REQUEST_REGISTRY_URL, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public RequestRegistry getRequestRegistry(@RequestBody FilterDTO filterDTO) {
        return new RequestRegistry(RequestRegistryUtil.toDTO(searchService.getRequestRegistry(toDB(filterDTO))));
    }

    @RequestMapping(value = REQUEST_REGISTRY_URL + "/count", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer getRequestRegistryCount(@RequestBody FilterDTO filterDTO) {
        return searchService.getRequestRegistryCount(toDB(filterDTO));
    }

    @RequestMapping(value = CONTRACT_REGISTRY_URL, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ContractRegistry getContractRegistry(@RequestBody FilterDTO filterDTO) {
        return new ContractRegistry(ContractRegistryUtil.toDTO(searchService.getContractRegistry(toDB(filterDTO))));
    }

    @RequestMapping(value = CONTRACT_REGISTRY_URL + "/count", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer getContractRegistryCount(@RequestBody FilterDTO filterDTO) {
        return searchService.getContractRegistryCount(toDB(filterDTO));
    }
}
