package ro.InnovaTeam.cemeteryApp.util.registers;

import ro.InnovaTeam.cemeteryApp.model.registers.DeceasedNoCaregiverRegistryEntry;
import ro.InnovaTeam.cemeteryApp.registers.DeceasedNoCaregiverRegistryEntryDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by robert on 1/5/2015.
 */
public class DeceasedNoCaregiverRegistryUtil {

    public static DeceasedNoCaregiverRegistryEntry toDB(DeceasedNoCaregiverRegistryEntryDTO entryDTO) {
        if(entryDTO == null) {
            return null;
        }
        DeceasedNoCaregiverRegistryEntry entry = new DeceasedNoCaregiverRegistryEntry();
        entry.setDeceasedId(entryDTO.getDeceasedId());
        entry.setFirstName(entryDTO.getFirstName());
        entry.setLastName(entryDTO.getLastName());
        entry.setCemeteryId(entryDTO.getCemeteryId());
        entry.setCemeteryName(entryDTO.getCemeteryName());
        entry.setParcelId(entryDTO.getParcelId());
        entry.setParcelName(entryDTO.getParcelName());
        entry.setGraveId(entryDTO.getGraveId());
        entry.setBuriedOn(entryDTO.getBuriedOn());
        entry.setCertificateId(entryDTO.getCertificateId());
        entry.setRequestIMLid(entryDTO.getRequestIMLid());

        return entry;
    }

    public static DeceasedNoCaregiverRegistryEntryDTO toDTO(DeceasedNoCaregiverRegistryEntry entry) {
        if(entry == null) {
            return null;
        }
        DeceasedNoCaregiverRegistryEntryDTO entryDTO = new DeceasedNoCaregiverRegistryEntryDTO();
        entryDTO.setDeceasedId(entry.getDeceasedId());
        entryDTO.setFirstName(entry.getFirstName());
        entryDTO.setLastName(entry.getLastName());
        entryDTO.setCemeteryId(entry.getCemeteryId());
        entryDTO.setCemeteryName(entry.getCemeteryName());
        entryDTO.setParcelId(entry.getParcelId());
        entryDTO.setParcelName(entry.getParcelName());
        entryDTO.setGraveId(entry.getGraveId());
        entryDTO.setBuriedOn(entry.getBuriedOn());
        entryDTO.setCertificateId(entry.getCertificateId());
        entryDTO.setRequestIMLid(entry.getRequestIMLid());

        return entryDTO;
    }

    public static List<DeceasedNoCaregiverRegistryEntry> toDB(List<DeceasedNoCaregiverRegistryEntryDTO> entryDTOs) {
        if(entryDTOs == null) {
            return null;
        }
        List<DeceasedNoCaregiverRegistryEntry> entries = new ArrayList<DeceasedNoCaregiverRegistryEntry>();
        for(DeceasedNoCaregiverRegistryEntryDTO entryDTO : entryDTOs) {
            entries.add(toDB(entryDTO));
        }
        return entries;
    }

    public static List<DeceasedNoCaregiverRegistryEntryDTO> toDTO(List<DeceasedNoCaregiverRegistryEntry> entries) {
        if(entries == null) {
            return null;
        }
        List<DeceasedNoCaregiverRegistryEntryDTO> entryDTOs = new ArrayList<DeceasedNoCaregiverRegistryEntryDTO>();
        for(DeceasedNoCaregiverRegistryEntry entry : entries) {
            entryDTOs.add(toDTO(entry));
        }
        return entryDTOs;
    }
}
