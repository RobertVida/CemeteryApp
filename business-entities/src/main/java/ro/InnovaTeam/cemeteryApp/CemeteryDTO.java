package ro.InnovaTeam.cemeteryApp;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Catalin Sorecau on 11/24/2014.
 */
public class CemeteryDTO extends BaseDTO{

    @NotNull
    private String name;
    @NotNull
    private String address;

    private List<ParcelDTO> parcels;

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

    public List<ParcelDTO> getParcels() {
        return parcels;
    }

    public void setParcels(List<ParcelDTO> parcels) {
        this.parcels = parcels;
    }

    @Override
    public String toString() {
        return "CemeteryDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", parcels=" + parcels +
                '}';
    }
}
