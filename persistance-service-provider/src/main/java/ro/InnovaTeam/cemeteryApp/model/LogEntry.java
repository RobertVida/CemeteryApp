package ro.InnovaTeam.cemeteryApp.model;
import java.sql.Date;

/**
 * Created by lucian.vaida on 2/11/2014.
 */
public class LogEntry {

    private long id;
    private String userId;
    private String tableChanged;
    private String idAffected;
    private Date tookPlaceOn;
    private String action;
    private String details;

    public LogEntry(long id, String userId, String tableChanged, String idAffected, Date tookPlaceOn, String action, String details) {
        this.id = id;
        this.userId = userId;
        this.tableChanged = tableChanged;
        this.idAffected = idAffected;
        this.tookPlaceOn = tookPlaceOn;
        this.action = action;
        this.details = details;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTableChanged() {
        return tableChanged;
    }

    public void setTableChanged(String tableChanged) {
        this.tableChanged = tableChanged;
    }

    public String getIdAffected() {
        return idAffected;
    }

    public void setIdAffected(String idAffected) {
        this.idAffected = idAffected;
    }

    public Date getTookPlaceOn() {
        return tookPlaceOn;
    }

    public void setTookPlaceOn(Date tookPlaceOn) {
        this.tookPlaceOn = tookPlaceOn;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
