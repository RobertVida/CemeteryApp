package ro.InnovaTeam.cemeteryApp.model;
import java.sql.Date;

/**
 * Created by lucian.vaida on 2/11/2014.
 */
public class DeceasedWithNoCaregiver extends Deceased{

    private Integer noCareGiverDocumentId;
    private Integer certificateId;
    private Integer requestIMLid;
    private String map;

    public DeceasedWithNoCaregiver() {
    }

    public DeceasedWithNoCaregiver(String firstName, String lastName, String cnp, String religion, Date diedOn,
                                   Integer burialDocumentId, Date burialOn, Integer noCareGiverDocumentId, Integer certificateId, Integer requestIMLid, String map) {
        super(firstName, lastName, cnp, religion, diedOn, burialDocumentId, burialOn);
        this.noCareGiverDocumentId = noCareGiverDocumentId;
        this.certificateId = certificateId;
        this.requestIMLid = requestIMLid;
        this.map = map;
    }

    public Integer getNoCareGiverDocumentId() {
        return noCareGiverDocumentId;
    }

    public void setNoCareGiverDocumentId(Integer noCareGiverDocumentId) {
        this.noCareGiverDocumentId = noCareGiverDocumentId;
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

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "DeceasedWithNoCaregiver{" +
                "id=" + id +
                ", firstName='" + getFirstName ()+ '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", cnp='" + getCnp() + '\'' +
                ", religion='" + getReligion() + '\'' +
                ", diedOn=" + getDiedOn() +
                ", burialDocumentId=" + getBurialDocumentId() +
                ", burialOn=" + getBurialOn() +
                ", noCareGiverDocumentId=" + noCareGiverDocumentId +
                ", certificateId=" + certificateId +
                ", requestIMLid=" + requestIMLid +
                ", map='" + map + '\'' +
                '}';
    }
}
