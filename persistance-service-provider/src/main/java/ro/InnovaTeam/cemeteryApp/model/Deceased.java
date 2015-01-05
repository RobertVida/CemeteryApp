package ro.InnovaTeam.cemeteryApp.model;
import java.util.Date;

/**
 * Created by lucian.vaida on 2/11/2014.
 */
public class Deceased extends BaseEntity {

    private String firstName;
    private String lastName;
    private String cnp;
    private String religion;
    private Date diedOn;
    private BurialDocument document = new BurialDocument();
    private NoCaregiverDocument noCaregiverDocument;

    public Deceased() {
        super("deceased");
    }

    public Deceased(String tableName) {
        super(tableName);
    }

    public Deceased(String tableName, String firstName, String lastName, String cnp, String religion, Date diedOn, Integer burialDocumentId, Integer structureId, Date burialOn) {
        super(tableName);
        this.firstName = firstName;
        this.lastName = lastName;
        this.cnp = cnp;
        this.religion = religion;
        this.diedOn = diedOn;
        setBurialDocumentId(burialDocumentId);
        setStructureId(structureId);
        setBurialOn(burialOn);
    }

    public void setId(Integer id){
        super.setId(id);
        document.setDeceasedId(id);
        if(noCaregiverDocument != null){
            noCaregiverDocument.setDeceasedId(id);
        }
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
        return document.getId();
    }

    public void setBurialDocumentId(Integer burialDocumentId) {
        this.document.setId(burialDocumentId);
    }

    public Integer getStructureId() {
        return document.getStructureId();
    }

    public void setStructureId(Integer structureId) {
        this.document.setStructureId(structureId);
    }

    public Date getBurialOn() {
        return document.getBurialOn();
    }

    public void setBurialOn(Date burialOn) {
        this.document.setBurialOn(burialOn);
    }

    public BurialDocument getDocument() {
        return document;
    }

    public void setDocument(BurialDocument document) {
        this.document = document;
    }

    public NoCaregiverDocument getNoCaregiverDocument() {
        return noCaregiverDocument;
    }

    public void setNoCaregiverDocument(NoCaregiverDocument noCaregiverDocument) {
        this.noCaregiverDocument = noCaregiverDocument;
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
                ", burialDocument=" + document.toStringNoEntityName() +
                (noCaregiverDocument != null  ? ", noCaregiverDocument=" + noCaregiverDocument.toStringNoEntityName() : "")+
                '}';
    }
}
