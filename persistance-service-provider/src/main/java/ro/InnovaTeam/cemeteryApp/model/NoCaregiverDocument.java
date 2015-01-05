package ro.InnovaTeam.cemeteryApp.model;

/**
 * Created by lucian.vaida on 2/11/2014.
 */
public class NoCaregiverDocument extends BaseEntity{

    private Integer deceasedId;
    private Integer certificateId;
    private Integer requestIMLid;

    public NoCaregiverDocument() {
        super("nocaregiverdocuments");
    }

    public NoCaregiverDocument(Integer deceasedId, Integer certificateId, Integer requestIMLid) {
        this.deceasedId = deceasedId;
        this.certificateId = certificateId;
        this.requestIMLid = requestIMLid;
    }

    public Integer getDeceasedId() {
        return deceasedId;
    }

    public void setDeceasedId(Integer deceasedId) {
        this.deceasedId = deceasedId;
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
        return "DeceasedWithNoCaregiver{" +
                "id=" + id +
                ", deceasedId=" + deceasedId +
                ", certificateId=" + certificateId +
                ", requestIMLid=" + requestIMLid +
                '}';
    }

    public String toStringNoEntityName() {
        return "{" +
                "id=" + id +
                ", deceasedId=" + deceasedId +
                ", certificateId=" + certificateId +
                ", requestIMLid=" + requestIMLid +
                '}';
    }
}
