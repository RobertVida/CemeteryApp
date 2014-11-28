package ro.InnovaTeam.cemeteryApp;
import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * Created by lucian.vaida on 2/11/2014.
 */
public class DeceasedDTO extends BaseDTO {

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String cnp;
    @NotNull
    private String religion;
    @NotNull
    private Date diedOn;
    @NotNull
    private Integer burialDocumentId;
    @NotNull
    private Integer structureId;
    @NotNull
    private Date burialOn;

    public DeceasedDTO() {
    }

    public DeceasedDTO(String firstName, String lastName, String cnp, String religion, Date diedOn, Integer burialDocumentId, Integer structureId, Date burialOn) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cnp = cnp;
        this.religion = religion;
        this.diedOn = diedOn;
        this.burialDocumentId = burialDocumentId;
        this.structureId = structureId;
        this.burialOn = burialOn;
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

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public Date getDiedOn() {
        return diedOn;
    }

    public void setDiedOn(Date diedOn) {
        this.diedOn = diedOn;
    }

    public Integer getBurialDocumentId() {
        return burialDocumentId;
    }

    public void setBurialDocumentId(Integer burialDocumentId) {
        this.burialDocumentId = burialDocumentId;
    }

    public Integer getStructureId() {
        return structureId;
    }

    public void setStructureId(Integer structureId) {
        this.structureId = structureId;
    }

    public Date getBurialOn() {
        return burialOn;
    }

    public void setBurialOn(Date burialOn) {
        this.burialOn = burialOn;
    }

    @Override
    public String toString() {
        return "Deceased{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", cnp='" + cnp + '\'' +
                ", religion='" + religion + '\'' +
                ", diedOn=" + diedOn +
                ", burialDocumentId=" + burialDocumentId +
                ", structureId=" + structureId +
                ", burialOn=" + burialOn +
                '}';
    }
}
