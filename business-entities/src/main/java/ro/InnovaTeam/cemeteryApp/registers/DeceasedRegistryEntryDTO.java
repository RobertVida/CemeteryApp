package ro.InnovaTeam.cemeteryApp.registers;

import java.util.Date;

/**
 * Created by robert on 1/5/2015.
 */
public class DeceasedRegistryEntryDTO implements RegistryEntryDTO {

    private Integer deceasedId;
    private String firstName;
    private String lastName;
    private Integer cemeteryId;
    private String cemeteryName;
    private Integer parcelId;
    private String parcelName;
    private Integer graveId;
    private Date buriedOn;

    public DeceasedRegistryEntryDTO() {
    }

    public Integer getDeceasedId() {
        return deceasedId;
    }

    public void setDeceasedId(Integer deceasedId) {
        this.deceasedId = deceasedId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getCemeteryId() {
        return cemeteryId;
    }

    public void setCemeteryId(Integer cemeteryId) {
        this.cemeteryId = cemeteryId;
    }

    public String getCemeteryName() {
        return cemeteryName;
    }

    public void setCemeteryName(String cemeteryName) {
        this.cemeteryName = cemeteryName;
    }

    public Date getBuriedOn() {
        return buriedOn;
    }

    public void setBuriedOn(Date buriedOn) {
        this.buriedOn = buriedOn;
    }

    public Integer getParcelId() {
        return parcelId;
    }

    public void setParcelId(Integer parcelId) {
        this.parcelId = parcelId;
    }

    public String getParcelName() {
        return parcelName;
    }

    public void setParcelName(String parcelName) {
        this.parcelName = parcelName;
    }

    public Integer getGraveId() {
        return graveId;
    }

    public void setGraveId(Integer graveId) {
        this.graveId = graveId;
    }
}
