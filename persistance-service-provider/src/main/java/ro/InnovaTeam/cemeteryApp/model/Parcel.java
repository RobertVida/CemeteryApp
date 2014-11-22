package ro.InnovaTeam.cemeteryApp.model;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by lucian.vaida on 2/11/2014.
 */
public class Parcel extends BaseEntity {

    @NotNull
    private String name;
    @NotNull
    private Integer cemeteryId;
    private Structure structure;
    private List<ParcelHistoryEntry> history;

    public Parcel() {
    }

    public Parcel(Integer cemeteryId, String name) {
        this.cemeteryId = cemeteryId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCemeteryId() {
        return cemeteryId;
    }

    public void setCemeteryId(Integer cemeteryId) {
        this.cemeteryId = cemeteryId;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public List<ParcelHistoryEntry> getHistory() {
        return history;
    }

    public void setHistory(List<ParcelHistoryEntry> history) {
        this.history = history;
    }

    @Override
    public String toString() {
        return "Parcel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cemeteryId=" + cemeteryId +
                ", structure=" + structure +
                ", history=" + history +
                '}';
    }
}
