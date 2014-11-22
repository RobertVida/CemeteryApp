package ro.InnovaTeam.cemeteryApp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.InnovaTeam.cemeteryApp.eao.ParcelEAO;
import ro.InnovaTeam.cemeteryApp.model.Parcel;
import ro.InnovaTeam.cemeteryApp.service.ParcelService;

import java.util.List;

/**
 * Created by robert on 11/18/2014.
 */
@Transactional
@Service
public class ParcelServiceImpl implements ParcelService {

    @Autowired
    private ParcelEAO parcelEAO;

    @Override
    public Integer create(Parcel parcel) {
        return parcelEAO.create(parcel);
    }

    @Override
    public Parcel delete(Integer id) {
        //ToDo delete structure on it
        return parcelEAO.delete(id);
    }

    @Override
    public Parcel update(Parcel parcel) {
        return parcelEAO.update(parcel);
    }

    @Override
    public Parcel findById(Integer id) {
        return parcelEAO.findById(id);
    }

    @Override
    public List<Parcel> findByFilter() {
        return parcelEAO.findByFilter();
    }

    @Override
    public List<Parcel> findByCemeteryId(Integer cemeteryId) {
        return parcelEAO.findByCemeteryId(cemeteryId);
    }
}
