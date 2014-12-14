package ro.InnovaTeam.cemeteryApp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.InnovaTeam.cemeteryApp.eao.CemeteryEAO;
import ro.InnovaTeam.cemeteryApp.eao.LogEntryEAO;
import ro.InnovaTeam.cemeteryApp.eao.ParcelEAO;
import ro.InnovaTeam.cemeteryApp.model.Cemetery;
import ro.InnovaTeam.cemeteryApp.model.Filter;
import ro.InnovaTeam.cemeteryApp.model.LogEntry;
import ro.InnovaTeam.cemeteryApp.model.Parcel;
import ro.InnovaTeam.cemeteryApp.service.CemeteryService;

import java.util.Date;
import java.util.List;

/**
 * Created by robert on 11/18/2014.
 */
@Transactional
@Service
public class CemeteryServiceImpl implements CemeteryService {

    @Autowired
    private CemeteryEAO cemeteryEAO;
    @Autowired
    private ParcelEAO parcelEAO;
    @Autowired
    private LogEntryEAO logEntryEAO;

    @Override
    public Integer create(Cemetery cemetery) {
        return cemeteryEAO.create(cemetery);
    }

    @Override
    public Cemetery delete(Integer id) {
        //Todo make atomic
        deleteCemeteryParcels(id);
        Cemetery c = cemeteryEAO.delete(id);
        logDelete(c);
        return c;
    }

    private void logDelete(Cemetery c) {
        //Todo : make better
        LogEntry entry = new LogEntry("cemetery", c.getId(), new Date(), "delete");
        entry.setOldValue(c.toString());
        entry.setNewValue("NONE");
        entry.setUserId(1);
        logEntryEAO.create(entry);
    }

    @Override
    public Cemetery update(Cemetery cemetery) {
        return cemeteryEAO.update(cemetery);
    }

    @Override
    public Cemetery findById(Integer id) {
        return cemeteryEAO.findById(id);
    }

    @Override
    public List<Cemetery> findByFilter(Filter filter) {
        return cemeteryEAO.findByFilter(filter);
    }

    private void deleteCemeteryParcels(Integer id) {
        List<Parcel> parcels = parcelEAO.findByFilter(new Filter(null, null, null, id));
        for(Parcel parcel : parcels){
            parcelEAO.delete(parcel.getId());
        }
    }
}
