package ro.InnovaTeam.cemeteryApp;

import java.util.List;

/**
 * Created by robert on 12/14/2014.
 */
public class LogEntryList {

    List<LogEntryDTO> content;

    public LogEntryList() {
    }

    public LogEntryList(List<LogEntryDTO> content) {
        this.content = content;
    }

    public List<LogEntryDTO> getContent() {
        return content;
    }

    public void setContent(List<LogEntryDTO> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "LogEntryList{" +
                "content=" + content +
                '}';
    }
}
