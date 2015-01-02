package ro.InnovaTeam.cemeteryApp;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by robert on 01/01/2015.
 */
public class StructureHistoryEntryDTO extends BaseDTO {

    @NotNull
    private Integer structureId;
    @NotNull
    private String description;
    @NotNull
    private Date date;

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
        return "ParcelHistoryEntryDTO{" +
                "id=" + id +
                ", structureId=" + structureId +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }
}
