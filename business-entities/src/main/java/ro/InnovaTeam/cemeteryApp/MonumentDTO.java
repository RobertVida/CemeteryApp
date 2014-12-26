package ro.InnovaTeam.cemeteryApp;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Created by lucian.vaida on 2/11/2014.
 */
public class MonumentDTO extends BaseDTO {

    @NotNull
    private Integer parcelId;
    @NotNull
    private Date createdOn;
    @NotNull
    private String type;
    @NotNull
    private Integer width;
    @NotNull
    private Integer length;
    @NotNull
    private String name;
    @NotNull
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
