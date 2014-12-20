package ro.InnovaTeam.cemeteryApp.model;

import java.sql.Date;
import java.util.List;

/**
 * Created by lucian.vaida on 2/11/2014.
 */
public class Structure extends BaseEntity {

    private Integer parcelId;
    private Date createdOn;
    private String type;
    private Integer width;
    private Integer length;
    private List<Deceased> deceased;

    public Structure() {
        super("structures");
    }

    public Structure(String tableName) {
        super(tableName);
    }

    public Structure(String tableName, Integer parcelId, Date createdOn, String type, Integer width, Integer length) {
        super(tableName);
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

    public List<Deceased> getDeceased() {
        return deceased;
    }

    public void setDeceased(List<Deceased> deceased) {
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
                '}';
    }
}
