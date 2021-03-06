package ro.InnovaTeam.cemeteryApp.model;

import java.util.Date;

/**
 * Created by lucian.vaida on 2/11/2014.
 */
public class Contract extends BaseEntity {

    private Integer structureId;
    private Integer requestId;
    private Date signedOn;
    private Date updatedOn;
    private Date expiresOn;

    public Contract() {
        super("contracts");
    }

    public Contract(Integer structureId, Integer requestId, Date signedOn, Date updatedOn, Date expiresOn) {
        super("contracts");
        this.structureId = structureId;
        this.requestId = requestId;
        this.signedOn = signedOn;
        this.updatedOn = updatedOn;
        this.expiresOn = expiresOn;
    }

    public Date getExpiresOn() {
        return expiresOn;
    }

    public void setExpiresOn(Date expiresOn) {
        this.expiresOn = expiresOn;
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

    public void setRequestId(Integer clientId) {
        this.requestId = clientId;
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

    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", structureId=" + structureId +
                ", requestId=" + requestId +
                ", signedOn=" + signedOn +
                ", updatedOn=" + updatedOn +
                ", expiresOn=" + expiresOn +
                '}';
    }
}
