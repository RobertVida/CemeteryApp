package ro.InnovaTeam.cemeteryApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.InnovaTeam.cemeteryApp.FilterDTO;
import ro.InnovaTeam.cemeteryApp.model.Filter;
import ro.InnovaTeam.cemeteryApp.model.Parcel;
import ro.InnovaTeam.cemeteryApp.ParcelDTO;
import ro.InnovaTeam.cemeteryApp.ParcelList;
import ro.InnovaTeam.cemeteryApp.service.ParcelService;

import javax.validation.Valid;

import static ro.InnovaTeam.cemeteryApp.util.ParcelUtil.toDB;
import static ro.InnovaTeam.cemeteryApp.util.FilterUtil.toDB;
import static ro.InnovaTeam.cemeteryApp.util.ParcelUtil.toDTO;

/**
 * Created by robert on 11/18/2014.
 */
@Controller
public class ParcelController {

    public static final String PARCEL_URL = "/parcel";
    public static final String PARCELS_URL = "/parcels";
    public static final String SPECIFIC_PARCEL_URL = PARCEL_URL + "/{parcelId}";
    public static final String SPECIFIC_USER_PARCEL_URL = PARCEL_URL + "/{userId}/{parcelId}";
    public static final String SPECIFIC_CEMETERY_PARCELS_URL = PARCELS_URL + "/cemetery/{cemeteryId}";

    @Autowired
    private ParcelService parcelService;

    @RequestMapping(value = PARCEL_URL, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer create(@RequestBody @Valid ParcelDTO parcelDTO) {
        return parcelService.create(toDB(parcelDTO));
    }

    @RequestMapping(value = SPECIFIC_USER_PARCEL_URL, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ParcelDTO delete(@PathVariable Integer userId, @PathVariable Integer parcelId) {
        return toDTO(parcelService.delete(userId, parcelId));
    }

    @RequestMapping(value = SPECIFIC_PARCEL_URL, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ParcelDTO update(@PathVariable Integer parcelId, @RequestBody @Valid ParcelDTO parcelDTO) {
        Parcel parcel = toDB(parcelDTO);
        parcel.setId(parcelId);
        return toDTO(parcelService.update(parcel));
    }

    @RequestMapping(value = SPECIFIC_PARCEL_URL, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ParcelDTO findById(@PathVariable Integer parcelId) {
        return toDTO(parcelService.findById(parcelId));
    }


    @RequestMapping(value = PARCELS_URL, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ParcelList findByFilter(@RequestBody FilterDTO filterDTO) {
        return new ParcelList(toDTO(parcelService.findByFilter(toDB(filterDTO))));
    }

    @RequestMapping(value = SPECIFIC_CEMETERY_PARCELS_URL, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ParcelList findByCemeteryId(@PathVariable Integer cemeteryId) {
        return new ParcelList(toDTO(parcelService.findByFilter(new Filter(cemeteryId))));
    }
}
