package ro.InnovaTeam.cemeteryApp.model;

import java.util.Date;

/**
 * Created by lucian.vaida on 2/11/2014.
 */
public class Contract extends BaseEntity {

    private Integer structureId;
    private Integer clientId;
    private Date signedOn;
    private Date updatedOn;
    private String type;
    private Date expiresOn;
    private Structure structure;
    private Client client;

    public Contract() {
        super("contracts");
    }

    public Contract(Integer structureId, Integer clientId, Date signedOn, Date updatedOn, String type, Date expiresOn) {
        super("contracts");
        this.structureId = structureId;
        this.clientId = clientId;
        this.signedOn = signedOn;
        this.updatedOn = updatedOn;
        this.type = type;
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

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
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

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", structureId=" + structureId +
                ", clientId=" + clientId +
                ", signedOn=" + signedOn +
                ", updatedOn=" + updatedOn +
                ", type='" + type + '\'' +
                ", expiresOn=" + expiresOn +
                '}';
    }
}
