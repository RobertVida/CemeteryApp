package ro.InnovaTeam.cemeteryApp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.InnovaTeam.cemeteryApp.eao.ParcelEAO;
import ro.InnovaTeam.cemeteryApp.model.Filter;
import ro.InnovaTeam.cemeteryApp.model.Parcel;
import ro.InnovaTeam.cemeteryApp.service.LogEntryService;
import ro.InnovaTeam.cemeteryApp.service.ParcelService;

import java.util.List;

/**
 * Created by robert on 11/18/2014.
 */
@Transactional
@Service
public class ParcelServiceImpl extends LoggableService<Parcel, ParcelEAO, LogEntryService> implements ParcelService {

    @Autowired
    private ParcelEAO parcelEAO;
    @Autowired
    private LogEntryService logService;

    @Override
    public Integer create(Parcel parcel) {
        return loggedCreate(parcelEAO, logService, parcel);
    }

    @Override
    public Parcel delete(Integer userId, Integer id) {
        return loggedDelete(parcelEAO, logService, userId, id);
    }

    @Override
    public Parcel update(Parcel parcel) {
        return loggedUpdate(parcelEAO, logService, parcel);
    }

    @Override
    public Parcel findById(Integer id) {
        return parcelEAO.findById(id);
    }

    @Override
    public List<Parcel> findByFilter(Filter filter) {
        return parcelEAO.findByFilter(filter);
    }
}
