package ro.InnovaTeam.cemeteryApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.InnovaTeam.cemeteryApp.*;
import ro.InnovaTeam.cemeteryApp.model.Grave;
import ro.InnovaTeam.cemeteryApp.model.Monument;
import ro.InnovaTeam.cemeteryApp.model.StructureType;
import ro.InnovaTeam.cemeteryApp.service.GraveService;
import ro.InnovaTeam.cemeteryApp.service.MonumentService;
import ro.InnovaTeam.cemeteryApp.util.GraveUtil;
import ro.InnovaTeam.cemeteryApp.util.MonumentUtil;

import javax.validation.Valid;

import static ro.InnovaTeam.cemeteryApp.util.FilterUtil.toDB;

/**
 * Created by robert on 11/28/2014.
 */
@Controller
public class StructureController extends ExceptionHandledController {

    public static final String GRAVE_URL = "/grave";
    public static final String GRAVES_URL = "/graves";
    public static final String SPECIFIC_GRAVE_URL = GRAVE_URL + "/{graveId}";
    public static final String SPECIFIC_USER_GRAVE_URL = GRAVE_URL + "/{userId}/{graveId}";

    public static final String MONUMENT_URL = "/monument";
    public static final String MONUMENTS_URL = "/monuments";
    public static final String SPECIFIC_MONUMENT_URL = MONUMENT_URL + "/{monumentId}";
    public static final String SPECIFIC_USER_MONUMENT_URL = MONUMENT_URL + "/{userId}/{monumentId}";

    @Autowired
    private GraveService graveService;
    @Autowired
    private MonumentService monumentService;

    @RequestMapping(value = GRAVE_URL, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer createGrave(@RequestBody @Valid GraveDTO graveDTO) {
        graveDTO.setType(StructureType.Grave.toString());
        return graveService.create(GraveUtil.toDB(graveDTO));
    }

    @RequestMapping(value = MONUMENT_URL, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer createMonument(@RequestBody @Valid MonumentDTO monumentDTO) {
        monumentDTO.setType(StructureType.Monument.toString());
        return monumentService.create(MonumentUtil.toDB(monumentDTO));
    }

    @RequestMapping(value = SPECIFIC_USER_GRAVE_URL, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    private GraveDTO deleteGrave(@PathVariable Integer userId, @PathVariable Integer graveId) {
        return GraveUtil.toDTO(graveService.delete(userId, graveId));
    }

    @RequestMapping(value = SPECIFIC_USER_MONUMENT_URL, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    private MonumentDTO deleteMonument(@PathVariable Integer userId, @PathVariable Integer monumentId) {
        return MonumentUtil.toDTO(monumentService.delete(userId, monumentId));
    }

    @RequestMapping(value = SPECIFIC_GRAVE_URL, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public GraveDTO updateGrave(@PathVariable Integer graveId, @RequestBody @Valid GraveDTO graveDTO) {
        Grave grave = GraveUtil.toDB(graveDTO);
        grave.setId(graveId);
        grave.setType(StructureType.Grave.toString());
        return GraveUtil.toDTO(graveService.update(grave));
    }

    @RequestMapping(value = SPECIFIC_MONUMENT_URL, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public MonumentDTO updateMonument(@PathVariable Integer monumentId, @RequestBody @Valid MonumentDTO monumentDTO) {
        Monument monument = MonumentUtil.toDB(monumentDTO);
        monument.setId(monumentId);
        monument.setType(StructureType.Monument.toString());
        return MonumentUtil.toDTO(monumentService.update(monument));
    }

    @RequestMapping(value = SPECIFIC_GRAVE_URL, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public GraveDTO findGraveById(@PathVariable Integer graveId) {
        return GraveUtil.toDTO(graveService.findById(graveId));
    }

    @RequestMapping(value = SPECIFIC_MONUMENT_URL, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public MonumentDTO findMonumentById(@PathVariable Integer monumentId) {
        return MonumentUtil.toDTO(monumentService.findById(monumentId));
    }

    @RequestMapping(value = GRAVES_URL, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public GraveList findGraveByFilter(@RequestBody @Valid FilterDTO filterDTO) {
        return new GraveList(GraveUtil.toDTO(graveService.findByFilter(toDB(filterDTO))));
    }

    @RequestMapping(value = GRAVES_URL + "/count", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer countGraveByFilter(@RequestBody @Valid FilterDTO filterDTO) {
        return graveService.countByFilter(toDB(filterDTO));
    }

    @RequestMapping(value = MONUMENTS_URL, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public MonumentList findMonumentByFilter(@RequestBody @Valid FilterDTO filterDTO) {
        return new MonumentList(MonumentUtil.toDTO(monumentService.findByFilter(toDB(filterDTO))));
    }

    @RequestMapping(value = MONUMENTS_URL + "/count", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer countMonumentByFilter(@RequestBody @Valid FilterDTO filterDTO) {
        return monumentService.countByFilter(toDB(filterDTO));
    }
}
