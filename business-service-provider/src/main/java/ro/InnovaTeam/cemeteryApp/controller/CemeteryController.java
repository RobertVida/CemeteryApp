package ro.InnovaTeam.cemeteryApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.InnovaTeam.cemeteryApp.model.Cemetery;
import ro.InnovaTeam.cemeteryApp.service.CemeteryService;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by robert on 11/18/2014.
 */
@Controller
public class CemeteryController{

    public static final String CEMETERY_URL = "/cemetery";
    public static final String CEMETERIES_URL = "/cemeteries";
    public static final String SPECIFIC_CEMETERY_URL = CEMETERY_URL + "/{cemeteryId}";

    @Autowired
    private CemeteryService cemeteryService;

    @RequestMapping(value = CEMETERY_URL, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer create(@RequestBody @Valid Cemetery cemetery) {
        return cemeteryService.create(cemetery);
    }

    @RequestMapping(value = SPECIFIC_CEMETERY_URL, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Cemetery delete(@PathVariable Integer cemeteryId) {
        return cemeteryService.delete(cemeteryId);
    }

    @RequestMapping(value = SPECIFIC_CEMETERY_URL, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Cemetery update(@PathVariable Integer cemeteryId, @RequestBody @Valid Cemetery cemetery) {
        cemetery.setId(cemeteryId);
        return cemeteryService.update(cemetery);
    }
    
    @RequestMapping(value = SPECIFIC_CEMETERY_URL, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Cemetery findById(@PathVariable Integer cemeteryId) {
        return cemeteryService.findById(cemeteryId);
    }

    @RequestMapping(value = CEMETERIES_URL, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Cemetery> findByFilter() {
        return cemeteryService.findByFilter();
    }

}
