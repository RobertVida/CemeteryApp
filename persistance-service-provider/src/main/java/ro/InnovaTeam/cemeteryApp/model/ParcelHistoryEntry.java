package ro.InnovaTeam.cemeteryApp.model;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Created by lucian.vaida on 2/11/2014.
 */
public class ParcelHistoryEntry extends BaseEntity{

    @NotNull
    private Integer parcelId;
    @NotNull
    private String description;
    @NotNull
    private Date date;
    private List<Document> documents;

    public ParcelHistoryEntry() {
        super("parcelhistory");
    }

    public ParcelHistoryEntry(Integer parcelId, String description, Date date) {
        super("parcelhistory");
        this.parcelId = parcelId;
        this.description = description;
        this.date = date;
    }

    public Integer getParcelId() {
        return parcelId;
    }

    public void setParcelId(Integer parcelId) {
        this.parcelId = parcelId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    @Override
    public String toString() {
        return "ParcelHistoryEntry{" +
                "id=" + id +
                ", parcelId=" + parcelId +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }
}
