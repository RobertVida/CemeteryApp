package ro.InnovaTeam.cemeteryApp.model;
import java.sql.Date;

/**
 * Created by lucian.vaida on 2/11/2014.
 */
public class RestingPlaceRequest {

    private long id;
    private long clientId;
    private Date createdOn;
    private int infocetNumber;
    private String status;

    public RestingPlaceRequest(long id, long clientId, Date createdOn, int infocetNumber, String status) {
        this.id = id;
        this.clientId = clientId;
        this.createdOn = createdOn;
        this.infocetNumber = infocetNumber;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public int getInfocetNumber() {
        return infocetNumber;
    }

    public void setInfocetNumber(int infocetNumber) {
        this.infocetNumber = infocetNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
