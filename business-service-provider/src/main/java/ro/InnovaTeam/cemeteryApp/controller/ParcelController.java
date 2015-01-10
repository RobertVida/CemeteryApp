package ro.InnovaTeam.cemeteryApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.InnovaTeam.cemeteryApp.FilterDTO;
import ro.InnovaTeam.cemeteryApp.ParcelDTO;
import ro.InnovaTeam.cemeteryApp.ParcelList;
import ro.InnovaTeam.cemeteryApp.model.Filter;
import ro.InnovaTeam.cemeteryApp.model.Parcel;
import ro.InnovaTeam.cemeteryApp.service.AuthenticationService;
import ro.InnovaTeam.cemeteryApp.service.ParcelService;

import javax.validation.Valid;

import static ro.InnovaTeam.cemeteryApp.util.FilterUtil.toDB;
import static ro.InnovaTeam.cemeteryApp.util.ParcelUtil.toDB;
import static ro.InnovaTeam.cemeteryApp.util.ParcelUtil.toDTO;

/**
 * Created by robert on 11/18/2014.
 */
@Controller
public class ParcelController extends ExceptionHandledController {

    public static final String PARCEL_URL = "/parcel";
    public static final String PARCELS_URL = "/parcels";
    public static final String SPECIFIC_PARCEL_URL = PARCEL_URL + "/{parcelId}";
    public static final String SPECIFIC_CEMETERY_PARCELS_URL = PARCELS_URL + "/cemetery/{cemeteryId}";

    @Autowired
    private ParcelService parcelService;
    @Autowired
    private AuthenticationService authService;

    @RequestMapping(value = PARCEL_URL, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer create(@RequestHeader("Authorization-Token") String token, @RequestBody @Valid ParcelDTO parcelDTO) {
        parcelDTO.setUserId(getUserId(token));
        return parcelService.create(toDB(parcelDTO));
    }

    @RequestMapping(value = SPECIFIC_PARCEL_URL, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ParcelDTO delete(@RequestHeader("Authorization-Token") String token, @PathVariable Integer parcelId) {
        return toDTO(parcelService.delete(getUserId(token), parcelId));
    }

    @RequestMapping(value = SPECIFIC_PARCEL_URL, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ParcelDTO update(@RequestHeader("Authorization-Token") String token, @PathVariable Integer parcelId, @RequestBody @Valid ParcelDTO parcelDTO) {
        Parcel parcel = toDB(parcelDTO);
        parcel.setUserId(getUserId(token));
        parcel.setId(parcelId);
        return toDTO(parcelService.update(parcel));
    }

    @RequestMapping(value = SPECIFIC_PARCEL_URL, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ParcelDTO findById(@RequestHeader("Authorization-Token") String token, @PathVariable Integer parcelId) {
        isLoggedIn(token);
        return toDTO(parcelService.findById(parcelId));
    }

    @RequestMapping(value = PARCELS_URL, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ParcelList findByFilter(@RequestHeader("Authorization-Token") String token, @RequestBody FilterDTO filterDTO) {
        isLoggedIn(token);
        return new ParcelList(toDTO(parcelService.findByFilter(toDB(filterDTO))));
    }

    @RequestMapping(value = PARCELS_URL + "/count", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer countByFilter(@RequestHeader("Authorization-Token") String token, @RequestBody FilterDTO filterDTO) {
        isLoggedIn(token);
        return parcelService.countByFilter(toDB(filterDTO));
    }

    @RequestMapping(value = SPECIFIC_CEMETERY_PARCELS_URL, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ParcelList findByCemeteryId(@RequestHeader("Authorization-Token") String token, @PathVariable Integer cemeteryId) {
        isLoggedIn(token);
        return new ParcelList(toDTO(parcelService.findByFilter(new Filter(cemeteryId))));
    }

    private Integer getUserId(String token) {
        return authService.getAdminAccess(token).getId();
    }

    private void isLoggedIn(String token) {
        authService.hasGuestAccess(token);
    }
}
