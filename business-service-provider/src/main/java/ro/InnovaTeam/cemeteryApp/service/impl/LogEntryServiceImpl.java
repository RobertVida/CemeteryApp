package ro.InnovaTeam.cemeteryApp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.InnovaTeam.cemeteryApp.eao.LogEntryEAO;
import ro.InnovaTeam.cemeteryApp.model.Cemetery;
import ro.InnovaTeam.cemeteryApp.model.Filter;
import ro.InnovaTeam.cemeteryApp.model.LogEntry;
import ro.InnovaTeam.cemeteryApp.model.Parcel;
import ro.InnovaTeam.cemeteryApp.service.LogEntryService;

import java.util.List;

/**
 * Created by robert on 11/18/2014.
 */
@Transactional
@Service
public class LogEntryServiceImpl implements LogEntryService {

    @Autowired
    private LogEntryEAO logEAO;

    @Override
    public Integer create(LogEntry cemetery) {
        return logEAO.create(cemetery);
    }

    @Override
    public LogEntry delete(Integer id) {
        return logEAO.delete(id);
    }

    @Override
    public LogEntry update(LogEntry log) {
        return logEAO.update(log);
    }

    @Override
    public LogEntry findById(Integer id) {
        return logEAO.findById(id);
    }

    @Override
    public List<LogEntry> findByFilter(Filter filter) {
        return logEAO.findByFilter(filter);
    }

    @Override
    public List<LogEntry> findByFilter(Filter filter, String entityName) {
        return logEAO.findByFilter(filter, entityName);
    }

    @Override
    public List<LogEntry> findByFilter(Filter filter, String entityName, Integer entityId) {
        return logEAO.findByFilter(filter, entityName, entityId);
    }
}
