package ro.InnovaTeam.cemeteryApp.model;
import java.sql.Date;

/**
 * Created by lucian.vaida on 2/11/2014.
 */
public class Contract {

    private long id;
    private long structureId;
    private long clientId;
    private Date signedOn;
    private Date updatedOn;
    private String type;
    private Date expiresOn;

    public Date getExpiresOn() {
        return expiresOn;
    }

    public void setExpiresOn(Date expiresOn) {
        this.expiresOn = expiresOn;
    }

    public Contract(long id, long structureId, long clientId, Date signedOn, Date updatedOn, String type, Date expiresOn) {
        this.id = id;
        this.structureId = structureId;
        this.clientId = clientId;
        this.signedOn = signedOn;
        this.updatedOn = updatedOn;
        this.type = type;
        this.expiresOn = expiresOn;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStructureId() {
        return structureId;
    }

    public void setStructureId(long structureId) {
        this.structureId = structureId;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
