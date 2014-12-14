package ro.InnovaTeam.cemeteryApp;

import java.util.Date;

/**
 * Created by robert on 12/14/2014.
 */
public class LogEntryDTO extends BaseDTO{

    private String tableChanged;
    private Integer idAffected;
    private Date tookPlaceOn;
    private String action;
    private String oldValue;
    private String newValue;

    public String getTableChanged() {
        return tableChanged;
    }

    public void setTableChanged(String tableChanged) {
        this.tableChanged = tableChanged;
    }

    public Integer getIdAffected() {
        return idAffected;
    }

    public void setIdAffected(Integer idAffected) {
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

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    @Override
    public String toString() {
        return "LogEntryDTO{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", tableChanged='" + tableChanged + '\'' +
                ", idAffected='" + idAffected + '\'' +
                ", tookPlaceOn=" + tookPlaceOn +
                ", action='" + action + '\'' +
                ", oldValue='" + oldValue + '\'' +
                ", newValue='" + newValue + '\'' +
                '}';
    }
}
