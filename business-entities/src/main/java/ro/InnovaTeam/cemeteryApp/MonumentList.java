package ro.InnovaTeam.cemeteryApp;

import java.io.Serializable;
import java.util.List;

/**
 * Created by robert on 11/28/2014.
 */
public class MonumentList extends BaseList<MonumentDTO> implements Serializable {

    public MonumentList() {
    }

    public MonumentList(List<MonumentDTO> content) {
        super(content);
    }

    @Override
    public String toString() {
        return "MonumentList{" +
                "content=" + content +
                '}';
    }
}
