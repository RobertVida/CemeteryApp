package ro.InnovaTeam.cemeteryApp.model;
import java.sql.Date;

/**
 * Created by lucian.vaida on 2/11/2014.
 */
public class Structure {

    private long id;
    private long parcelId;
    private Date createdOn;
    private String type;
    private int width;
    private int length;

    public Structure(long id, long parcelId, Date createdOn, String type, int width, int length) {
        this.id = id;
        this.parcelId = parcelId;
        this.createdOn = createdOn;
        this.type = type;
        this.width = width;
        this.length = length;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getParcelId() {
        return parcelId;
    }

    public void setParcelId(long parcelId) {
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
