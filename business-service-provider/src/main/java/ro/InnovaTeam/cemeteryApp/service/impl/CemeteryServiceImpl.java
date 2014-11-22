package ro.InnovaTeam.cemeteryApp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.InnovaTeam.cemeteryApp.eao.CemeteryEAO;
import ro.InnovaTeam.cemeteryApp.eao.ParcelEAO;
import ro.InnovaTeam.cemeteryApp.model.Cemetery;
import ro.InnovaTeam.cemeteryApp.model.Parcel;
import ro.InnovaTeam.cemeteryApp.service.CemeteryService;

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

    @Override
    public Integer create(Cemetery cemetery) {
        return cemeteryEAO.create(cemetery);
    }

    @Override
    public Cemetery delete(Integer id) {
        //Todo make atomic
        deleteCemeteryParcels(id);
        return cemeteryEAO.delete(id);
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
    public List<Cemetery> findByFilter() {
        return cemeteryEAO.findByFilter();
    }

    private void deleteCemeteryParcels(Integer id) {
        List<Parcel> parcels = parcelEAO.findByCemeteryId(id);
        for(Parcel parcel : parcels){
            parcelEAO.delete(parcel.getId());
        }
    }
}
