package ro.InnovaTeam.cemeteryApp;

import ro.InnovaTeam.cemeteryApp.ParcelDTO;

import java.util.Arrays;
import java.util.List;

/**
 * Created by robert on 11/27/2014.
 */
public class ParcelList {

    private List<ParcelDTO> content;

    public ParcelList() {
    }

    public ParcelList(List<ParcelDTO> content) {
        this.content = content;
    }

    public List<ParcelDTO> getContent() {
        return content;
    }

    public void setContent(List<ParcelDTO> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ParcelList{" +
                "content=" + Arrays.toString(content.toArray()) +
                '}';
    }
}
