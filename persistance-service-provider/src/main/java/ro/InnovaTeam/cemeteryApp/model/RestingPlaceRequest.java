package ro.InnovaTeam.cemeteryApp.model;

import java.util.Date;

/**
 * Created by lucian.vaida on 2/11/2014.
 */
public class RestingPlaceRequest extends BaseEntity {

    private Integer clientId;
    private Date createdOn;
    private Integer infocetNumber;
    private String status;

    public RestingPlaceRequest() {
        super("restingplacerequests");
    }

    public RestingPlaceRequest(Integer clientId, Date createdOn, Integer infocetNumber, String status) {
        super("restingplacerequests");
        this.clientId = clientId;
        this.createdOn = createdOn;
        this.infocetNumber = infocetNumber;
        this.status = status;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Integer getInfocetNumber() {
        return infocetNumber;
    }

    public void setInfocetNumber(Integer infocetNumber) {
        this.infocetNumber = infocetNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RestingPlaceRequest{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", createdOn=" + createdOn +
                ", infocetNumber=" + infocetNumber +
                ", status='" + status + '\'' +
                '}';
    }
}
