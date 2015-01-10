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
import ro.InnovaTeam.cemeteryApp.service.AuthenticationService;
import ro.InnovaTeam.cemeteryApp.service.DeceasedService;

import javax.validation.Valid;

import static ro.InnovaTeam.cemeteryApp.util.DeceasedUtil.toDB;
import static ro.InnovaTeam.cemeteryApp.util.DeceasedUtil.toDTO;
import static ro.InnovaTeam.cemeteryApp.util.FilterUtil.toDB;

/**
 * Created by amalia on 11/27/2014.
 */
@Controller
public class DeceasedController extends ExceptionHandledController {

    public static final String DECEASED_URL = "/deceased";
    public static final String SPECIFIC_DECEASED_URL = DECEASED_URL + "/{deceasedId}";

    @Autowired
    private DeceasedService deceasedService;
    @Autowired
    private AuthenticationService authService;

    @RequestMapping(value = DECEASED_URL, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer create(@RequestHeader("Authorization-Token") String token, @RequestBody @Valid DeceasedDTO deceasedDTO) {
        deceasedDTO.setUserId(getUserId(token));
        return deceasedService.create(toDB(deceasedDTO));
    }

    @RequestMapping(value = SPECIFIC_DECEASED_URL, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public DeceasedDTO delete(@RequestHeader("Authorization-Token") String token, @PathVariable Integer deceasedId) {
        return toDTO(deceasedService.delete(getUserId(token), deceasedId));
    }

    @RequestMapping(value = SPECIFIC_DECEASED_URL, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public DeceasedDTO update(@RequestHeader("Authorization-Token") String token, @PathVariable Integer deceasedId, @RequestBody @Valid DeceasedDTO deceasedDTO) {
        Deceased deceased = toDB(deceasedDTO);
        deceased.setUserId(getUserId(token));
        deceased.setId(deceasedId);
        return toDTO(deceasedService.update(deceased));
    }

    @RequestMapping(value = SPECIFIC_DECEASED_URL, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public DeceasedDTO findById(@RequestHeader("Authorization-Token") String token, @PathVariable Integer deceasedId) {
        isLoggedIn(token);
        return toDTO(deceasedService.findById(deceasedId));
    }

    @RequestMapping(value = DECEASED_URL, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public DeceasedList findByFilter(@RequestHeader("Authorization-Token") String token, @RequestBody FilterDTO filterDTO) {
        isLoggedIn(token);
        return new DeceasedList(toDTO(deceasedService.findByFilter(toDB(filterDTO))));
    }

    @RequestMapping(value = DECEASED_URL + "/count", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer countByFilter(@RequestHeader("Authorization-Token") String token, @RequestBody FilterDTO filterDTO) {
        isLoggedIn(token);
        return deceasedService.countByFilter(toDB(filterDTO));
    }

    private Integer getUserId(String token) {
        return authService.getAdminAccess(token).getId();
    }

    private void isLoggedIn(String token) {
        authService.hasGuestAccess(token);
    }
}
