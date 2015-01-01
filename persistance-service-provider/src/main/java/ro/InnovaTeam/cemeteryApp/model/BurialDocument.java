package ro.InnovaTeam.cemeteryApp.model;

import java.util.Date;

/**
 * Created by robert on 12/31/2014.
 */
public class BurialDocument extends BaseEntity {

    private Integer structureId;
    private Integer deceasedId;
    private Date burialOn;

    public BurialDocument() {
        super("burialdocuments");
    }

    public Integer getStructureId() {
        return structureId;
    }

    public void setStructureId(Integer structureId) {
        this.structureId = structureId;
    }

    public Integer getDeceasedId() {
        return deceasedId;
    }

    public void setDeceasedId(Integer deceasedId) {
        this.deceasedId = deceasedId;
    }

    public Date getBurialOn() {
        return burialOn;
    }

    public void setBurialOn(Date burialOn) {
        this.burialOn = burialOn;
    }

    @Override
    public String toString() {
        return "BurialDocument{" +
                "id=" + id +
                ", structureId=" + structureId +
                ", deceasedId=" + deceasedId +
                ", burialOn=" + burialOn +
                '}';
    }

    public String toStringNoEntityName() {
        return "{" +
                "id=" + id +
                ", structureId=" + structureId +
                ", deceasedId=" + deceasedId +
                ", burialOn=" + burialOn +
                '}';
    }
}
