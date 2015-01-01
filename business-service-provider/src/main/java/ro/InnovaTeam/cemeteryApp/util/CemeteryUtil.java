package ro.InnovaTeam.cemeteryApp.util;

import ro.InnovaTeam.cemeteryApp.CemeteryDTO;
import ro.InnovaTeam.cemeteryApp.ParcelDTO;
import ro.InnovaTeam.cemeteryApp.model.Cemetery;
import ro.InnovaTeam.cemeteryApp.model.Parcel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Catalin Sorecau on 11/24/2014.
 */
public abstract class CemeteryUtil {

    public static Cemetery toDB(CemeteryDTO cemeteryDTO) {
        if (cemeteryDTO == null) {
            return null;
        }
        Cemetery cemetery = new Cemetery();
        cemetery.setId(cemeteryDTO.getId());
        cemetery.setName(cemeteryDTO.getName());
        cemetery.setAddress(cemeteryDTO.getAddress());
        cemetery.setUserId(cemeteryDTO.getUserId());

        return cemetery;
    }

    public static CemeteryDTO toDTO(Cemetery cemetery) {
        if (cemetery == null) {
            return null;
        }
        CemeteryDTO cemeteryDTO = new CemeteryDTO();
        cemeteryDTO.setId(cemetery.getId());
        cemeteryDTO.setName(cemetery.getName());
        cemeteryDTO.setAddress(cemetery.getAddress());

        return cemeteryDTO;
    }

    public static List<Cemetery> toDB(List<CemeteryDTO> cemeteryDTOs) {
        if (cemeteryDTOs == null) {
            return null;
        }
        List<Cemetery> cemeteries = new ArrayList<Cemetery>();
        for (CemeteryDTO cemeteryDTO : cemeteryDTOs) {
            cemeteries.add(toDB(cemeteryDTO));
        }
        return cemeteries;
    }

    public static List<CemeteryDTO> toDTO(List<Cemetery> cemeteries) {
        if (cemeteries == null) {
            return null;
        }
        List<CemeteryDTO> cemeteryDTOs = new ArrayList<CemeteryDTO>();
        for (Cemetery cemetery : cemeteries) {
            cemeteryDTOs.add(toDTO(cemetery));
        }
        return cemeteryDTOs;
    }
}
