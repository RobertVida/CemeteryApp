package ro.InnovaTeam.cemeteryApp.eao;

import ro.InnovaTeam.cemeteryApp.model.Filter;
import ro.InnovaTeam.cemeteryApp.model.RestingPlaceRequest;

import java.util.List;

/**
 * Created by robert on 12/26/2014.
 */
public interface RestingPlaceRequestEAO extends EntityEAO<RestingPlaceRequest> {

    public List<RestingPlaceRequest> findByFilterAndStatus(Filter filter, String status);
}
