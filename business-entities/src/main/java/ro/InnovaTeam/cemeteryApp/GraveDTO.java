package ro.InnovaTeam.cemeteryApp;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

import static ro.InnovaTeam.cemeteryApp.ValidationErrors.*;
import static ro.InnovaTeam.cemeteryApp.ValidationErrors.GRAVE_WIDTH_INVALID;

/**
 * Created by robert on 11/28/2014.
 */
public class GraveDTO extends BaseDTO {

    @NotNull(message = GRAVE_PARCEL_ID_INVALID)
    private Integer parcelId;
    @NotNull(message = GRAVE_CREATED_ON_INVALID)
    private Date createdOn;
    @NotNull(message = GRAVE_TYPE_BLANK)
    private String type;
    @NotNull(message = GRAVE_WIDTH_INVALID)
    @Min(value = 1, message = GRAVE_WIDTH_INVALID)
    private Integer width;
    @NotNull(message = GRAVE_LENGTH_INVALID)
    @Min(value = 1, message = GRAVE_LENGTH_INVALID)
    private Integer length;
    private List<DeceasedDTO> deceased;

    public GraveDTO() {
    }

    public GraveDTO(Integer parcelId, Date createdOn, String type, Integer width, Integer length) {
        this.parcelId = parcelId;
        this.createdOn = createdOn;
        this.type = type;
        this.width = width;
        this.length = length;
    }

    public Integer getParcelId() {
        return parcelId;
    }

    public void setParcelId(Integer parcelId) {
        this.parcelId = parcelId;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public List<DeceasedDTO> getDeceased() {
        return deceased;
    }

    public void setDeceased(List<DeceasedDTO> deceased) {
        this.deceased = deceased;
    }

    @Override
    public String toString() {
        return "Structure{" +
                "id=" + id +
                ", parcelId=" + parcelId +
                ", createdOn=" + createdOn +
                ", type='" + type + '\'' +
                ", width=" + width +
                ", length=" + length +
                ", deceased=" + deceased +
                '}';
    }
}
