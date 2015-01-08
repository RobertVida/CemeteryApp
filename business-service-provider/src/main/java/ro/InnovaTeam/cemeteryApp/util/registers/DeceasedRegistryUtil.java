package ro.InnovaTeam.cemeteryApp.util.registers;

import ro.InnovaTeam.cemeteryApp.model.registers.DeceasedRegistryEntry;
import ro.InnovaTeam.cemeteryApp.registers.DeceasedRegistryEntryDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by robert on 1/5/2015.
 */
public class DeceasedRegistryUtil {

    public static DeceasedRegistryEntry toDB(DeceasedRegistryEntryDTO entryDTO) {
        if(entryDTO == null) {
            return null;
        }
        DeceasedRegistryEntry entry = new DeceasedRegistryEntry();
        entry.setDeceasedId(entryDTO.getDeceasedId());
        entry.setFirstName(entryDTO.getFirstName());
        entry.setLastName(entryDTO.getLastName());
        entry.setCemeteryId(entryDTO.getCemeteryId());
        entry.setCemeteryName(entryDTO.getCemeteryName());
        entry.setParcelId(entryDTO.getParcelId());
        entry.setParcelName(entryDTO.getParcelName());
        entry.setGraveId(entryDTO.getGraveId());
        entry.setBuriedOn(entryDTO.getBuriedOn());

        return entry;
    }

    public static DeceasedRegistryEntryDTO toDTO(DeceasedRegistryEntry entry) {
        if(entry == null) {
            return null;
        }
        DeceasedRegistryEntryDTO entryDTO = new DeceasedRegistryEntryDTO();
        entryDTO.setDeceasedId(entry.getDeceasedId());
        entryDTO.setFirstName(entry.getFirstName());
        entryDTO.setLastName(entry.getLastName());
        entryDTO.setCemeteryId(entry.getCemeteryId());
        entryDTO.setCemeteryName(entry.getCemeteryName());
        entryDTO.setParcelId(entry.getParcelId());
        entryDTO.setParcelName(entry.getParcelName());
        entryDTO.setGraveId(entry.getGraveId());
        entryDTO.setBuriedOn(entry.getBuriedOn());

        return entryDTO;
    }

    public static List<DeceasedRegistryEntry> toDB(List<DeceasedRegistryEntryDTO> entryDTOs) {
        if(entryDTOs == null) {
            return null;
        }
        List<DeceasedRegistryEntry> entries = new ArrayList<DeceasedRegistryEntry>();
        for(DeceasedRegistryEntryDTO entryDTO : entryDTOs) {
            entries.add(toDB(entryDTO));
        }
        return entries;
    }

    public static List<DeceasedRegistryEntryDTO> toDTO(List<DeceasedRegistryEntry> entries) {
        if(entries == null) {
            return null;
        }
        List<DeceasedRegistryEntryDTO> entryDTOs = new ArrayList<DeceasedRegistryEntryDTO>();
        for(DeceasedRegistryEntry entry : entries) {
            entryDTOs.add(toDTO(entry));
        }
        return entryDTOs;
    }
}
