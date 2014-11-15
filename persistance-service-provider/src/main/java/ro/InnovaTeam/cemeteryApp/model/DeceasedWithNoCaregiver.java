package ro.InnovaTeam.cemeteryApp.model;
import java.sql.Date;

/**
 * Created by lucian.vaida on 2/11/2014.
 */
public class DeceasedWithNoCaregiver extends Deceased{

    private long noCareGiverDocumentId;
    private long certificateId;
    private long requestIMLid;
    private String map;

    public DeceasedWithNoCaregiver(long id, String firstName, String lastName, String cnp, String religion, Date diedOn, long burialDocumentId, Date burialOn, long noCareGiverDocumentId, long certificateId, long requestIMLid, String map) {
        super(id, firstName, lastName, cnp, religion, diedOn, burialDocumentId, burialOn);
        this.noCareGiverDocumentId = noCareGiverDocumentId;
        this.certificateId = certificateId;
        this.requestIMLid = requestIMLid;
        this.map = map;
    }

    public long getNoCareGiverDocumentId() {
        return noCareGiverDocumentId;
    }

    public void setNoCareGiverDocumentId(long noCareGiverDocumentId) {
        this.noCareGiverDocumentId = noCareGiverDocumentId;
    }

    public long getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(long certificateId) {
        this.certificateId = certificateId;
    }

    public long getRequestIMLid() {
        return requestIMLid;
    }

    public void setRequestIMLid(long requestIMLid) {
        this.requestIMLid = requestIMLid;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }
}
