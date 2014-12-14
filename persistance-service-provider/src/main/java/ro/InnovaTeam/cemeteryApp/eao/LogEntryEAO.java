package ro.InnovaTeam.cemeteryApp.eao;

import ro.InnovaTeam.cemeteryApp.model.Filter;
import ro.InnovaTeam.cemeteryApp.model.LogEntry;

import java.util.List;

/**
 * Created by robert on 12/14/2014.
 */
public interface LogEntryEAO extends EntityEAO<LogEntry> {

    public List<LogEntry> findByFilter(Filter filter, String entityName);

    public List<LogEntry> findByFilter(Filter filter, String entityName, Integer entityId);
}
