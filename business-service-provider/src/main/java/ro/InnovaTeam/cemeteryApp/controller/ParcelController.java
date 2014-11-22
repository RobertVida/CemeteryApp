package ro.InnovaTeam.cemeteryApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.InnovaTeam.cemeteryApp.model.Parcel;
import ro.InnovaTeam.cemeteryApp.service.ParcelService;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by robert on 11/18/2014.
 */
@Controller
public class ParcelController {

    public static final String PARCEL_URL = "/parcel";
    public static final String PARCELS_URL = "/parcels";
    public static final String SPECIFIC_PARCEL_URL = PARCEL_URL + "/{parcelId}";
    public static final String SPECIFIC_CEMETERY_PARCELS_URL = PARCEL_URL + "/cemetery/{cemeteryId}";

    @Autowired
    private ParcelService parcelService;

    @RequestMapping(value = PARCEL_URL, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer create(@RequestBody @Valid Parcel parcel) {
        return parcelService.create(parcel);
    }

    @RequestMapping(value = SPECIFIC_PARCEL_URL, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Parcel delete(@PathVariable Integer parcelId) {
        return parcelService.delete(parcelId);
    }

    @RequestMapping(value = SPECIFIC_PARCEL_URL, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Parcel update(@PathVariable Integer parcelId, @RequestBody @Valid Parcel parcel) {
        parcel.setId(parcelId);
        return parcelService.update(parcel);
    }

    @RequestMapping(value = SPECIFIC_PARCEL_URL, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Parcel findById(@PathVariable Integer parcelId) {
        return parcelService.findById(parcelId);
    }

    @RequestMapping(value = SPECIFIC_CEMETERY_PARCELS_URL, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Parcel> findByCemeteryId(@PathVariable Integer cemeteryId) {
        return parcelService.findByCemeteryId(cemeteryId);
    }

    @RequestMapping(value = PARCELS_URL, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Parcel> findByFilter() {
        return parcelService.findByFilter();
    }
}
