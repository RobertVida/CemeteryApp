package ro.InnovaTeam.cemeteryApp.util;

import org.springframework.util.CollectionUtils;
import ro.InnovaTeam.cemeteryApp.cemetery.CemeteryDTO;
import ro.InnovaTeam.cemeteryApp.model.Cemetery;
import ro.InnovaTeam.cemeteryApp.model.Parcel;
import ro.InnovaTeam.cemeteryApp.parcel.ParcelDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Catalin Sorecau on 11/24/2014.
 */
public class CemeteryUtil {

    public static void setCemeteryFromDTO(Cemetery cemetery, CemeteryDTO cemeteryDTO) {
        cemetery.setId(cemeteryDTO.getId());
        cemetery.setName(cemeteryDTO.getName());
        cemetery.setAddress(cemeteryDTO.getAddress());

        List<Parcel> parcels = new ArrayList<Parcel>();
        ParcelUtil.setParcelFromDTOs(parcels, cemeteryDTO.getParcels());
        cemetery.setParcels(parcels);
    }

    public static CemeteryDTO getCemeteryDTO(Cemetery cemetery) {
        CemeteryDTO cemeteryDTO = new CemeteryDTO();

        cemeteryDTO.setId(cemetery.getId());
        cemeteryDTO.setName(cemetery.getName());
        cemeteryDTO.setAddress(cemetery.getAddress());

        List<ParcelDTO> parcelDTOList = new ArrayList<ParcelDTO>();
        List<Parcel> parcels = cemetery.getParcels();
        if (!CollectionUtils.isEmpty(parcels)) {
            for (Parcel parcel : parcels) {
                if (parcel != null) {
                    parcelDTOList.add(ParcelUtil.getParcelDTO(parcel));
                }
            }
        }

        cemeteryDTO.setParcels(parcelDTOList);

        return cemeteryDTO;
    }

    public static List<CemeteryDTO> getCemeteryDTOs(List<Cemetery> cemeteries) {
        List<CemeteryDTO> cemeteryDTOs = new ArrayList<CemeteryDTO>();
        for (Cemetery cemetery : cemeteries) {
            cemeteryDTOs.add(getCemeteryDTO(cemetery));
        }

        return cemeteryDTOs;
    }

    
}
