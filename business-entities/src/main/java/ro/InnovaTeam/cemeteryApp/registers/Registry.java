package ro.InnovaTeam.cemeteryApp.registers;

import java.util.List;

/**
 * Created by robert on 1/5/2015.
 */
public class Registry<T extends RegistryEntryDTO> {

    private List<T> content;

    public Registry() {
    }

    public Registry(List<T> content) {
        this.content = content;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
