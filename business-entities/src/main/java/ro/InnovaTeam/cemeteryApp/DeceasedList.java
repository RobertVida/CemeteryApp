package ro.InnovaTeam.cemeteryApp;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Catalin Sorecau on 11/24/2014.
 */
public class DeceasedList implements Serializable {

    private List<DeceasedDTO> content;

    public DeceasedList() {
    }

    public DeceasedList(List<DeceasedDTO> content) {
        this.content = content;
    }

    public List<DeceasedDTO> getContent() {
        return content;
    }

    public void setContent(List<DeceasedDTO> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "DeceasedList{" +
                "content=" + content +
                '}';
    }
}
