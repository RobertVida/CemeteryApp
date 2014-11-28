package ro.InnovaTeam.cemeteryApp;

import java.util.List;

/**
 * Created by robert on 11/28/2014.
 */
public class GraveList {

    private List<GraveDTO> content;

    public GraveList() {
    }

    public GraveList(List<GraveDTO> content) {
        this.content = content;
    }

    public List<GraveDTO> getContent() {
        return content;
    }

    public void setContent(List<GraveDTO> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "GraveList{" +
                "content=" + content +
                '}';
    }
}
