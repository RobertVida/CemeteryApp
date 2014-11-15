package ro.InnovaTeam.cemeteryApp.model;
/**
 * Created by lucian.vaida on 2/11/2014.
 */
public class Document {

    private long id;
    private long parcelHistoryEntryId;
    private byte[] document;

    public Document(long id, long parcelHistoryEntryId, byte[] document) {
        this.id = id;
        this.parcelHistoryEntryId = parcelHistoryEntryId;
        this.document = document;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getParcelHistoryEntryId() {
        return parcelHistoryEntryId;
    }

    public void setParcelHistoryEntryId(long parcelHistoryEntryId) {
        this.parcelHistoryEntryId = parcelHistoryEntryId;
    }

    public byte[] getDocument() {
        return document;
    }

    public void setDocument(byte[] document) {
        this.document = document;
    }
}
