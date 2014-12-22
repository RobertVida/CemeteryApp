package ro.InnovaTeam.cemeteryApp;

import java.util.List;

/**
 * Created by robert on 12/21/2014.
 */
public class BaseList<Entity extends BaseDTO> {

    protected List<Entity> content;

    public BaseList() {
    }

    public BaseList(List<Entity> content) {
        this.content = content;
    }

    public List<Entity> getContent() {
        return content;
    }

    public void setContent(List<Entity> content) {
        this.content = content;
    }
}
