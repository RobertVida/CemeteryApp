package ro.InnovaTeam.cemeteryApp.util;

import ro.InnovaTeam.cemeteryApp.RestingPlaceRequestDTO;
import ro.InnovaTeam.cemeteryApp.model.RestingPlaceRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Catalin Sorecau on 11/24/2014.
 */
public abstract class RestingPlaceRequestUtil {

    public static RestingPlaceRequest toDB(RestingPlaceRequestDTO requestDTO) {
        if (requestDTO == null) {
            return null;
        }
        RestingPlaceRequest request = new RestingPlaceRequest();
        request.setId(requestDTO.getId());
        request.setClientId(requestDTO.getClientId());
        request.setCreatedOn(requestDTO.getCreatedOn());
        request.setInfocetNumber(requestDTO.getInfocetNumber());
        request.setStatus(requestDTO.getStatus());
        request.setUserId(requestDTO.getUserId());

        return request;
    }

    public static RestingPlaceRequestDTO toDTO(RestingPlaceRequest request) {
        if (request == null) {
            return null;
        }
        RestingPlaceRequestDTO requestDTO = new RestingPlaceRequestDTO();
        requestDTO.setId(request.getId());
        requestDTO.setClientId(request.getClientId());
        requestDTO.setCreatedOn(request.getCreatedOn());
        requestDTO.setInfocetNumber(request.getInfocetNumber());
        requestDTO.setStatus(request.getStatus());

        return requestDTO;
    }

    public static List<RestingPlaceRequest> toDB(List<RestingPlaceRequestDTO> requestDTOs) {
        if (requestDTOs == null) {
            return null;
        }
        List<RestingPlaceRequest> requests = new ArrayList<RestingPlaceRequest>();
        for (RestingPlaceRequestDTO requestDTO : requestDTOs) {
            requests.add(toDB(requestDTO));
        }
        return requests;
    }

    public static List<RestingPlaceRequestDTO> toDTO(List<RestingPlaceRequest> requests) {
        if (requests == null) {
            return null;
        }
        List<RestingPlaceRequestDTO> requestDTOs = new ArrayList<RestingPlaceRequestDTO>();
        for (RestingPlaceRequest request : requests) {
            requestDTOs.add(toDTO(request));
        }
        return requestDTOs;
    }
}
