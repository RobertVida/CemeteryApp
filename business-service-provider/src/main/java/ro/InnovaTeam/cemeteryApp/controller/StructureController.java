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
import ro.InnovaTeam.cemeteryApp.service.AuthenticationService;
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

    public static final String MONUMENT_URL = "/monument";
    public static final String MONUMENTS_URL = "/monuments";
    public static final String SPECIFIC_MONUMENT_URL = MONUMENT_URL + "/{monumentId}";

    @Autowired
    private GraveService graveService;
    @Autowired
    private MonumentService monumentService;
    @Autowired
    private AuthenticationService authService;

    @RequestMapping(value = GRAVE_URL, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer createGrave(@RequestHeader("Authorization-Token") String token, @RequestBody @Valid GraveDTO graveDTO) {
        graveDTO.setType(StructureType.Grave.toString());
        graveDTO.setUserId(getUserId(token));
        return graveService.create(GraveUtil.toDB(graveDTO));
    }

    @RequestMapping(value = MONUMENT_URL, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer createMonument(@RequestHeader("Authorization-Token") String token, @RequestBody @Valid MonumentDTO monumentDTO) {
        monumentDTO.setType(StructureType.Monument.toString());
        monumentDTO.setUserId(getUserId(token));
        return monumentService.create(MonumentUtil.toDB(monumentDTO));
    }

    @RequestMapping(value = SPECIFIC_GRAVE_URL, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    private GraveDTO deleteGrave(@RequestHeader("Authorization-Token") String token, @PathVariable Integer graveId) {
        return GraveUtil.toDTO(graveService.delete(getUserId(token), graveId));
    }

    @RequestMapping(value = SPECIFIC_MONUMENT_URL, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    private MonumentDTO deleteMonument(@RequestHeader("Authorization-Token") String token, @PathVariable Integer monumentId) {
        return MonumentUtil.toDTO(monumentService.delete(getUserId(token), monumentId));
    }

    @RequestMapping(value = SPECIFIC_GRAVE_URL, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public GraveDTO updateGrave(@RequestHeader("Authorization-Token") String token, @PathVariable Integer graveId, @RequestBody @Valid GraveDTO graveDTO) {
        Grave grave = GraveUtil.toDB(graveDTO);
        grave.setId(graveId);
        grave.setUserId(getUserId(token));
        grave.setType(StructureType.Grave.toString());
        return GraveUtil.toDTO(graveService.update(grave));
    }

    @RequestMapping(value = SPECIFIC_MONUMENT_URL, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public MonumentDTO updateMonument(@RequestHeader("Authorization-Token") String token, @PathVariable Integer monumentId, @RequestBody @Valid MonumentDTO monumentDTO) {
        Monument monument = MonumentUtil.toDB(monumentDTO);
        monument.setId(monumentId);
        monument.setUserId(getUserId(token));
        monument.setType(StructureType.Monument.toString());
        return MonumentUtil.toDTO(monumentService.update(monument));
    }

    @RequestMapping(value = SPECIFIC_GRAVE_URL, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public GraveDTO findGraveById(@RequestHeader("Authorization-Token") String token, @PathVariable Integer graveId) {
        isLoggedIn(token);
        return GraveUtil.toDTO(graveService.findById(graveId));
    }

    @RequestMapping(value = SPECIFIC_MONUMENT_URL, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public MonumentDTO findMonumentById(@RequestHeader("Authorization-Token") String token, @PathVariable Integer monumentId) {
        isLoggedIn(token);
        return MonumentUtil.toDTO(monumentService.findById(monumentId));
    }

    @RequestMapping(value = GRAVES_URL, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public GraveList findGraveByFilter(@RequestHeader("Authorization-Token") String token, @RequestBody @Valid FilterDTO filterDTO) {
        isLoggedIn(token);
        return new GraveList(GraveUtil.toDTO(graveService.findByFilter(toDB(filterDTO))));
    }

    @RequestMapping(value = GRAVES_URL + "/count", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer countGraveByFilter(@RequestHeader("Authorization-Token") String token, @RequestBody @Valid FilterDTO filterDTO) {
        isLoggedIn(token);
        return graveService.countByFilter(toDB(filterDTO));
    }

    @RequestMapping(value = MONUMENTS_URL, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public MonumentList findMonumentByFilter(@RequestHeader("Authorization-Token") String token, @RequestBody @Valid FilterDTO filterDTO) {
        isLoggedIn(token);
        return new MonumentList(MonumentUtil.toDTO(monumentService.findByFilter(toDB(filterDTO))));
    }

    @RequestMapping(value = MONUMENTS_URL + "/count", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer countMonumentByFilter(@RequestHeader("Authorization-Token") String token, @RequestBody @Valid FilterDTO filterDTO) {
        isLoggedIn(token);
        return monumentService.countByFilter(toDB(filterDTO));
    }

    private Integer getUserId(String token) {
        return authService.getAdminAccess(token).getId();
    }

    private void isLoggedIn(String token) {
        authService.hasGuestAccess(token);
    }
}
