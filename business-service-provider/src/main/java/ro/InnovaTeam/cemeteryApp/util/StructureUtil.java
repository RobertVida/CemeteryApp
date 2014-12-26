package ro.InnovaTeam.cemeteryApp.util;

import ro.InnovaTeam.cemeteryApp.GraveDTO;
import ro.InnovaTeam.cemeteryApp.model.Grave;
import ro.InnovaTeam.cemeteryApp.model.Structure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by robert on 11/28/2014.
 */
public abstract class StructureUtil {

    public static Grave toDB(GraveDTO graveDTO) {
        if (graveDTO == null) {
            return null;
        }
        Grave grave = new Grave();
        grave.setId(graveDTO.getId());
        grave.setParcelId(graveDTO.getParcelId());
        grave.setCreatedOn(graveDTO.getCreatedOn());
        grave.setType(graveDTO.getType());
        grave.setWidth(graveDTO.getWidth());
        grave.setLength(graveDTO.getLength());
        grave.setUserId(graveDTO.getUserId());

        return grave;
    }

    public static GraveDTO toDTO(Grave grave) {
        if (grave == null) {
            return null;
        }
        GraveDTO graveDTO = new GraveDTO();
        graveDTO.setId(grave.getId());
        graveDTO.setParcelId(grave.getParcelId());
        graveDTO.setCreatedOn(grave.getCreatedOn());
        graveDTO.setType(grave.getType());
        graveDTO.setWidth(grave.getWidth());
        graveDTO.setLength(grave.getLength());

        return graveDTO;
    }

    public static List<Structure> toDB(List<GraveDTO> graveDTOs) {
        if (graveDTOs == null) {
            return null;
        }
        List<Structure> graves = new ArrayList<Structure>();
        for (GraveDTO graveDTO : graveDTOs) {
            graves.add(toDB(graveDTO));
        }
        return graves;
    }

    public static List<GraveDTO> toDTO(List<Grave> graves) {
        if (graves == null) {
            return null;
        }
        List<GraveDTO> graveDTOs = new ArrayList<GraveDTO>();
        for (Grave grave : graves) {
            graveDTOs.add(toDTO(grave));
        }
        return graveDTOs;
    }
}
