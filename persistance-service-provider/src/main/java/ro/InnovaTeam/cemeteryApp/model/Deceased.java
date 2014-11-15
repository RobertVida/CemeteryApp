package ro.InnovaTeam.cemeteryApp.model;
import java.sql.Date;

/**
 * Created by lucian.vaida on 2/11/2014.
 */
public class Deceased {

    private long id;
    private String firstName;
    private String lastName;
    private String cnp;
    private String religion;
    private Date diedOn;
    private long burialDocumentId;
    private Date burialOn;

    public Deceased(long id, String firstName, String lastName, String cnp, String religion, Date diedOn, long burialDocumentId, Date burialOn) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cnp = cnp;
        this.religion = religion;
        this.diedOn = diedOn;
        this.burialDocumentId = burialDocumentId;
        this.burialOn = burialOn;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public long getBurialDocumentId() {
        return burialDocumentId;
    }

    public void setBurialDocumentId(long burialDocumentId) {
        this.burialDocumentId = burialDocumentId;
    }

    public Date getBurialOn() {
        return burialOn;
    }

    public void setBurialOn(Date burialOn) {
        this.burialOn = burialOn;
    }
}
