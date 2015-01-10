package ro.InnovaTeam.cemeteryApp;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

import static ro.InnovaTeam.cemeteryApp.ValidationErrors.*;

/**
 * Created by lucian.vaida on 2/11/2014.
 */
public class MonumentDTO extends BaseDTO {

    @NotNull(message = MONUMENT_PARCEL_ID_INVALID)
    private Integer parcelId;
    @NotNull(message = MONUMENT_CREATED_ON_INVALID)
    private Date createdOn;
    @NotNull(message = MONUMENT_TYPE_BLANK)
    private String type;
    @NotNull(message = MONUMENT_WIDTH_INVALID)
    @Min(value = 1, message = MONUMENT_WIDTH_INVALID)
    private Integer width;
    @NotNull(message = MONUMENT_LENGTH_INVALID)
    @Min(value = 1, message = MONUMENT_LENGTH_INVALID)
    private Integer length;
    @NotBlank(message = MONUMENT_NAME_BLANK)
    private String name;
    @NotBlank(message = MONUMENT_DESCRIPTION_BLANK)
    private String description;
    private List<DeceasedDTO> deceased;

    public MonumentDTO() {
    }

    public MonumentDTO(Integer parcelId, Date createdOn, String type, Integer width, Integer length,
                       String name, String description) {
        this.name = name;
        this.description = description;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
                ", parcelId=" + getParcelId() +
                ", createdOn=" + getCreatedOn() +
                ", type='" + getType() + '\'' +
                ", width=" + getWidth() +
                ", length=" + getLength() +
                ", deceased=" + getDeceased() +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
