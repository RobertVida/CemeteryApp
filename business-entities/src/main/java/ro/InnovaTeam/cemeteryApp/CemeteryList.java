package ro.InnovaTeam.cemeteryApp;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Catalin Sorecau on 11/24/2014.
 */
public class CemeteryList implements Serializable {

    private List<CemeteryDTO> content;

    public CemeteryList() {
    }

    public CemeteryList(List<CemeteryDTO> content) {
        this.content = content;
    }

    public List<CemeteryDTO> getContent() {
        return content;
    }

    public void setContent(List<CemeteryDTO> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "CemeteryList{" +
                "content=" + Arrays.toString(content.toArray()) +
                '}';
    }
}
