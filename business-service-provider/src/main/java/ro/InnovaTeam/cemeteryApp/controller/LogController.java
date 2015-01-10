package ro.InnovaTeam.cemeteryApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.InnovaTeam.cemeteryApp.FilterDTO;
import ro.InnovaTeam.cemeteryApp.LogEntryDTO;
import ro.InnovaTeam.cemeteryApp.LogEntryList;
import ro.InnovaTeam.cemeteryApp.service.AuthenticationService;
import ro.InnovaTeam.cemeteryApp.service.LogEntryService;

import static ro.InnovaTeam.cemeteryApp.util.FilterUtil.toDB;
import static ro.InnovaTeam.cemeteryApp.util.LogEntryUtil.toDTO;

/**
 * Created by robert on 12/14/2014.
 */
@Controller
public class LogController extends ExceptionHandledController {

    public static final String LOG_URL = "/log/{logId}";
    public static final String LOGS_URL = "/logs";
    public static final String LOGS_FOR_ENTITY_URL = LOGS_URL + "/{entityName}";
    public static final String LOGS_FOR_ENTITY_AND_ID_URL = LOGS_FOR_ENTITY_URL + "/{entityId}";

    @Autowired
    private LogEntryService logEntryService;
    @Autowired
    private AuthenticationService authService;

    @RequestMapping(value = LOGS_URL, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public LogEntryList getLogs(@RequestHeader("Authorization-Token") String token, @RequestBody FilterDTO filterDTO) {
        isLoggedIn(token);
        return new LogEntryList(toDTO(logEntryService.findByFilter(toDB(filterDTO))));
    }

    @RequestMapping(value = LOGS_URL + "/count", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer getLogCount(@RequestHeader("Authorization-Token") String token, @RequestBody FilterDTO filterDTO) {
        isLoggedIn(token);
        return logEntryService.countByFilter(toDB(filterDTO));
    }

    @RequestMapping(value = LOGS_FOR_ENTITY_URL, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public LogEntryList getLogs(@RequestHeader("Authorization-Token") String token, @RequestBody FilterDTO filterDTO, @PathVariable String entityName) {
        isLoggedIn(token);
        return new LogEntryList(toDTO(logEntryService.findByFilter(toDB(filterDTO), entityName)));
    }

    @RequestMapping(value = LOGS_FOR_ENTITY_URL + "/count", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer getLogCount(@RequestHeader("Authorization-Token") String token, @RequestBody FilterDTO filterDTO, @PathVariable String entityName) {
        isLoggedIn(token);
        return logEntryService.countByFilter(toDB(filterDTO), entityName);
    }

    @RequestMapping(value = LOGS_FOR_ENTITY_AND_ID_URL, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public LogEntryList getLogs(@RequestHeader("Authorization-Token") String token, @RequestBody FilterDTO filterDTO, @PathVariable String entityName, @PathVariable Integer entityId) {
        isLoggedIn(token);
        return new LogEntryList(toDTO(logEntryService.findByFilter(toDB(filterDTO), entityName, entityId)));
    }

    @RequestMapping(value = LOGS_FOR_ENTITY_AND_ID_URL + "/count", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer getLogCount(@RequestHeader("Authorization-Token") String token, @RequestBody FilterDTO filterDTO, @PathVariable String entityName, @PathVariable Integer entityId) {
        isLoggedIn(token);
        return logEntryService.countByFilter(toDB(filterDTO), entityName, entityId);
    }

    @RequestMapping(value = LOG_URL, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public LogEntryDTO getLog(@RequestHeader("Authorization-Token") String token, @PathVariable Integer logId) {
        isLoggedIn(token);
        return toDTO(logEntryService.findById(logId));
    }

    private void isLoggedIn(String token) {
        authService.hasGuestAccess(token);
    }
}
