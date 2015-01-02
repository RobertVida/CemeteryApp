package ro.InnovaTeam.cemeteryApp.util;

import ro.InnovaTeam.cemeteryApp.ContractDTO;
import ro.InnovaTeam.cemeteryApp.model.Contract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by robert on 1/2/2015.
 */
public class ContractUtil {

    public static Contract toDB(ContractDTO contractDTO) {
        if(contractDTO == null) {
            return null;
        }
        Contract contract = new Contract();
        contract.setId(contractDTO.getId());
        contract.setStructureId(contractDTO.getStructureId());
        contract.setRequestId(contractDTO.getRequestId());
        contract.setSignedOn(contractDTO.getSignedOn());
        contract.setUpdatedOn(contractDTO.getUpdatedOn());
        contract.setExpiresOn(contractDTO.getExpiresOn());
        contract.setUserId(contractDTO.getUserId());

        return contract;
    }

    public static ContractDTO toDTO(Contract contract) {
        if(contract == null) {
            return null;
        }
        ContractDTO contractDTO = new ContractDTO();
        contractDTO.setId(contract.getId());
        contractDTO.setStructureId(contract.getStructureId());
        contractDTO.setRequestId(contract.getRequestId());
        contractDTO.setSignedOn(contract.getSignedOn());
        contractDTO.setUpdatedOn(contract.getUpdatedOn());
        contractDTO.setExpiresOn(contract.getExpiresOn());
        contractDTO.setUserId(contract.getUserId());

        return contractDTO;
    }

    public static List<Contract> toDB(List<ContractDTO> contractDTOs){
        if(contractDTOs == null){
            return null;
        }
        List<Contract> contracts = new ArrayList<Contract>();
        for(ContractDTO contractDTO : contractDTOs) {
            contracts.add(toDB(contractDTO));
        }
        return contracts;
    }

    public static List<ContractDTO> toDTO(List<Contract> contracts){
        if(contracts == null){
            return null;
        }
        List<ContractDTO> contractDTOs = new ArrayList<ContractDTO>();
        for(Contract contract : contracts) {
            contractDTOs.add(toDTO(contract));
        }
        return contractDTOs;
    }
}
