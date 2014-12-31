package ro.InnovaTeam.cemeteryApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.InnovaTeam.cemeteryApp.DeceasedDTO;
import ro.InnovaTeam.cemeteryApp.DeceasedList;
import ro.InnovaTeam.cemeteryApp.FilterDTO;
import ro.InnovaTeam.cemeteryApp.model.Deceased;
import ro.InnovaTeam.cemeteryApp.service.DeceasedService;

import javax.validation.Valid;

import static ro.InnovaTeam.cemeteryApp.util.DeceasedUtil.toDTO;
import static ro.InnovaTeam.cemeteryApp.util.FilterUtil.toDB;
import static ro.InnovaTeam.cemeteryApp.util.DeceasedUtil.toDB;

/**
 * Created by amalia on 11/27/2014.
 */
@Controller
public class DeceasedController {

    public static final String DECEASED_URL = "/deceased";
    public static final String SPECIFIC_DECEASED_URL = DECEASED_URL + "/{deceasedId}";
    public static final String SPECIFIC_USER_DECEASED_URL = DECEASED_URL + "/{userId}/{deceasedId}";

    @Autowired
    private DeceasedService deceasedService;

    @RequestMapping(value = DECEASED_URL, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer create(@RequestBody @Valid DeceasedDTO deceasedDTO) {
        return deceasedService.create(toDB(deceasedDTO));
    }

    @RequestMapping(value = SPECIFIC_USER_DECEASED_URL, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public DeceasedDTO delete(@PathVariable Integer userId, @PathVariable Integer deceasedId) {
        return toDTO(deceasedService.delete(userId, deceasedId));
    }

    @RequestMapping(value = SPECIFIC_DECEASED_URL, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public DeceasedDTO update(@PathVariable Integer deceasedId, @RequestBody @Valid DeceasedDTO deceasedDTO) {
        Deceased deceased = toDB(deceasedDTO);
        deceased.setId(deceasedId);
        return toDTO(deceasedService.update(deceased));
    }
    
    @RequestMapping(value = SPECIFIC_DECEASED_URL, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public DeceasedDTO findById(@PathVariable Integer deceasedId) {
        return toDTO(deceasedService.findById(deceasedId));
    }

    @RequestMapping(value = DECEASED_URL, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public DeceasedList findByFilter(@RequestBody FilterDTO filterDTO) {
        return new DeceasedList(toDTO(deceasedService.findByFilter(toDB(filterDTO))));
    }

    @RequestMapping(value = DECEASED_URL + "/count", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer countByFilter(@RequestBody FilterDTO filterDTO) {
        return deceasedService.countByFilter(toDB(filterDTO));
    }
}
