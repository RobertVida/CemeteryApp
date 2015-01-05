package ro.InnovaTeam.cemeteryApp.registers;

import java.util.List;

/**
 * Created by robert on 1/5/2015.
 */
public class MonumentRegistry extends Registry<MonumentRegistryEntryDTO> {

    public MonumentRegistry() {
    }

    public MonumentRegistry(List<MonumentRegistryEntryDTO> content) {
        super(content);
    }
}
