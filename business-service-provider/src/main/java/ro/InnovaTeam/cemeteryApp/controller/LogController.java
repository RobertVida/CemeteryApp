package ro.InnovaTeam.cemeteryApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.InnovaTeam.cemeteryApp.LogEntryList;
import ro.InnovaTeam.cemeteryApp.model.Filter;
import ro.InnovaTeam.cemeteryApp.service.LogEntryService;

import static ro.InnovaTeam.cemeteryApp.util.LogEntryUtil.toDTO;

/**
 * Created by robert on 12/14/2014.
 */
@Controller
public class LogController {

    public static final String LOGS_URL = "/logs";
    public static final String LOGS_FOR_ENTITY_URL = LOGS_URL + "/{entityName}";
    public static final String LOGS_FOR_ENTITY_AND_ID_URL = LOGS_FOR_ENTITY_URL + "/{entityId}";

    @Autowired
    private LogEntryService logEntryService;

    @RequestMapping(value = LOGS_URL, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public LogEntryList getLogs(){
        return new LogEntryList(toDTO(logEntryService.findByFilter(new Filter())));
    }

    @RequestMapping(value = LOGS_FOR_ENTITY_URL, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public LogEntryList getLogs(@PathVariable String entityName){
        return new LogEntryList(toDTO(logEntryService.findByFilter(new Filter(), entityName)));
    }

    @RequestMapping(value = LOGS_FOR_ENTITY_AND_ID_URL, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public LogEntryList getLogs(@PathVariable String entityName, @PathVariable Integer entityId){
        return new LogEntryList(toDTO(logEntryService.findByFilter(new Filter(), entityName, entityId)));
    }
}
