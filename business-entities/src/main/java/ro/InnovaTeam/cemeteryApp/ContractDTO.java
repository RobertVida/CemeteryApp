package ro.InnovaTeam.cemeteryApp;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by robert on 1/2/2015.
 */
public class ContractDTO extends BaseDTO {

    @NotNull
    private Integer structureId;
    @NotNull
    private Integer requestId;
    @NotNull
    private Date signedOn;
    private Date updatedOn;
    private Date expiresOn;

    public ContractDTO() {
    }

    public Integer getStructureId() {
        return structureId;
    }

    public void setStructureId(Integer structureId) {
        this.structureId = structureId;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public Date getSignedOn() {
        return signedOn;
    }

    public void setSignedOn(Date signedOn) {
        this.signedOn = signedOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Date getExpiresOn() {
        return expiresOn;
    }

    public void setExpiresOn(Date expiresOn) {
        this.expiresOn = expiresOn;
    }

    @Override
    public String toString() {
        return "ContractDTO{" +
                "structureId=" + structureId +
                ", requestId=" + requestId +
                ", signedOn=" + signedOn +
                ", updatedOn=" + updatedOn +
                ", expiresOn=" + expiresOn +
                '}';
    }
}
