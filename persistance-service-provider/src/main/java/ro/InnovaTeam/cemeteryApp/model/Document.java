package ro.InnovaTeam.cemeteryApp.model;

import javax.validation.constraints.NotNull;
import java.util.Arrays;

/**
 * Created by lucian.vaida on 2/11/2014.
 */
public class Document extends BaseEntity {

    @NotNull
    private Integer parcelHistoryEntryId;
    @NotNull
    private byte[] document;

    public Document() {
        super("documents");
    }

    public Document(Integer parcelHistoryEntryId, byte[] document) {
        super("documents");
        this.parcelHistoryEntryId = parcelHistoryEntryId;
        this.document = document;
    }

    public Integer getParcelHistoryEntryId() {
        return parcelHistoryEntryId;
    }

    public void setParcelHistoryEntryId(Integer parcelHistoryEntryId) {
        this.parcelHistoryEntryId = parcelHistoryEntryId;
    }

    public byte[] getDocument() {
        return document;
    }

    public void setDocument(byte[] document) {
        this.document = document;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", parcelHistoryEntryId=" + parcelHistoryEntryId +
                ", document=" + Arrays.toString(document) +
                '}';
    }
}
