package ro.InnovaTeam.cemeteryApp.util;

import ro.InnovaTeam.cemeteryApp.CemeteryDTO;
import ro.InnovaTeam.cemeteryApp.model.Cemetery;
import ro.InnovaTeam.cemeteryApp.model.Parcel;
import ro.InnovaTeam.cemeteryApp.ParcelDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Catalin Sorecau on 11/24/2014.
 */
public abstract class CemeteryUtil {

    public static Cemetery toDB(CemeteryDTO cemeteryDTO){
        Cemetery cemetery = new Cemetery();
        cemetery.setId(cemeteryDTO.getId());
        cemetery.setName(cemeteryDTO.getName());
        cemetery.setAddress(cemeteryDTO.getAddress());
        cemetery.setParcels(getParcels(cemeteryDTO));

        return cemetery;
    }

    public static CemeteryDTO toDTO(Cemetery cemetery){
        CemeteryDTO cemeteryDTO = new CemeteryDTO();
        cemeteryDTO.setId(cemetery.getId());
        cemeteryDTO.setName(cemetery.getName());
        cemeteryDTO.setAddress(cemetery.getAddress());
        cemeteryDTO.setParcels(getParcels(cemetery));

        return cemeteryDTO;
    }

    public static List<Cemetery> toDB(List<CemeteryDTO> cemeteryDTOs){
        List<Cemetery> cemeteries = new ArrayList<Cemetery>();
        for(CemeteryDTO cemeteryDTO : cemeteryDTOs){
            cemeteries.add(toDB(cemeteryDTO));
        }
        return cemeteries;
    }

    public static List<CemeteryDTO> toDTO(List<Cemetery> cemeteries){
        List<CemeteryDTO> cemeteryDTOs = new ArrayList<CemeteryDTO>();
        for(Cemetery cemetery : cemeteries){
            cemeteryDTOs.add(toDTO(cemetery));
        }
        return cemeteryDTOs;
    }

    private static List<Parcel> getParcels(CemeteryDTO cemeteryDTO) {
        return hasParcels(cemeteryDTO) ? ParcelUtil.toDB(cemeteryDTO.getParcels()) : null;
    }

    private static List<ParcelDTO> getParcels(Cemetery cemetery) {
        return hasParcels(cemetery) ? ParcelUtil.toDTO(cemetery.getParcels()) : null;
    }

    private static boolean hasParcels(CemeteryDTO cemeteryDTO) {
        return cemeteryDTO.getParcels() != null;
    }

    private static boolean hasParcels(Cemetery cemetery) {
        return cemetery.getParcels() != null;
    }
}
