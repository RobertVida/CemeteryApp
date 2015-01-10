package ro.InnovaTeam.cemeteryApp;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

import static ro.InnovaTeam.cemeteryApp.ValidationErrors.*;

/**
 * Created by lucian.vaida on 2/11/2014.
 */
public class RestingPlaceRequestDTO extends BaseDTO{

    @NotNull(message = REQUEST_CLIENT_ID_INVALID)
    private Integer clientId;
    @NotNull(message = REQUEST_CREATED_ON_INVALID)
    private Date createdOn;
    @NotNull(message = REQUEST_INFOCET_NUMBER_INVALID)
    private Integer infocetNumber;
    @NotBlank(message = REQUEST_STATUS_BLANK)
    private String status;

    public RestingPlaceRequestDTO() {
    }

    public RestingPlaceRequestDTO(Integer clientId, Date createdOn, Integer infocetNumber, String status) {
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
