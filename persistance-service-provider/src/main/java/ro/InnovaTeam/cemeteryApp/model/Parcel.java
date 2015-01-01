package ro.InnovaTeam.cemeteryApp.model;

/**
 * Created by lucian.vaida on 2/11/2014.
 */
public class Parcel extends BaseEntity {

    private String name;
    private Integer cemeteryId;

    public Parcel() {
        super("parcel");
    }

    public Parcel(Integer cemeteryId, String name) {
        super("parcel");
        this.cemeteryId = cemeteryId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCemeteryId() {
        return cemeteryId;
    }

    public void setCemeteryId(Integer cemeteryId) {
        this.cemeteryId = cemeteryId;
    }

    @Override
    public String toString() {
        return "Parcel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cemeteryId=" + cemeteryId +
                '}';
    }
}
