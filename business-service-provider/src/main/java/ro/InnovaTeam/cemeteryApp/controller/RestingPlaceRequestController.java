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
import ro.InnovaTeam.cemeteryApp.service.AuthenticationService;
import ro.InnovaTeam.cemeteryApp.service.RestingPlaceRequestService;

import javax.validation.Valid;

import static ro.InnovaTeam.cemeteryApp.util.FilterUtil.toDB;
import static ro.InnovaTeam.cemeteryApp.util.RestingPlaceRequestUtil.toDB;
import static ro.InnovaTeam.cemeteryApp.util.RestingPlaceRequestUtil.toDTO;

/**
 * Created by robert on 11/28/2014.
 */
@Controller
public class RestingPlaceRequestController extends ExceptionHandledController {

    public static final String REQUEST_URL = "/request";
    public static final String REQUESTS_URL = "/requests";
    public static final String REQUESTS_FOR_STATUS_URL = REQUESTS_URL + "/{status}";
    public static final String SPECIFIC_REQUEST_URL = REQUEST_URL + "/{requestId}";

    @Autowired
    private RestingPlaceRequestService requestService;
    @Autowired
    private AuthenticationService authService;

    @RequestMapping(value = REQUEST_URL, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer createRequest(@RequestHeader("Authorization-Token") String token, @RequestBody @Valid RestingPlaceRequestDTO requestDTO) {
        requestDTO.setUserId(getUserId(token));
        return requestService.create(toDB(requestDTO));
    }

    @RequestMapping(value = SPECIFIC_REQUEST_URL, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    private RestingPlaceRequestDTO deleteRequest(@RequestHeader("Authorization-Token") String token, @PathVariable Integer requestId) {
        return toDTO(requestService.delete(getUserId(token), requestId));
    }

    @RequestMapping(value = SPECIFIC_REQUEST_URL, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public RestingPlaceRequestDTO updateRequest(@RequestHeader("Authorization-Token") String token, @PathVariable Integer requestId, @RequestBody @Valid RestingPlaceRequestDTO requestDTO) {
        RestingPlaceRequest request = toDB(requestDTO);
        request.setUserId(getUserId(token));
        request.setId(requestId);
        return toDTO(requestService.update(request));
    }

    @RequestMapping(value = SPECIFIC_REQUEST_URL, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public RestingPlaceRequestDTO findRequestById(@RequestHeader("Authorization-Token") String token, @PathVariable Integer requestId) {
        isLoggedIn(token);
        return toDTO(requestService.findById(requestId));
    }

    @RequestMapping(value = REQUESTS_URL, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public RestingPlaceRequestList findRequestByFilter(@RequestHeader("Authorization-Token") String token, @RequestBody @Valid FilterDTO filterDTO) {
        isLoggedIn(token);
        return new RestingPlaceRequestList(toDTO(requestService.findByFilter(toDB(filterDTO))));
    }

    @RequestMapping(value = REQUESTS_URL + "/count", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer countRequestByFilter(@RequestHeader("Authorization-Token") String token, @RequestBody @Valid FilterDTO filterDTO) {
        isLoggedIn(token);
        return requestService.countByFilter(toDB(filterDTO));
    }

    @RequestMapping(value = REQUESTS_FOR_STATUS_URL, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public RestingPlaceRequestList findRequestByFilterAndStatus(@RequestHeader("Authorization-Token") String token, @RequestBody @Valid FilterDTO filterDTO, @PathVariable String status) {
        isLoggedIn(token);
        return new RestingPlaceRequestList(toDTO(requestService.findByFilterAndStatus(toDB(filterDTO), status)));
    }

    @RequestMapping(value = REQUESTS_FOR_STATUS_URL + "/count", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer countRequestByFilterAndStatus(@RequestHeader("Authorization-Token") String token, @RequestBody @Valid FilterDTO filterDTO, @PathVariable String status) {
        isLoggedIn(token);
        return requestService.countByFilterAndStatus(toDB(filterDTO), status);
    }

    private Integer getUserId(String token) {
        return authService.getAdminAccess(token).getId();
    }

    private void isLoggedIn(String token) {
        authService.hasGuestAccess(token);
    }
}
