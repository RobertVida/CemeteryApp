package ro.InnovaTeam.cemeteryApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.InnovaTeam.cemeteryApp.FilterDTO;
import ro.InnovaTeam.cemeteryApp.GraveDTO;
import ro.InnovaTeam.cemeteryApp.GraveList;
import ro.InnovaTeam.cemeteryApp.enums.StructureType;
import ro.InnovaTeam.cemeteryApp.model.Grave;
import ro.InnovaTeam.cemeteryApp.service.GraveService;

import javax.validation.Valid;

import static ro.InnovaTeam.cemeteryApp.util.FilterUtil.toDB;
import static ro.InnovaTeam.cemeteryApp.util.StructureUtil.toDB;
import static ro.InnovaTeam.cemeteryApp.util.StructureUtil.toDTO;

/**
 * Created by robert on 11/28/2014.
 */
@Controller
public class StructureController {

    public static final String GRAVE_URL = "/grave";
    public static final String GRAVES_URL = "/graves";
    public static final String SPECIFIC_GRAVE_URL = GRAVE_URL + "/{graveId}";
    public static final String SPECIFIC_USER_GRAVE_URL = GRAVE_URL + "/{userId}/{graveId}";

    @Autowired
    private GraveService graveService;

    @RequestMapping(value = GRAVE_URL, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer createGrave(@RequestBody @Valid GraveDTO graveDTO){
        graveDTO.setType(StructureType.Grave.toString());
        return graveService.create(toDB(graveDTO));
    }

    @RequestMapping(value = SPECIFIC_USER_GRAVE_URL, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    private GraveDTO deleteGrave(@PathVariable Integer userId, @PathVariable Integer graveId){
        return toDTO(graveService.delete(graveId));
    }

    @RequestMapping(value = SPECIFIC_GRAVE_URL, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public GraveDTO updateGrave(@PathVariable Integer graveId, @RequestBody @Valid GraveDTO graveDTO){
        Grave grave = toDB(graveDTO);
        grave.setId(graveId);
        grave.setType(StructureType.Grave.toString());
        return toDTO(graveService.update(grave));
    }

    @RequestMapping(value = SPECIFIC_GRAVE_URL, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public GraveDTO findGraveById(@PathVariable Integer graveId){
        return toDTO(graveService.findById(graveId));
    }

    @RequestMapping(value = GRAVES_URL, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public GraveList findGraveByFilter(@RequestBody @Valid FilterDTO filterDTO){
        return new GraveList(toDTO(graveService.findByFilter(toDB(filterDTO))));
    }
}
