package ro.InnovaTeam.cemeteryApp.model;
/**
 * Created by lucian.vaida on 2/11/2014.
 */
public class Parcel {

    private long id;
    private String address;
    private long cemeteryId;

    public Parcel(long id,  long cemeteryId ,String address) {
        this.id = id;
        this.cemeteryId = cemeteryId;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getCemeteryId() {
        return cemeteryId;
    }

    public void setCemeteryId(long cemeteryId) {
        this.cemeteryId = cemeteryId;
    }
}
