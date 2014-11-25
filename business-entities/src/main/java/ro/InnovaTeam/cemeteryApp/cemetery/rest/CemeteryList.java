package ro.InnovaTeam.cemeteryApp.cemetery.rest;

import ro.InnovaTeam.cemeteryApp.cemetery.CemeteryDTO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Catalin Sorecau on 11/24/2014.
 */
public class CemeteryList implements Serializable {

    private List<CemeteryDTO> content;

    public List<CemeteryDTO> getContent() {
        return content;
    }

    public void setContent(List<CemeteryDTO> content) {
        this.content = content;
    }
}
