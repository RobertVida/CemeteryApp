package ro.InnovaTeam.cemeteryApp.model;
import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * Created by lucian.vaida on 2/11/2014.
 */
public class LogEntry extends BaseEntity{

    @NotNull
    private String userId;
    @NotNull
    private String tableChanged;
    @NotNull
    private String idAffected;
    @NotNull
    private Date tookPlaceOn;
    @NotNull
    private String action;
    @NotNull
    private String details;

    public LogEntry() {
    }

    public LogEntry(String userId, String tableChanged, String idAffected, Date tookPlaceOn, String action, String details) {
        this.userId = userId;
        this.tableChanged = tableChanged;
        this.idAffected = idAffected;
        this.tookPlaceOn = tookPlaceOn;
        this.action = action;
        this.details = details;
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

    @Override
    public String toString() {
        return "LogEntry{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", tableChanged='" + tableChanged + '\'' +
                ", idAffected='" + idAffected + '\'' +
                ", tookPlaceOn=" + tookPlaceOn +
                ", action='" + action + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
