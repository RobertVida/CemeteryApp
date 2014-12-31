package ro.InnovaTeam.cemeteryApp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.InnovaTeam.cemeteryApp.eao.RestingPlaceRequestEAO;
import ro.InnovaTeam.cemeteryApp.model.Filter;
import ro.InnovaTeam.cemeteryApp.model.RestingPlaceRequest;
import ro.InnovaTeam.cemeteryApp.service.LogEntryService;
import ro.InnovaTeam.cemeteryApp.service.RestingPlaceRequestService;

import java.util.List;

/**
 * Created by robert on 11/18/2014.
 */
@Transactional
@Service
public class RestingPlaceRequestServiceImpl extends LoggableService<RestingPlaceRequest, RestingPlaceRequestEAO, LogEntryService> implements RestingPlaceRequestService {

    @Autowired
    private RestingPlaceRequestEAO requestEAO;
    @Autowired
    private LogEntryService logService;

    @Override
    public Integer create(RestingPlaceRequest request) {
        return loggedCreate(requestEAO, logService, request);
    }

    @Override
    public RestingPlaceRequest delete(Integer userId, Integer id) {
        return loggedDelete(requestEAO, logService, userId, id);
    }

    @Override
    public RestingPlaceRequest update(RestingPlaceRequest request) {
        return loggedUpdate(requestEAO, logService, request);
    }

    @Override
    public RestingPlaceRequest findById(Integer id) {
        return requestEAO.findById(id);
    }

    @Override
    public List<RestingPlaceRequest> findByFilter(Filter filter) {
        return requestEAO.findByFilter(filter);
    }

    @Override
    public List<RestingPlaceRequest> findByFilterAndStatus(Filter filter, String status) {
        return requestEAO.findByFilterAndStatus(filter, status);
    }

    @Override
    public Integer countByFilter(Filter filter) {
        return requestEAO.countByFilter(filter);
    }

    @Override
    public Integer countByFilterAndStatus(Filter filter, String status) {
        return requestEAO.countByFilterAndStatus(filter, status);
    }
}
