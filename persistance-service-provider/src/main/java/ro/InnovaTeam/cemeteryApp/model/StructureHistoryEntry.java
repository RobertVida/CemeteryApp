package ro.InnovaTeam.cemeteryApp.model;

import java.util.Date;

/**
 * Created by lucian.vaida on 2/11/2014.
 */
public class StructureHistoryEntry extends BaseEntity {

    private Integer structureId;
    private String description;
    private Date date;

    public StructureHistoryEntry() {
        super("structurehistory");
    }

    public StructureHistoryEntry(Integer structureId, String description, Date date) {
        super("structurehistory");
        this.structureId = structureId;
        this.description = description;
        this.date = date;
    }

    public Integer getStructureId() {
        return structureId;
    }

    public void setStructureId(Integer parcelId) {
        this.structureId = parcelId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ParcelHistoryEntry{" +
                "id=" + id +
                ", structureId=" + structureId +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }
}
