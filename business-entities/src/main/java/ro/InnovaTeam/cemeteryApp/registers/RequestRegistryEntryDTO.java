package ro.InnovaTeam.cemeteryApp.registers;

import java.util.Date;

/**
 * Created by robert on 1/8/2015.
 */
public class RequestRegistryEntryDTO implements RegistryEntryDTO {

    private Integer requestId;
    private Date createdOn;
    private Integer infocetNumber;
    private String status;
    private Integer clientId;
    private String clientLastName;
    private String clientFirstName;

    public RequestRegistryEntryDTO() {
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
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

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public String getClientFirstName() {
        return clientFirstName;
    }

    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }
}
