package ro.InnovaTeam.cemeteryApp.util;

import ro.InnovaTeam.cemeteryApp.StructureHistoryEntryDTO;
import ro.InnovaTeam.cemeteryApp.model.StructureHistoryEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by robert on 1/1/2015.
 */
public abstract class StructureHistoryEntryUtil {

    public static StructureHistoryEntry toDB(StructureHistoryEntryDTO entryDTO){
        if(entryDTO == null){
            return null;
        }
        StructureHistoryEntry entry = new StructureHistoryEntry();
        entry.setId(entryDTO.getId());
        entry.setStructureId(entryDTO.getStructureId());
        entry.setDescription(entryDTO.getDescription());
        entry.setDate(entryDTO.getDate());
        entry.setUserId(entryDTO.getUserId());

        return entry;
    }

    public static StructureHistoryEntryDTO toDTO(StructureHistoryEntry entry){
        if(entry == null){
            return null;
        }
        StructureHistoryEntryDTO entryDTO = new StructureHistoryEntryDTO();
        entryDTO.setId(entry.getId());
        entryDTO.setStructureId(entry.getStructureId());
        entryDTO.setDescription(entry.getDescription());
        entryDTO.setDate(entry.getDate());
        entryDTO.setUserId(entry.getUserId());

        return entryDTO;
    }

    public static List<StructureHistoryEntry> toDB(List<StructureHistoryEntryDTO> entryDTOs) {
        if (entryDTOs == null) {
            return null;
        }
        List<StructureHistoryEntry> entries = new ArrayList<StructureHistoryEntry>();
        for (StructureHistoryEntryDTO entryDTO : entryDTOs) {
            entries.add(toDB(entryDTO));
        }
        return entries;
    }

    public static List<StructureHistoryEntryDTO> toDTO(List<StructureHistoryEntry> entries) {
        if (entries == null) {
            return null;
        }
        List<StructureHistoryEntryDTO> entryDTOs = new ArrayList<StructureHistoryEntryDTO>();
        for (StructureHistoryEntry entry : entries) {
            entryDTOs.add(toDTO(entry));
        }
        return entryDTOs;
    }
}
