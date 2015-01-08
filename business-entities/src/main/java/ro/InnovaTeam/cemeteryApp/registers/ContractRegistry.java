package ro.InnovaTeam.cemeteryApp.registers;

import java.util.List;

/**
 * Created by robert on 1/5/2015.
 */
public class ContractRegistry extends Registry<ContractRegistryEntryDTO> {

    public ContractRegistry() {
    }

    public ContractRegistry(List<ContractRegistryEntryDTO> content) {
        super(content);
    }

}
