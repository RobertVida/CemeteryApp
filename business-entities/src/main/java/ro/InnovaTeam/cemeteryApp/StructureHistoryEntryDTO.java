package ro.InnovaTeam.cemeteryApp;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

import static ro.InnovaTeam.cemeteryApp.ValidationErrors.*;

/**
 * Created by robert on 01/01/2015.
 */
public class StructureHistoryEntryDTO extends BaseDTO {

    @NotNull(message = STRUCTURE_HISTORY_STRUCTURE_ID_INVALID)
    private Integer structureId;
    @NotBlank(message = STRUCTURE_HISTORY_DESCRIPTION_BLANK)
    private String description;
    @NotNull(message = STRUCTURE_HISTORY_DATE_INVALID)
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
