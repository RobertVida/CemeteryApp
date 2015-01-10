package ro.InnovaTeam.cemeteryApp;
import org.hibernate.validator.constraints.NotBlank;
import ro.InnovaTeam.cemeteryApp.validators.SimpleCnp;

import javax.validation.constraints.NotNull;
import java.util.Date;

import static ro.InnovaTeam.cemeteryApp.ValidationErrors.*;

/**
 * Created by lucian.vaida on 2/11/2014.
 */
public class DeceasedDTO extends BaseDTO {

    @NotBlank(message = DECEASED_FIRST_NAME_BLANK)
    private String firstName;
    @NotBlank(message = DECEASED_LAST_NAME_BLANK)
    private String lastName;
    @SimpleCnp(message = DECEASED_CNP_INVALID)
    private String cnp;
    @NotBlank(message = DECEASED_RELIGION_BLANK)
    private String religion;
    @NotNull(message = DECEASED_DIED_ON_INVALID)
    private Date diedOn;
    private Integer burialDocumentId;
    @NotNull(message = DECEASED_STRUCTURE_ID_INVALID)
    private Integer structureId;
    @NotNull(message = DECEASED_BURIAL_ON_INVALID)
    private Date burialOn;
    private Boolean hasCaregiver = true;
    private Integer noCaregiverDocumentId;
    private Integer certificateId;
    private Integer requestIMLid;

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

    public Boolean getHasCaregiver() {
        return hasCaregiver;
    }

    public void setHasCaregiver(Boolean hasCaregiver) {
        this.hasCaregiver = hasCaregiver;
    }

    public Integer getNoCaregiverDocumentId() {
        return noCaregiverDocumentId;
    }

    public void setNoCaregiverDocumentId(Integer noCaregiverDocumentId) {
        this.noCaregiverDocumentId = noCaregiverDocumentId;
    }

    public Integer getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(Integer certificateId) {
        this.certificateId = certificateId;
    }

    public Integer getRequestIMLid() {
        return requestIMLid;
    }

    public void setRequestIMLid(Integer requestIMLid) {
        this.requestIMLid = requestIMLid;
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
