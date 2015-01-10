package ro.InnovaTeam.cemeteryApp;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

import static ro.InnovaTeam.cemeteryApp.ValidationErrors.*;

/**
 * Created by Catalin Sorecau on 11/24/2014.
 */
public class ParcelDTO extends BaseDTO{

    @NotBlank(message = PARCEL_NAME_BLANK)
    private String name;
    @NotNull(message = PARCEL_CEMETERY_ID_INVALID)
    private Integer cemeteryId;

    public ParcelDTO() {
    }

    public ParcelDTO(Integer cemeteryId, String name) {
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
