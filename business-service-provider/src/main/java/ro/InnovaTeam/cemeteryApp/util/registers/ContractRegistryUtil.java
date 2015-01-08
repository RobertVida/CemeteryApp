package ro.InnovaTeam.cemeteryApp.util.registers;

import ro.InnovaTeam.cemeteryApp.model.registers.ContractRegistryEntry;
import ro.InnovaTeam.cemeteryApp.registers.ContractRegistryEntryDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by robert on 1/8/2015.
 */
public class ContractRegistryUtil {

    public static ContractRegistryEntry toDB(ContractRegistryEntryDTO entryDTO) {
        if(entryDTO == null) {
            return null;
        }
        ContractRegistryEntry entry = new ContractRegistryEntry();
        entry.setContractId(entryDTO.getContractId());
        entry.setSignedOn(entryDTO.getSignedOn());
        entry.setClientId(entryDTO.getClientId());
        entry.setClientLastName(entryDTO.getClientLastName());
        entry.setClientFirstName(entryDTO.getClientFirstName());
        entry.setClientAddress(entryDTO.getClientAddress());

        return entry;
    }

    public static ContractRegistryEntryDTO toDTO(ContractRegistryEntry entry) {
        if(entry == null) {
            return null;
        }
        ContractRegistryEntryDTO entryDTO = new ContractRegistryEntryDTO();
        entryDTO.setContractId(entry.getContractId());
        entryDTO.setSignedOn(entry.getSignedOn());
        entryDTO.setClientId(entry.getClientId());
        entryDTO.setClientLastName(entry.getClientLastName());
        entryDTO.setClientFirstName(entry.getClientFirstName());
        entryDTO.setClientAddress(entry.getClientAddress());

        return entryDTO;
    }

    public static List<ContractRegistryEntry> toDB(List<ContractRegistryEntryDTO> entryDTOs) {
        if(entryDTOs == null) {
            return null;
        }
        List<ContractRegistryEntry> entries = new ArrayList<ContractRegistryEntry>();
        for(ContractRegistryEntryDTO entryDTO : entryDTOs) {
            entries.add(toDB(entryDTO));
        }
        return entries;
    }

    public static List<ContractRegistryEntryDTO> toDTO(List<ContractRegistryEntry> entries) {
        if(entries == null) {
            return null;
        }
        List<ContractRegistryEntryDTO> entryDTOs = new ArrayList<ContractRegistryEntryDTO>();
        for(ContractRegistryEntry entry : entries) {
            entryDTOs.add(toDTO(entry));
        }
        return entryDTOs;
    }
}
