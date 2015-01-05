package ro.InnovaTeam.cemeteryApp.util.registers;

import ro.InnovaTeam.cemeteryApp.model.registers.GraveRegistryEntry;
import ro.InnovaTeam.cemeteryApp.model.registers.MonumentRegistryEntry;
import ro.InnovaTeam.cemeteryApp.registers.GraveRegistryEntryDTO;
import ro.InnovaTeam.cemeteryApp.registers.MonumentRegistryEntryDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by robert on 1/5/2015.
 */
public class MonumentRegistryUtil {

    public static MonumentRegistryEntry toDB(MonumentRegistryEntryDTO entryDTO) {
        if(entryDTO == null) {
            return null;
        }
        MonumentRegistryEntry entry = new MonumentRegistryEntry();
        entry.setCemeteryId(entryDTO.getCemeteryId());
        entry.setCemeteryName(entryDTO.getCemeteryName());
        entry.setParcelId(entryDTO.getParcelId());
        entry.setParcelName(entryDTO.getParcelName());
        entry.setMonumentId(entryDTO.getMonumentId());
        entry.setOwnerFirstName(entryDTO.getOwnerFirstName());
        entry.setOwnerLastName(entryDTO.getOwnerLastName());
        entry.setOwnerAddress(entryDTO.getOwnerAddress());
        entry.setReceiptNumber(entryDTO.getReceiptNumber());
        entry.setDeceasedFirstName(entryDTO.getDeceasedFirstName());
        entry.setDeceasedLastName(entryDTO.getDeceasedLastName());
        entry.setBurialDate(entryDTO.getBurialDate());
        entry.setSurface(entryDTO.getSurface());

        return entry;
    }

    public static MonumentRegistryEntryDTO toDTO(MonumentRegistryEntry entry) {
        if(entry == null) {
            return null;
        }
        MonumentRegistryEntryDTO entryDTO = new MonumentRegistryEntryDTO();
        entryDTO.setCemeteryId(entry.getCemeteryId());
        entryDTO.setCemeteryName(entry.getCemeteryName());
        entryDTO.setParcelId(entry.getParcelId());
        entryDTO.setParcelName(entry.getParcelName());
        entryDTO.setMonumentId(entry.getMonumentId());
        entryDTO.setOwnerFirstName(entry.getOwnerFirstName());
        entryDTO.setOwnerLastName(entry.getOwnerLastName());
        entryDTO.setOwnerAddress(entry.getOwnerAddress());
        entryDTO.setReceiptNumber(entry.getReceiptNumber());
        entryDTO.setDeceasedFirstName(entry.getDeceasedFirstName());
        entryDTO.setDeceasedLastName(entry.getDeceasedLastName());
        entryDTO.setBurialDate(entry.getBurialDate());
        entryDTO.setSurface(entry.getSurface());

        return entryDTO;
    }

    public static List<MonumentRegistryEntry> toDB(List<MonumentRegistryEntryDTO> entryDTOs) {
        if(entryDTOs == null) {
            return null;
        }
        List<MonumentRegistryEntry> entries = new ArrayList<MonumentRegistryEntry>();
        for(MonumentRegistryEntryDTO entryDTO : entryDTOs) {
            entries.add(toDB(entryDTO));
        }
        return entries;
    }

    public static List<MonumentRegistryEntryDTO> toDTO(List<MonumentRegistryEntry> entries) {
        if(entries == null) {
            return null;
        }
        List<MonumentRegistryEntryDTO> entryDTOs = new ArrayList<MonumentRegistryEntryDTO>();
        for(MonumentRegistryEntry entry : entries) {
            entryDTOs.add(toDTO(entry));
        }
        return entryDTOs;
    }
}
