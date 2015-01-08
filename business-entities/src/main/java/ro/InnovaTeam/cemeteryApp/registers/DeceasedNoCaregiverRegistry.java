package ro.InnovaTeam.cemeteryApp.registers;

import java.util.List;

/**
 * Created by robert on 1/5/2015.
 */
public class DeceasedNoCaregiverRegistry extends Registry<DeceasedNoCaregiverRegistryEntryDTO> {

    public DeceasedNoCaregiverRegistry() {
    }

    public DeceasedNoCaregiverRegistry(List<DeceasedNoCaregiverRegistryEntryDTO> content) {
        super(content);
    }
}
