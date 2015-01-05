package ro.InnovaTeam.cemeteryApp.registers;

import java.util.List;

/**
 * Created by robert on 1/5/2015.
 */
public class GraveRegistry extends Registry<GraveRegistryEntryDTO> {

    public GraveRegistry() {
    }

    public GraveRegistry(List<GraveRegistryEntryDTO> content) {
        super(content);
    }
}
