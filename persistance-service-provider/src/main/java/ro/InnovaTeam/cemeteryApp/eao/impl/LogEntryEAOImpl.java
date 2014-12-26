package ro.InnovaTeam.cemeteryApp.eao.impl;

import org.springframework.stereotype.Component;
import ro.InnovaTeam.cemeteryApp.eao.LogEntryEAO;
import ro.InnovaTeam.cemeteryApp.helpers.QueryBuilder;
import ro.InnovaTeam.cemeteryApp.model.Filter;
import ro.InnovaTeam.cemeteryApp.model.LogEntry;

import java.util.List;

import static ro.InnovaTeam.cemeteryApp.helpers.AliasBuilder.from;
import static ro.InnovaTeam.cemeteryApp.helpers.ColumnConstraintBuilder.column;
import static ro.InnovaTeam.cemeteryApp.helpers.ConstraintWrapper.AndConstraintWrapper.and;


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
        LogEntry logEntry = delete(TABLE, id);
        flush();
        return logEntry;
    }

    @Override
    public LogEntry update(LogEntry logEntry) {
        update(TABLE, logEntry);
        flush();
        return findById(logEntry.getId());
    }

    @Override
    public LogEntry findById(Integer id) {
        return findById(TABLE, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<LogEntry> findByFilter(Filter filter) {
        return QueryBuilder.instance(getSession())
                .select(
                        from(TABLE).as("l"))
                .setMaxResults(filter.getPageSize())
                .setFirstResult(filter.getPageNo())
                .build().list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<LogEntry> findByFilter(Filter filter, String entityName) {
        return QueryBuilder.instance(getSession())
                .select(
                        from(TABLE).as("l")
                ).where(
                        column("l.tableChanged").like(entityName)
                )
                .setMaxResults(filter.getPageSize())
                .setFirstResult(filter.getPageNo())
                .build().list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<LogEntry> findByFilter(Filter filter, String entityName, Integer entityId) {
        return QueryBuilder.instance(getSession())
                .select(
                        from(TABLE).as("l")
                ).where(
                        and(
                                column("l.tableChanged").like(entityName),
                                column("l.idAffected").is(entityId)
                        )
                )
                .setMaxResults(filter.getPageSize())
                .setFirstResult(filter.getPageNo())
                .build().list();
    }
}
