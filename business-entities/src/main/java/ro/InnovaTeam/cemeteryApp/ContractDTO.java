package ro.InnovaTeam.cemeteryApp;

import ro.InnovaTeam.cemeteryApp.validators.ContractExpiresOn;
import ro.InnovaTeam.cemeteryApp.validators.ContractUpdatedOn;

import javax.validation.constraints.NotNull;
import java.util.Date;

import static ro.InnovaTeam.cemeteryApp.ValidationErrors.*;

/**
 * Created by robert on 1/2/2015.
 */
@ContractUpdatedOn(message = CONTRACT_UPDATED_INVALID)
@ContractExpiresOn(message = CONTRACT_EXPIRES_INVALID)
public class ContractDTO extends BaseDTO {

    @NotNull(message = CONTRACT_STRUCTURE_ID_NULL)
    private Integer structureId;
    @NotNull(message = CONTRACT_REQUEST_ID_NULL)
    private Integer requestId;
    @NotNull(message = CONTRACT_SIGNED_ON_NULL)
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
