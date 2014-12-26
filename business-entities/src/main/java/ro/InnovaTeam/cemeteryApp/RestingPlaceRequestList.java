package ro.InnovaTeam.cemeteryApp;

import java.io.Serializable;
import java.util.List;

/**
 * Created by robert on 11/27/2014.
 */
public class RestingPlaceRequestList extends BaseList<RestingPlaceRequestDTO> implements Serializable {

    public RestingPlaceRequestList() {
    }

    public RestingPlaceRequestList(List<RestingPlaceRequestDTO> content) {
        super(content);
    }

    @Override
    public String toString() {
        return "RestingPlaceRequestList{" +
                "content=" + content +
                '}';
    }
}
