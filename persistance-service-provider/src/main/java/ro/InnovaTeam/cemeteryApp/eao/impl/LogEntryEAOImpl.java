package ro.InnovaTeam.cemeteryApp.eao.impl;

import org.springframework.stereotype.Component;
import ro.InnovaTeam.cemeteryApp.eao.LogEntryEAO;
import ro.InnovaTeam.cemeteryApp.helpers.FilteredQueryBuilder;
import ro.InnovaTeam.cemeteryApp.model.Filter;
import ro.InnovaTeam.cemeteryApp.model.LogEntry;

import java.util.List;

/**
 * Created by robert on 12/14/2014.
 */
@Component
public class LogEntryEAOImpl extends EntityEAOImpl<LogEntry> implements LogEntryEAO {

    private static final String TABLE = "logs";

    @Override
    public Integer create(LogEntry logEntry) {
        Integer id = create(TABLE, logEntry);
        flush();
        return id;
    }

    @Override
    public LogEntry delete(Integer id) {
        return null;
    }

    @Override
    public LogEntry update(LogEntry logEntry) {
        return null;
    }

    @Override
    public LogEntry findById(Integer id) {
        return findById(TABLE, id);
    }

    @Override
    public List<LogEntry> findByFilter(Filter filter) {
        return findByFilter(TABLE, filter);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<LogEntry> findByFilter(String tableName, Filter filter) {
        return FilteredQueryBuilder.instance()
                .from(tableName)
                .setFilter(filter)
                .setCriteriaSearchableColumns("details")
                .build(getSession()).list();
    }

    @Override
    public List<LogEntry> findByFilter(Filter filter, String entityName) {
        return findByFilter(TABLE, filter, entityName);
    }

    @SuppressWarnings("unchecked")
    public List<LogEntry> findByFilter(String tableName, Filter filter, String entityName) {
        return FilteredQueryBuilder.instance()
                .from(tableName)
                .setFilter(filter)
                .setCriteriaSearchableColumns("details")
                .where("table_changed", entityName)
                .build(getSession()).list();
    }

    @Override
    public List<LogEntry> findByFilter(Filter filter, String entityName, Integer entityId) {
        return findByFilter(TABLE, filter, entityName, entityId);
    }

    @SuppressWarnings("unchecked")
    public List<LogEntry> findByFilter(String tableName, Filter filter, String entityName, Integer entityId) {
        return FilteredQueryBuilder.instance()
                .from(tableName)
                .setFilter(filter)
                .setCriteriaSearchableColumns("details")
                .where("table_changed", entityName)
                .where("id_affected", entityId.toString())
                .build(getSession()).list();
    }
}
