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
import ro.InnovaTeam.cemeteryApp.service.StructureHistoryEntryService;

import javax.validation.Valid;

import static ro.InnovaTeam.cemeteryApp.util.FilterUtil.toDB;
import static ro.InnovaTeam.cemeteryApp.util.StructureHistoryEntryUtil.toDB;
import static ro.InnovaTeam.cemeteryApp.util.StructureHistoryEntryUtil.toDTO;

/**
 * Created by robert on 1/1/2015.
 */
@Controller
public class StructureHistoryController {

    public static final String STRUCTURE_HISTORY_URL = "/structureHistory";
    public static final String STRUCTURE_HISTORIES_URL = "/structureHistories";
    public static final String SPECIFIC_STRUCTURE_HISTORY_URL = STRUCTURE_HISTORY_URL + "/{structureHistoryId}";
    public static final String SPECIFIC_USER_STRUCTURE_HISTORY_URL = STRUCTURE_HISTORY_URL + "/{userId}/{structureHistoryId}";

    @Autowired
    private StructureHistoryEntryService historyService;

    @RequestMapping(value = STRUCTURE_HISTORY_URL, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer create(@RequestBody @Valid StructureHistoryEntryDTO historyEntryDTO) {
        return historyService.create(toDB(historyEntryDTO));
    }

    @RequestMapping(value = SPECIFIC_USER_STRUCTURE_HISTORY_URL, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public StructureHistoryEntryDTO delete(@PathVariable Integer userId, @PathVariable Integer structureHistoryId) {
        return toDTO(historyService.delete(userId, structureHistoryId));
    }

    @RequestMapping(value = SPECIFIC_STRUCTURE_HISTORY_URL, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public StructureHistoryEntryDTO update(@PathVariable Integer structureHistoryId, @RequestBody @Valid StructureHistoryEntryDTO historyEntryDTO) {
        StructureHistoryEntry historyEntry = toDB(historyEntryDTO);
        historyEntry.setId(structureHistoryId);
        return toDTO(historyService.update(historyEntry));
    }

    @RequestMapping(value = SPECIFIC_STRUCTURE_HISTORY_URL, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public StructureHistoryEntryDTO findById(@PathVariable Integer structureHistoryId) {
        return toDTO(historyService.findById(structureHistoryId));
    }

    @RequestMapping(value = STRUCTURE_HISTORIES_URL, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public StructureHistoryEntryList findByFilter(@RequestBody FilterDTO filterDTO) {
        return new StructureHistoryEntryList(toDTO(historyService.findByFilter(toDB(filterDTO))));
    }

    @RequestMapping(value = STRUCTURE_HISTORIES_URL + "/count", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer countByFilter(@RequestBody FilterDTO filterDTO) {
        return historyService.countByFilter(toDB(filterDTO));
    }
}
