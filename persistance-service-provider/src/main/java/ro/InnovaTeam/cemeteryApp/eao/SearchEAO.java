package ro.InnovaTeam.cemeteryApp.eao;

import org.springframework.stereotype.Component;
import ro.InnovaTeam.cemeteryApp.model.Filter;
import ro.InnovaTeam.cemeteryApp.model.registers.BurialRegistryEntry;

import java.util.List;

/**
 * Created by robert on 1/5/2015.
 */
@Component
public interface SearchEAO {

    public List<BurialRegistryEntry> getBurialRegistry(Filter filter);

    public Integer getBurialRegistryCount(Filter filter);
}
