package ro.InnovaTeam.cemeteryApp.model;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by lucian.vaida on 2/11/2014.
 */
public class Cemetery extends BaseEntity{

    @NotNull
    private String name;
    @NotNull
    private String address;
    private List<Parcel> parcels;

    public Cemetery() {
    }

    public Cemetery(String name, String address) {
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

    public List<Parcel> getParcels() {
        return parcels;
    }

    public void setParcels(List<Parcel> parcels) {
        this.parcels = parcels;
    }

    @Override
    public String toString() {
        return "Cemetery{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", parcels=" + parcels +
                '}';
    }
}
