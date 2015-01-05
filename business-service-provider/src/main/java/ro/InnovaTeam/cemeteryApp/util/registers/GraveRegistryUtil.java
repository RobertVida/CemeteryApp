package ro.InnovaTeam.cemeteryApp.util.registers;

import ro.InnovaTeam.cemeteryApp.model.registers.GraveRegistryEntry;
import ro.InnovaTeam.cemeteryApp.registers.GraveRegistryEntryDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by robert on 1/5/2015.
 */
public class GraveRegistryUtil {

    public static GraveRegistryEntry toDB(GraveRegistryEntryDTO entryDTO) {
        if(entryDTO == null) {
            return null;
        }
        GraveRegistryEntry entry = new GraveRegistryEntry();
        entry.setCemeteryId(entryDTO.getCemeteryId());
        entry.setCemeteryName(entryDTO.getCemeteryName());
        entry.setParcelId(entryDTO.getParcelId());
        entry.setParcelName(entryDTO.getParcelName());
        entry.setGraveId(entryDTO.getGraveId());
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

    public static GraveRegistryEntryDTO toDTO(GraveRegistryEntry entry) {
        if(entry == null) {
            return null;
        }
        GraveRegistryEntryDTO entryDTO = new GraveRegistryEntryDTO();
        entryDTO.setCemeteryId(entry.getCemeteryId());
        entryDTO.setCemeteryName(entry.getCemeteryName());
        entryDTO.setParcelId(entry.getParcelId());
        entryDTO.setParcelName(entry.getParcelName());
        entryDTO.setGraveId(entry.getGraveId());
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

    public static List<GraveRegistryEntry> toDB(List<GraveRegistryEntryDTO> entryDTOs) {
        if(entryDTOs == null) {
            return null;
        }
        List<GraveRegistryEntry> entries = new ArrayList<GraveRegistryEntry>();
        for(GraveRegistryEntryDTO entryDTO : entryDTOs) {
            entries.add(toDB(entryDTO));
        }
        return entries;
    }

    public static List<GraveRegistryEntryDTO> toDTO(List<GraveRegistryEntry> entries) {
        if(entries == null) {
            return null;
        }
        List<GraveRegistryEntryDTO> entryDTOs = new ArrayList<GraveRegistryEntryDTO>();
        for(GraveRegistryEntry entry : entries) {
            entryDTOs.add(toDTO(entry));
        }
        return entryDTOs;
    }
}
