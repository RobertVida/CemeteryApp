package ro.InnovaTeam.cemeteryApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.InnovaTeam.cemeteryApp.FilterDTO;
import ro.InnovaTeam.cemeteryApp.StructureHistoryEntryDTO;
import ro.InnovaTeam.cemeteryApp.StructureHistoryEntryList;
import ro.InnovaTeam.cemeteryApp.model.StructureHistoryEntry;
import ro.InnovaTeam.cemeteryApp.service.AuthenticationService;
import ro.InnovaTeam.cemeteryApp.service.StructureHistoryEntryService;

import javax.validation.Valid;

import static ro.InnovaTeam.cemeteryApp.util.FilterUtil.toDB;
import static ro.InnovaTeam.cemeteryApp.util.StructureHistoryEntryUtil.toDB;
import static ro.InnovaTeam.cemeteryApp.util.StructureHistoryEntryUtil.toDTO;

/**
 * Created by robert on 1/1/2015.
 */
@Controller
public class StructureHistoryController extends ExceptionHandledController {

    public static final String STRUCTURE_HISTORY_URL = "/structureHistory";
    public static final String STRUCTURE_HISTORIES_URL = "/structureHistories";
    public static final String SPECIFIC_STRUCTURE_HISTORY_URL = STRUCTURE_HISTORY_URL + "/{structureHistoryId}";

    @Autowired
    private StructureHistoryEntryService historyService;
    @Autowired
    private AuthenticationService authService;

    @RequestMapping(value = STRUCTURE_HISTORY_URL, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer create(@RequestHeader("Authorization-Token") String token, @RequestBody @Valid StructureHistoryEntryDTO historyEntryDTO) {
        historyEntryDTO.setUserId(getUserId(token));
        return historyService.create(toDB(historyEntryDTO));
    }

    @RequestMapping(value = SPECIFIC_STRUCTURE_HISTORY_URL, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public StructureHistoryEntryDTO delete(@RequestHeader("Authorization-Token") String token, @PathVariable Integer structureHistoryId) {
        return toDTO(historyService.delete(getUserId(token), structureHistoryId));
    }

    @RequestMapping(value = SPECIFIC_STRUCTURE_HISTORY_URL, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public StructureHistoryEntryDTO update(@RequestHeader("Authorization-Token") String token, @PathVariable Integer structureHistoryId, @RequestBody @Valid StructureHistoryEntryDTO historyEntryDTO) {
        StructureHistoryEntry historyEntry = toDB(historyEntryDTO);
        historyEntry.setId(structureHistoryId);
        historyEntry.setUserId(getUserId(token));
        return toDTO(historyService.update(historyEntry));
    }

    @RequestMapping(value = SPECIFIC_STRUCTURE_HISTORY_URL, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public StructureHistoryEntryDTO findById(@RequestHeader("Authorization-Token") String token, @PathVariable Integer structureHistoryId) {
        isLoggedIn(token);
        return toDTO(historyService.findById(structureHistoryId));
    }

    @RequestMapping(value = STRUCTURE_HISTORIES_URL, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public StructureHistoryEntryList findByFilter(@RequestHeader("Authorization-Token") String token, @RequestBody FilterDTO filterDTO) {
        isLoggedIn(token);
        return new StructureHistoryEntryList(toDTO(historyService.findByFilter(toDB(filterDTO))));
    }

    @RequestMapping(value = STRUCTURE_HISTORIES_URL + "/count", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer countByFilter(@RequestHeader("Authorization-Token") String token, @RequestBody FilterDTO filterDTO) {
        isLoggedIn(token);
        return historyService.countByFilter(toDB(filterDTO));
    }

    private Integer getUserId(String token) {
        return authService.getAdminAccess(token).getId();
    }

    private void isLoggedIn(String token) {
        authService.hasGuestAccess(token);
    }
}
