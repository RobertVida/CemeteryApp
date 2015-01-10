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
import ro.InnovaTeam.cemeteryApp.service.AuthenticationService;
import ro.InnovaTeam.cemeteryApp.service.CemeteryService;

import javax.validation.Valid;

import static ro.InnovaTeam.cemeteryApp.util.CemeteryUtil.toDB;
import static ro.InnovaTeam.cemeteryApp.util.CemeteryUtil.toDTO;
import static ro.InnovaTeam.cemeteryApp.util.FilterUtil.toDB;

/**
 * Created by robert on 11/18/2014.
 */
@Controller
public class CemeteryController extends ExceptionHandledController{

    public static final String CEMETERY_URL = "/cemetery";
    public static final String CEMETERIES_URL = "/cemeteries";
    public static final String SPECIFIC_CEMETERY_URL = CEMETERY_URL + "/{cemeteryId}";

    @Autowired
    private CemeteryService cemeteryService;
    @Autowired
    private AuthenticationService authService;

    @RequestMapping(value = CEMETERY_URL, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer create(@RequestHeader("Authorization-Token") String token, @RequestBody @Valid CemeteryDTO cemeteryDTO) {
        cemeteryDTO.setUserId(getUserId(token));
        return cemeteryService.create(toDB(cemeteryDTO));
    }

    @RequestMapping(value = SPECIFIC_CEMETERY_URL, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CemeteryDTO delete(@RequestHeader("Authorization-Token") String token, @PathVariable Integer cemeteryId) {
        return toDTO(cemeteryService.delete(getUserId(token), cemeteryId));
    }

    @RequestMapping(value = SPECIFIC_CEMETERY_URL, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CemeteryDTO update(@RequestHeader("Authorization-Token") String token, @PathVariable Integer cemeteryId, @RequestBody @Valid CemeteryDTO cemeteryDTO) {
        Cemetery cemetery = toDB(cemeteryDTO);
        cemetery.setUserId(getUserId(token));
        cemetery.setId(cemeteryId);
        return toDTO(cemeteryService.update(cemetery));
    }

    @RequestMapping(value = SPECIFIC_CEMETERY_URL, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CemeteryDTO findById(@RequestHeader("Authorization-Token") String token, @PathVariable Integer cemeteryId) {
        isLoggedIn(token);
        return toDTO(cemeteryService.findById(cemeteryId));
    }

    @RequestMapping(value = CEMETERIES_URL, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CemeteryList findByFilter(@RequestHeader("Authorization-Token") String token, @RequestBody FilterDTO filterDTO) {
        isLoggedIn(token);
        return new CemeteryList(toDTO(cemeteryService.findByFilter(toDB(filterDTO))));
    }

    @RequestMapping(value = CEMETERIES_URL + "/count", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer countByFilter(@RequestHeader("Authorization-Token") String token, @RequestBody FilterDTO filterDTO) {
        isLoggedIn(token);
        return cemeteryService.countByFilter(toDB(filterDTO));
    }

    private Integer getUserId(String token) {
        return authService.getAdminAccess(token).getId();
    }

    private void isLoggedIn(String token) {
        authService.hasGuestAccess(token);
    }
}
