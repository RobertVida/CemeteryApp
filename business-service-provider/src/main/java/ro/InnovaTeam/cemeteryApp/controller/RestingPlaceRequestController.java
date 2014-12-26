package ro.InnovaTeam.cemeteryApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.InnovaTeam.cemeteryApp.FilterDTO;
import ro.InnovaTeam.cemeteryApp.RestingPlaceRequestDTO;
import ro.InnovaTeam.cemeteryApp.RestingPlaceRequestList;
import ro.InnovaTeam.cemeteryApp.model.RestingPlaceRequest;
import ro.InnovaTeam.cemeteryApp.service.RestingPlaceRequestService;

import javax.validation.Valid;

import static ro.InnovaTeam.cemeteryApp.util.FilterUtil.toDB;
import static ro.InnovaTeam.cemeteryApp.util.RestingPlaceRequestUtil.toDB;
import static ro.InnovaTeam.cemeteryApp.util.RestingPlaceRequestUtil.toDTO;

/**
 * Created by robert on 11/28/2014.
 */
@Controller
public class RestingPlaceRequestController {

    public static final String REQUEST_URL = "/request";
    public static final String REQUESTS_URL = "/requests";
    public static final String REQUESTS_FOR_STATUS_URL = REQUESTS_URL + "/{status}";
    public static final String SPECIFIC_REQUEST_URL = REQUEST_URL + "/{requestId}";
    public static final String SPECIFIC_USER_REQUEST_URL = REQUEST_URL + "/{userId}/{requestId}";

    @Autowired
    private RestingPlaceRequestService requestService;

    @RequestMapping(value = REQUEST_URL, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer createRequest(@RequestBody @Valid RestingPlaceRequestDTO requestDTO) {
        return requestService.create(toDB(requestDTO));
    }

    @RequestMapping(value = SPECIFIC_USER_REQUEST_URL, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    private RestingPlaceRequestDTO deleteRequest(@PathVariable Integer userId, @PathVariable Integer requestId) {
        return toDTO(requestService.delete(userId, requestId));
    }

    @RequestMapping(value = SPECIFIC_REQUEST_URL, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public RestingPlaceRequestDTO updateRequest(@PathVariable Integer requestId, @RequestBody @Valid RestingPlaceRequestDTO requestDTO) {
        RestingPlaceRequest request = toDB(requestDTO);
        request.setId(requestId);
        return toDTO(requestService.update(request));
    }

    @RequestMapping(value = SPECIFIC_REQUEST_URL, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public RestingPlaceRequestDTO findRequestById(@PathVariable Integer requestId) {
        return toDTO(requestService.findById(requestId));
    }

    @RequestMapping(value = REQUESTS_URL, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public RestingPlaceRequestList findRequestByFilter(@RequestBody @Valid FilterDTO filterDTO) {
        return new RestingPlaceRequestList(toDTO(requestService.findByFilter(toDB(filterDTO))));
    }

    @RequestMapping(value = REQUESTS_FOR_STATUS_URL, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public RestingPlaceRequestList findRequestByFilterAndStatus(@RequestBody @Valid FilterDTO filterDTO, @PathVariable String status) {
        return new RestingPlaceRequestList(toDTO(requestService.findByFilterAndStatus(toDB(filterDTO), status)));
    }
}
