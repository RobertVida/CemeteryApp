package ro.InnovaTeam.cemeteryApp;

import java.io.Serializable;
import java.util.List;

/**
 * Created by robert on 11/28/2014.
 */
public class GraveList extends BaseList<GraveDTO> implements Serializable {

    public GraveList() {
    }

    public GraveList(List<GraveDTO> content) {
        super(content);
    }

    @Override
    public String toString() {
        return "GraveList{" +
                "content=" + content +
                '}';
    }
}
