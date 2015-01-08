package ro.InnovaTeam.cemeteryApp.util.registers;

import ro.InnovaTeam.cemeteryApp.model.registers.RequestRegistryEntry;
import ro.InnovaTeam.cemeteryApp.registers.RequestRegistryEntryDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by robert on 1/5/2015.
 */
public class RequestRegistryUtil {

    public static RequestRegistryEntry toDB(RequestRegistryEntryDTO entryDTO) {
        if(entryDTO == null) {
            return null;
        }
        RequestRegistryEntry entry = new RequestRegistryEntry();
        entry.setRequestId(entryDTO.getRequestId());
        entry.setCreatedOn(entryDTO.getCreatedOn());
        entry.setInfocetNumber(entryDTO.getInfocetNumber());
        entry.setStatus(entryDTO.getStatus());
        entry.setClientId(entryDTO.getClientId());
        entry.setClientLastName(entryDTO.getClientLastName());
        entry.setClientFirstName(entryDTO.getClientFirstName());

        return entry;
    }

    public static RequestRegistryEntryDTO toDTO(RequestRegistryEntry entry) {
        if(entry == null) {
            return null;
        }
        RequestRegistryEntryDTO entryDTO = new RequestRegistryEntryDTO();
        entryDTO.setRequestId(entry.getRequestId());
        entryDTO.setCreatedOn(entry.getCreatedOn());
        entryDTO.setInfocetNumber(entry.getInfocetNumber());
        entryDTO.setStatus(entry.getStatus());
        entryDTO.setClientId(entry.getClientId());
        entryDTO.setClientLastName(entry.getClientLastName());
        entryDTO.setClientFirstName(entry.getClientFirstName());

        return entryDTO;
    }

    public static List<RequestRegistryEntry> toDB(List<RequestRegistryEntryDTO> entryDTOs) {
        if(entryDTOs == null) {
            return null;
        }
        List<RequestRegistryEntry> entries = new ArrayList<RequestRegistryEntry>();
        for(RequestRegistryEntryDTO entryDTO : entryDTOs) {
            entries.add(toDB(entryDTO));
        }
        return entries;
    }

    public static List<RequestRegistryEntryDTO> toDTO(List<RequestRegistryEntry> entries) {
        if(entries == null) {
            return null;
        }
        List<RequestRegistryEntryDTO> entryDTOs = new ArrayList<RequestRegistryEntryDTO>();
        for(RequestRegistryEntry entry : entries) {
            entryDTOs.add(toDTO(entry));
        }
        return entryDTOs;
    }
}
