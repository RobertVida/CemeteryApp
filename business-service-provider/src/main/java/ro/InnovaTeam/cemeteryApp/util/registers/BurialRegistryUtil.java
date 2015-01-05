package ro.InnovaTeam.cemeteryApp.util.registers;

import ro.InnovaTeam.cemeteryApp.model.registers.BurialRegistryEntry;
import ro.InnovaTeam.cemeteryApp.registers.BurialRegistryEntryDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by robert on 1/5/2015.
 */
public class BurialRegistryUtil {

    public static BurialRegistryEntry toDB(BurialRegistryEntryDTO entryDTO) {
        if(entryDTO == null) {
            return null;
        }
        BurialRegistryEntry entry = new BurialRegistryEntry();
        entry.setDeceasedId(entryDTO.getDeceasedId());
        entry.setFirstName(entryDTO.getFirstName());
        entry.setLastName(entryDTO.getLastName());
        entry.setReligion(entryDTO.getReligion());
        entry.setBuriedOn(entryDTO.getBuriedOn());
        entry.setParcelId(entryDTO.getParcelId());
        entry.setParcelName(entryDTO.getParcelName());

        return entry;
    }

    public static BurialRegistryEntryDTO toDTO(BurialRegistryEntry entry) {
        if(entry == null) {
            return null;
        }
        BurialRegistryEntryDTO entryDTO = new BurialRegistryEntryDTO();
        entryDTO.setDeceasedId(entry.getDeceasedId());
        entryDTO.setFirstName(entry.getFirstName());
        entryDTO.setLastName(entry.getLastName());
        entryDTO.setReligion(entry.getReligion());
        entryDTO.setBuriedOn(entry.getBuriedOn());
        entryDTO.setParcelId(entry.getParcelId());
        entryDTO.setParcelName(entry.getParcelName());

        return entryDTO;
    }

    public static List<BurialRegistryEntry> toDB(List<BurialRegistryEntryDTO> entryDTOs) {
        if(entryDTOs == null) {
            return null;
        }
        List<BurialRegistryEntry> entries = new ArrayList<BurialRegistryEntry>();
        for(BurialRegistryEntryDTO entryDTO : entryDTOs) {
            entries.add(toDB(entryDTO));
        }
        return entries;
    }

    public static List<BurialRegistryEntryDTO> toDTO(List<BurialRegistryEntry> entries) {
        if(entries == null) {
            return null;
        }
        List<BurialRegistryEntryDTO> entryDTOs = new ArrayList<BurialRegistryEntryDTO>();
        for(BurialRegistryEntry entry : entries) {
            entryDTOs.add(toDTO(entry));
        }
        return entryDTOs;
    }
}
