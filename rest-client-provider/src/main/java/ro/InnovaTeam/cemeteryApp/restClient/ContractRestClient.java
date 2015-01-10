package ro.InnovaTeam.cemeteryApp.restClient;

import org.springframework.web.bind.annotation.PathVariable;
import ro.InnovaTeam.cemeteryApp.*;

import java.util.List;

/**
 * Created by Cata on 1/5/2015.
 */
public class ContractRestClient extends GenericRestClient {

    public static final String CONTRACT_URL = "/contract";
    public static final String CONTRACTS_URL = "/contracts";
    public static final String SPECIFIC_CONTRACT_URL = CONTRACT_URL + "/{contractId}";

    public static List<ContractDTO> findByFilter(FilterDTO contractFilterDTO) {
        return getByFilter(contractFilterDTO, BASE_URL + CONTRACTS_URL, ContractList.class);
    }

    public static ContractDTO findById(@PathVariable Integer contractId) {
        return findById(contractId, BASE_URL + SPECIFIC_CONTRACT_URL, ContractDTO.class);
    }

    public static ContractDTO update(@PathVariable Integer deceasedId, ContractDTO contractDTO) {
        return update(deceasedId, BASE_URL + SPECIFIC_CONTRACT_URL, contractDTO, ContractDTO.class);
    }

    public static void add(ContractDTO contractDTO) {
        add(contractDTO, BASE_URL + CONTRACT_URL);
    }

    public static void delete(@PathVariable Integer contractId) {
        delete(contractId, BASE_URL + SPECIFIC_CONTRACT_URL);
    }

    public static Integer getContractCount(FilterDTO filterDTO) {
        return getCount(filterDTO, BASE_URL + CONTRACTS_URL + "/count");
    }
}
