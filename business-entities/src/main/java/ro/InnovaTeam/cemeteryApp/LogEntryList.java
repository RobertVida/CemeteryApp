package ro.InnovaTeam.cemeteryApp;

import java.io.Serializable;
import java.util.List;

/**
 * Created by robert on 12/14/2014.
 */
public class LogEntryList extends BaseList<LogEntryDTO> implements Serializable {

    public LogEntryList() {
    }

    public LogEntryList(List<LogEntryDTO> content) {
        super(content);
    }

    @Override
    public String toString() {
        return "LogEntryList{" +
                "content=" + content +
                '}';
    }
}
