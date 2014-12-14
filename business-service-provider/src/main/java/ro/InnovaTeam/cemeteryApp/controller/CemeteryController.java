package ro.InnovaTeam.cemeteryApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.InnovaTeam.cemeteryApp.CemeteryDTO;
import ro.InnovaTeam.cemeteryApp.CemeteryList;
import ro.InnovaTeam.cemeteryApp.FilterDTO;
import ro.InnovaTeam.cemeteryApp.model.Cemetery;
import ro.InnovaTeam.cemeteryApp.service.CemeteryService;

import javax.validation.Valid;

import static ro.InnovaTeam.cemeteryApp.util.CemeteryUtil.toDB;
import static ro.InnovaTeam.cemeteryApp.util.FilterUtil.toDB;
import static ro.InnovaTeam.cemeteryApp.util.CemeteryUtil.toDTO;

/**
 * Created by robert on 11/18/2014.
 */
@Controller
public class CemeteryController{

    public static final String CEMETERY_URL = "/cemetery";
    public static final String CEMETERIES_URL = "/cemeteries";
    public static final String SPECIFIC_CEMETERY_URL = CEMETERY_URL + "/{cemeteryId}";
    public static final String SPECIFIC_USER_CEMETERY_URL = CEMETERY_URL + "/{userId}/{cemeteryId}";

    @Autowired
    private CemeteryService cemeteryService;

    @RequestMapping(value = CEMETERY_URL, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer create(@RequestBody @Valid CemeteryDTO cemeteryDTO) {
        return cemeteryService.create(toDB(cemeteryDTO));
    }

    @RequestMapping(value = SPECIFIC_USER_CEMETERY_URL, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CemeteryDTO delete(@PathVariable Integer userId, @PathVariable Integer cemeteryId) {
        return toDTO(cemeteryService.delete(cemeteryId));
    }

    @RequestMapping(value = SPECIFIC_CEMETERY_URL, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CemeteryDTO update(@PathVariable Integer cemeteryId, @RequestBody @Valid CemeteryDTO cemeteryDTO) {
        Cemetery cemetery = toDB(cemeteryDTO);
        cemetery.setId(cemeteryId);
        return toDTO(cemeteryService.update(cemetery));
    }
    
    @RequestMapping(value = SPECIFIC_CEMETERY_URL, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CemeteryDTO findById(@PathVariable Integer cemeteryId) {
        return toDTO(cemeteryService.findById(cemeteryId));
    }

    @RequestMapping(value = CEMETERIES_URL, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CemeteryList findByFilter(@RequestBody FilterDTO filterDTO) {
        return new CemeteryList(toDTO(cemeteryService.findByFilter(toDB(filterDTO))));
    }

}
