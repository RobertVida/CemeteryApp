package ro.InnovaTeam.cemeteryApp.registers;

import java.util.List;

/**
 * Created by robert on 1/5/2015.
 */
public class DeceasedRegistry extends Registry<DeceasedRegistryEntryDTO> {

    public DeceasedRegistry() {
    }

    public DeceasedRegistry(List<DeceasedRegistryEntryDTO> content) {
        super(content);
    }
}
