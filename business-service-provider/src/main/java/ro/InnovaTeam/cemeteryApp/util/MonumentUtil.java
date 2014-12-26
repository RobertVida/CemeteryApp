package ro.InnovaTeam.cemeteryApp.util;

import ro.InnovaTeam.cemeteryApp.MonumentDTO;
import ro.InnovaTeam.cemeteryApp.model.Monument;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by robert on 11/28/2014.
 */
public abstract class MonumentUtil {

    public static Monument toDB(MonumentDTO monumentDTO) {
        if (monumentDTO == null) {
            return null;
        }
        Monument monument = new Monument();
        monument.setId(monumentDTO.getId());
        monument.setParcelId(monumentDTO.getParcelId());
        monument.setCreatedOn(monumentDTO.getCreatedOn());
        monument.setType(monumentDTO.getType());
        monument.setWidth(monumentDTO.getWidth());
        monument.setLength(monumentDTO.getLength());
        monument.setUserId(monumentDTO.getUserId());
        monument.setName(monumentDTO.getName());
        monument.setDescription(monumentDTO.getDescription());

        return monument;
    }

    public static MonumentDTO toDTO(Monument monument) {
        if (monument == null) {
            return null;
        }
        MonumentDTO monumentDTO = new MonumentDTO();
        monumentDTO.setId(monument.getId());
        monumentDTO.setParcelId(monument.getParcelId());
        monumentDTO.setCreatedOn(monument.getCreatedOn());
        monumentDTO.setType(monument.getType());
        monumentDTO.setWidth(monument.getWidth());
        monumentDTO.setLength(monument.getLength());
        monumentDTO.setUserId(monument.getUserId());
        monumentDTO.setName(monument.getName());
        monumentDTO.setDescription(monument.getDescription());

        return monumentDTO;
    }

    public static List<Monument> toDB(List<MonumentDTO> monumentDTOs) {
        if (monumentDTOs == null) {
            return null;
        }
        List<Monument> monuments = new ArrayList<Monument>();
        for (MonumentDTO monumentDTO : monumentDTOs) {
            monuments.add(toDB(monumentDTO));
        }
        return monuments;
    }

    public static List<MonumentDTO> toDTO(List<Monument> monuments) {
        if (monuments == null) {
            return null;
        }
        List<MonumentDTO> monumentDTOs = new ArrayList<MonumentDTO>();
        for (Monument monument : monuments) {
            monumentDTOs.add(toDTO(monument));
        }
        return monumentDTOs;
    }
}
