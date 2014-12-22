package ro.InnovaTeam.cemeteryApp;

import java.io.Serializable;
import java.util.List;

/**
 * Created by robert on 11/27/2014.
 */
public class ParcelList extends BaseList<ParcelDTO> implements Serializable {

    public ParcelList() {
    }

    public ParcelList(List<ParcelDTO> content) {
        super(content);
    }

    @Override
    public String toString() {
        return "ParcelList{" +
                "content=" + content +
                '}';
    }
}
