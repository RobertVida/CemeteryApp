package ro.InnovaTeam.cemeteryApp.util;

import ro.InnovaTeam.cemeteryApp.ParcelDTO;
import ro.InnovaTeam.cemeteryApp.model.Parcel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Catalin Sorecau on 11/24/2014.
 */
public abstract class ParcelUtil {

    public static Parcel toDB(ParcelDTO parcelDTO) {
        if (parcelDTO == null) {
            return null;
        }
        Parcel parcel = new Parcel();
        parcel.setId(parcelDTO.getId());
        parcel.setCemeteryId(parcelDTO.getCemeteryId());
        parcel.setName(parcelDTO.getName());
        parcel.setUserId(parcelDTO.getUserId());

        return parcel;
    }

    public static ParcelDTO toDTO(Parcel parcel) {
        if (parcel == null) {
            return null;
        }
        ParcelDTO parcelDTO = new ParcelDTO();
        parcelDTO.setId(parcel.getId());
        parcelDTO.setCemeteryId(parcel.getCemeteryId());
        parcelDTO.setName(parcel.getName());

        return parcelDTO;
    }

    public static List<Parcel> toDB(List<ParcelDTO> parcelDTOs) {
        if (parcelDTOs == null) {
            return null;
        }
        List<Parcel> parcels = new ArrayList<Parcel>();
        for (ParcelDTO parcelDTO : parcelDTOs) {
            parcels.add(toDB(parcelDTO));
        }
        return parcels;
    }

    public static List<ParcelDTO> toDTO(List<Parcel> parcels) {
        if (parcels == null) {
            return null;
        }
        List<ParcelDTO> parcelDTOs = new ArrayList<ParcelDTO>();
        for (Parcel parcel : parcels) {
            parcelDTOs.add(toDTO(parcel));
        }
        return parcelDTOs;
    }
}
