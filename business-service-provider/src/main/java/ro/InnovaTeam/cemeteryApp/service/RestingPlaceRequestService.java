package ro.InnovaTeam.cemeteryApp.service;

import ro.InnovaTeam.cemeteryApp.model.Filter;
import ro.InnovaTeam.cemeteryApp.model.RestingPlaceRequest;

import java.util.List;

/**
 * Created by robert on 11/28/2014.
 */
public interface RestingPlaceRequestService extends EntityService<RestingPlaceRequest> {

    public List<RestingPlaceRequest> findByFilterAndStatus(Filter filter, String status);

    public Integer countByFilterAndStatus(Filter filter, String status);
}
