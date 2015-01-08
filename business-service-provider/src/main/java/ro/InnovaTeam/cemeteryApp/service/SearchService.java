package ro.InnovaTeam.cemeteryApp.service;

import ro.InnovaTeam.cemeteryApp.model.Filter;
import ro.InnovaTeam.cemeteryApp.model.registers.BurialRegistryEntry;
import ro.InnovaTeam.cemeteryApp.model.registers.DeceasedRegistryEntry;
import ro.InnovaTeam.cemeteryApp.model.registers.GraveRegistryEntry;
import ro.InnovaTeam.cemeteryApp.model.registers.MonumentRegistryEntry;

import java.util.List;

/**
 * Created by robert on 1/5/2015.
 */
public interface SearchService extends Service{

    public List<BurialRegistryEntry> getBurialRegistry(Filter filter);

    public Integer getBurialRegistryCount(Filter filter);

    public List<GraveRegistryEntry> getGraveRegistry(Filter filter);

    public Integer getGraveRegistryCount(Filter filter);

    public List<MonumentRegistryEntry> getMonumentRegistry(Filter filter);

    public Integer getMonumentRegistryCount(Filter filter);

    public List<DeceasedRegistryEntry> getDeceasedRegistry(Filter filter, String nameOrder, String diedOnOrder);

    public Integer getDeceasedRegistryCount(Filter filter, String nameOrder, String diedOnOrder);
}
