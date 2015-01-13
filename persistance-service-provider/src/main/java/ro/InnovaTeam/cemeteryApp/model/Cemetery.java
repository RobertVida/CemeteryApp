package ro.InnovaTeam.cemeteryApp.model;

import java.util.List;

/**
 * Created by lucian.vaida on 2/11/2014.
 */
public class Cemetery extends BaseEntity {

    private String name;
    private String address;
    private List<Parcel> parcels;

    public Cemetery() {
        super("cemeteries");
    }

    public Cemetery(String name, String address) {
        super("cemeteries");
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Cemetery{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
