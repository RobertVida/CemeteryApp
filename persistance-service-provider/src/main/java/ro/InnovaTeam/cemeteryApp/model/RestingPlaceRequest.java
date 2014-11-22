package ro.InnovaTeam.cemeteryApp.model;
import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * Created by lucian.vaida on 2/11/2014.
 */
public class RestingPlaceRequest extends BaseEntity{

    @NotNull
    private Integer clientId;
    @NotNull
    private Date createdOn;
    @NotNull
    private Integer infocetNumber;
    @NotNull
    private String status;

    public RestingPlaceRequest() {
    }

    public RestingPlaceRequest(Integer clientId, Date createdOn, Integer infocetNumber, String status) {
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
