package ro.InnovaTeam.cemeteryApp.registers;

import java.util.Date;

/**
 * Created by robert on 1/5/2015.
 */
public class BurialRegistryEntryDTO implements RegistryEntryDTO {

    private Integer deceasedId;
    private String firstName;
    private String lastName;
    private String religion;
    private Date buriedOn;
    private Integer parcelId;
    private String parcelName;

    public BurialRegistryEntryDTO() {
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

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
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
}
