package ro.InnovaTeam.cemeteryApp;

import java.io.Serializable;
import java.util.List;

/**
 * Created by robert on 01/01/2015.
 */
public class StructureHistoryEntryList extends BaseList<StructureHistoryEntryDTO> implements Serializable {

    public StructureHistoryEntryList() {
    }

    public StructureHistoryEntryList(List<StructureHistoryEntryDTO> content) {
        super(content);
    }

    @Override
    public String toString() {
        return "StructureHistoryEntryList{" +
                "content=" + content +
                '}';
    }
}
