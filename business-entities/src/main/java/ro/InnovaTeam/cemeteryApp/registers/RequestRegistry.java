package ro.InnovaTeam.cemeteryApp.registers;

import java.util.List;

/**
 * Created by robert on 1/5/2015.
 */
public class RequestRegistry extends Registry<RequestRegistryEntryDTO> {

    public RequestRegistry() {
    }

    public RequestRegistry(List<RequestRegistryEntryDTO> content) {
        super(content);
    }
}
