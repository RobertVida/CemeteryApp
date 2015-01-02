package ro.InnovaTeam.cemeteryApp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.InnovaTeam.cemeteryApp.eao.StructureHistoryEntryEAO;
import ro.InnovaTeam.cemeteryApp.model.Filter;
import ro.InnovaTeam.cemeteryApp.model.StructureHistoryEntry;
import ro.InnovaTeam.cemeteryApp.service.LogEntryService;
import ro.InnovaTeam.cemeteryApp.service.StructureHistoryEntryService;

import java.util.List;

/**
 * Created by robert on 1/1/2015.
 */
@Transactional
@Service
public class StructureHistoryEntryServiceImpl extends LoggableService<StructureHistoryEntry, StructureHistoryEntryEAO, LogEntryService> implements StructureHistoryEntryService{

    @Autowired
    private StructureHistoryEntryEAO entryEAO;
    @Autowired
    private LogEntryService logService;

    @Override
    public Integer create(StructureHistoryEntry entry) {
        return loggedCreate(entryEAO, logService, entry);
    }

    @Override
    public StructureHistoryEntry delete(Integer userId, Integer id) {
        return loggedDelete(entryEAO, logService, userId, id);
    }

    @Override
    public StructureHistoryEntry update(StructureHistoryEntry entry) {
        return loggedUpdate(entryEAO, logService, entry);
    }

    @Override
    public StructureHistoryEntry findById(Integer id) {
        return entryEAO.findById(id);
    }

    @Override
    public List<StructureHistoryEntry> findByFilter(Filter filter) {
        return entryEAO.findByFilter(filter);
    }

    @Override
    public Integer countByFilter(Filter filter) {
        return entryEAO.countByFilter(filter);
    }
}
