package ro.InnovaTeam.cemeteryApp.model;

import javax.validation.constraints.NotNull;

/**
 * Created by lucian.vaida on 2/11/2014.
 */
public class Parcel extends BaseEntity {

    @NotNull
    private String name;
    @NotNull
    private Integer cemeteryId;

    public Parcel() {
    }

    public Parcel(Integer cemeteryId, String name) {
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
}
