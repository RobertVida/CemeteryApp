package ro.InnovaTeam.cemeteryApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.InnovaTeam.cemeteryApp.FilterDTO;
import ro.InnovaTeam.cemeteryApp.registers.BurialRegistry;
import ro.InnovaTeam.cemeteryApp.registers.DeceasedRegistry;
import ro.InnovaTeam.cemeteryApp.registers.GraveRegistry;
import ro.InnovaTeam.cemeteryApp.registers.MonumentRegistry;
import ro.InnovaTeam.cemeteryApp.service.SearchService;
import ro.InnovaTeam.cemeteryApp.util.registers.BurialRegistryUtil;
import ro.InnovaTeam.cemeteryApp.util.registers.DeceasedRegistryUtil;
import ro.InnovaTeam.cemeteryApp.util.registers.GraveRegistryUtil;
import ro.InnovaTeam.cemeteryApp.util.registers.MonumentRegistryUtil;

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
}