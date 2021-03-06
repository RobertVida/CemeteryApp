package ro.InnovaTeam.cemeteryApp.util;

import ro.InnovaTeam.cemeteryApp.LogEntryDTO;
import ro.InnovaTeam.cemeteryApp.model.LogEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by robert on 12/14/2014.
 */
public class LogEntryUtil {

    private static Map<String, String> tableMapping = new HashMap<String, String>(){{
        put("cemeteries", "Cimitir");
        put("parcel", "Parcela");
        put("structures", "Structura");
        put("graves", "Mormant");
        put("monument", "Monument");
        put("clients", "Client");
        put("deceased", "Decedat");
        put("burialdocuments", "Document de inmormantare");
        put("nocaregiverdocuments", "Document fara apartinator");
        put("restingplacerequests", "Cerere");
        put("contracts", "Contract");
        put("structurehistory", "Istoric structuri");
    }};

    public static LogEntry toDB(LogEntryDTO entryDTO) {
        if (entryDTO == null) {
            return null;
        }
        LogEntry entry = new LogEntry();
        entry.setId(entryDTO.getId());
        entry.setUserId(entryDTO.getUserId());
        entry.setTableChanged(entryDTO.getTableChanged());
        entry.setIdAffected(entryDTO.getIdAffected());
        entry.setTookPlaceOn(entryDTO.getTookPlaceOn());
        entry.setAction(entryDTO.getAction());
        entry.setOldValue(entryDTO.getOldValue());
        entry.setNewValue(entryDTO.getNewValue());

        return entry;
    }

    public static LogEntryDTO toDTO(LogEntry entry) {
        if (entry == null) {
            return null;
        }
        LogEntryDTO entryDTO = new LogEntryDTO();
        entryDTO.setId(entry.getId());
        entryDTO.setUserId(entry.getUserId());
        entryDTO.setTableChanged(tableMapping.get(entry.getTableChanged()));
        entryDTO.setIdAffected(entry.getIdAffected());
        entryDTO.setTookPlaceOn(entry.getTookPlaceOn());
        entryDTO.setAction(entry.getAction());
        entryDTO.setOldValue(entry.getOldValue());
        entryDTO.setNewValue(entry.getNewValue());

        return entryDTO;
    }

    public static List<LogEntry> toDB(List<LogEntryDTO> entryDTOs) {
        if (entryDTOs == null) {
            return null;
        }
        List<LogEntry> entries = new ArrayList<LogEntry>();
        for (LogEntryDTO entryDTO : entryDTOs) {
            entries.add(toDB(entryDTO));
        }
        return entries;
    }

    public static List<LogEntryDTO> toDTO(List<LogEntry> entries) {
        if (entries == null) {
            return null;
        }
        List<LogEntryDTO> entryDTOs = new ArrayList<LogEntryDTO>();
        for (LogEntry entry : entries) {
            entryDTOs.add(toDTO(entry));
        }
        return entryDTOs;
    }
}
