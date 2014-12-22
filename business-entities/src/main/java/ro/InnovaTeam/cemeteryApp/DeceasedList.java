package ro.InnovaTeam.cemeteryApp;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Catalin Sorecau on 11/24/2014.
 */
public class DeceasedList extends BaseList<DeceasedDTO> implements Serializable {

    public DeceasedList() {
    }

    public DeceasedList(List<DeceasedDTO> content) {
        super(content);
    }

    @Override
    public String toString() {
        return "DeceasedList{" +
                "content=" + content +
                '}';
    }
}
