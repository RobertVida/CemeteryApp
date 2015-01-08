package ro.InnovaTeam.cemeteryApp.eao;

import org.springframework.stereotype.Component;
import ro.InnovaTeam.cemeteryApp.model.Filter;
import ro.InnovaTeam.cemeteryApp.model.registers.*;

import java.util.List;

/**
 * Created by robert on 1/5/2015.
 */
@Component
public interface SearchEAO {

    public List<BurialRegistryEntry> getBurialRegistry(Filter filter);

    public Integer getBurialRegistryCount(Filter filter);

    public List<GraveRegistryEntry> getGraveRegistry(Filter filter);

    public Integer getGraveRegistryCount(Filter filter);

    public List<MonumentRegistryEntry> getMonumentRegistry(Filter filter);

    public Integer getMonumentRegistryCount(Filter filter);

    public List<DeceasedRegistryEntry> getDeceasedRegistry(Filter filter, String nameOrder, String diedOnOrder);

    public Integer getDeceasedRegistryCount(Filter filter, String nameOrder, String diedOnOrder);

    public List<DeceasedNoCaregiverRegistryEntry> getDeceasedNoCaregiverRegistry(Filter filter, String nameOrder, String diedOnOrder);

    public Integer getDeceasedNoCaregiverRegistryCount(Filter filter, String nameOrder, String diedOnOrder);
}
