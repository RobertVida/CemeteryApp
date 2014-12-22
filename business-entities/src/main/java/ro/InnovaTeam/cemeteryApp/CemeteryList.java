package ro.InnovaTeam.cemeteryApp;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Catalin Sorecau on 11/24/2014.
 */
public class CemeteryList extends BaseList<CemeteryDTO> implements Serializable {

    public CemeteryList() {
    }

    public CemeteryList(List<CemeteryDTO> content) {
        super(content);
    }

    @Override
    public String toString() {
        return "CemeteryList{" +
                "content=" + content +
                '}';
    }
}
