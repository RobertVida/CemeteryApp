package ro.InnovaTeam.cemeteryApp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.InnovaTeam.cemeteryApp.eao.SearchEAO;
import ro.InnovaTeam.cemeteryApp.model.Filter;
import ro.InnovaTeam.cemeteryApp.model.registers.*;
import ro.InnovaTeam.cemeteryApp.service.SearchService;

import java.util.List;

/**
 * Created by robert on 1/5/2015.
 */
@Transactional
@Service
public class SearchServiceImpl implements SearchService{

    @Autowired
    private SearchEAO searchEAO;

    @Override
    public List<BurialRegistryEntry> getBurialRegistry(Filter filter) {
        return searchEAO.getBurialRegistry(filter);
    }

    @Override
    public Integer getBurialRegistryCount(Filter filter) {
        return searchEAO.getBurialRegistryCount(filter);
    }

    @Override
    public List<GraveRegistryEntry> getGraveRegistry(Filter filter) {
        return searchEAO.getGraveRegistry(filter);
    }

    @Override
    public Integer getGraveRegistryCount(Filter filter) {
        return searchEAO.getGraveRegistryCount(filter);
    }

    @Override
    public List<MonumentRegistryEntry> getMonumentRegistry(Filter filter) {
        return searchEAO.getMonumentRegistry(filter);
    }

    @Override
    public Integer getMonumentRegistryCount(Filter filter) {
        return searchEAO.getMonumentRegistryCount(filter);
    }

    @Override
    public List<DeceasedRegistryEntry> getDeceasedRegistry(Filter filter, String nameOrder, String diedOnOrder) {
        return searchEAO.getDeceasedRegistry(filter, nameOrder, diedOnOrder);
    }

    @Override
    public Integer getDeceasedRegistryCount(Filter filter, String nameOrder, String diedOnOrder) {
        return searchEAO.getDeceasedRegistryCount(filter, nameOrder, diedOnOrder);
    }

    @Override
    public List<DeceasedNoCaregiverRegistryEntry> getDeceasedNoCaregiverRegistry(Filter filter, String nameOrder, String diedOnOrder) {
        return searchEAO.getDeceasedNoCaregiverRegistry(filter, nameOrder, diedOnOrder);
    }

    @Override
    public Integer getDeceasedNoCaregiverRegistryCount(Filter filter, String nameOrder, String diedOnOrder) {
        return searchEAO.getDeceasedNoCaregiverRegistryCount(filter, nameOrder, diedOnOrder);
    }

    @Override
    public List<RequestRegistryEntry> getRequestRegistry(Filter filter) {
        return searchEAO.getRequestRegistry(filter);
    }

    @Override
    public Integer getRequestRegistryCount(Filter filter) {
        return searchEAO.getRequestRegistryCount(filter);
    }

    @Override
    public List<ContractRegistryEntry> getContractRegistry(Filter filter) {
        return searchEAO.getContractRegistry(filter);
    }

    @Override
    public Integer getContractRegistryCount(Filter filter) {
        return searchEAO.getContractRegistryCount(filter);
    }
}
