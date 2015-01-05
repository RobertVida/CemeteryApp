package ro.InnovaTeam.cemeteryApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.InnovaTeam.cemeteryApp.FilterDTO;
import ro.InnovaTeam.cemeteryApp.registers.BurialRegistry;
import ro.InnovaTeam.cemeteryApp.service.SearchService;

import static ro.InnovaTeam.cemeteryApp.util.FilterUtil.toDB;
import static ro.InnovaTeam.cemeteryApp.util.registers.BurialRegistryUtil.toDTO;

/**
 * Created by robert on 1/5/2015.
 */
@Controller
public class SearchController {

    public static final String BURIAL_REGISTRY_URL = "/burialRegistry";

    @Autowired
    private SearchService searchService;

    @RequestMapping(value = BURIAL_REGISTRY_URL, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public BurialRegistry getBurialRegistry(@RequestBody FilterDTO filterDTO) {
        return new BurialRegistry(toDTO(searchService.getBurialRegistry(toDB(filterDTO))));
    }

    @RequestMapping(value = BURIAL_REGISTRY_URL + "/count", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer getBurialRegistryCount(@RequestBody FilterDTO filterDTO) {
        return searchService.getBurialRegistryCount(toDB(filterDTO));
    }
}
