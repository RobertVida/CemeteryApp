package ro.InnovaTeam.cemeteryApp.registers;

import java.util.List;

/**
 * Created by robert on 1/5/2015.
 */
public class BurialRegistry extends Registry<BurialRegistryEntryDTO> {

    public BurialRegistry() {
    }

    public BurialRegistry(List<BurialRegistryEntryDTO> content) {
        super(content);
    }

}
