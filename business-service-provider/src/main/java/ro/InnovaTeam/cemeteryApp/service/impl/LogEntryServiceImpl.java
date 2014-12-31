package ro.InnovaTeam.cemeteryApp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.InnovaTeam.cemeteryApp.eao.LogEntryEAO;
import ro.InnovaTeam.cemeteryApp.model.*;
import ro.InnovaTeam.cemeteryApp.service.LogEntryService;

import java.util.Date;
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
    public LogEntry delete(Integer userId, Integer id) {
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

    @Override
    public Integer countByFilter(Filter filter) {
        return logEAO.countByFilter(filter);
    }

    @Override
    public Integer countByFilter(Filter filter, String entityName) {
        return logEAO.countByFilter(filter, entityName);
    }

    @Override
    public Integer countByFilter(Filter filter, String entityName, Integer entityId) {
        return logEAO.countByFilter(filter, entityName, entityId);
    }

    @Override
    public void logCreate(BaseEntity entity){
        LogEntry entry = new LogEntry(entity.getTableName(), entity.getId(), new Date(), "CREATE");
        entry.setOldValue("NONE");
        entry.setNewValue(entity.toString());
        entry.setUserId(entity.getUserId());
        logEAO.create(entry);
    }

    @Override
    public void logDelete(Integer userId, BaseEntity entity){
        LogEntry entry = new LogEntry(entity.getTableName(), entity.getId(), new Date(), "DELETE");
        entry.setOldValue(entity.toString());
        entry.setNewValue("NONE");
        entry.setUserId(userId);
        logEAO.create(entry);
    }

    @Override
    public void logUpdate(BaseEntity oldEntity, BaseEntity newEntity){
        LogEntry entry = new LogEntry(newEntity.getTableName(), newEntity.getId(), new Date(), "UPDATE");
        entry.setOldValue(oldEntity.toString());
        entry.setNewValue(newEntity.toString());
        entry.setUserId(newEntity.getUserId());
        logEAO.create(entry);
    }
}
