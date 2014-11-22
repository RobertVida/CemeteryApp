package ro.InnovaTeam.cemeteryApp.eao.impl;

import org.springframework.stereotype.Component;
import ro.InnovaTeam.cemeteryApp.eao.ParcelEAO;
import ro.InnovaTeam.cemeteryApp.model.Parcel;

import java.util.List;

/**
 * Created by robert on 11/18/2014.
 */
@Component
public class ParcelEAOImpl extends EntityEAOImpl<Parcel> implements ParcelEAO {

    private static final String TABLE = "parcels";

    @Override
    public Integer create(Parcel parcel) {
        Integer id = create(TABLE, parcel);
        flush();
        return id;
    }

    @Override
    public Parcel delete(Integer id) {
        Parcel parcel = delete(TABLE, id);
        flush();
        return parcel;
    }

    @Override
    public Parcel update(Parcel parcel) {
        update(TABLE, parcel);
        flush();
        return findById(TABLE, parcel.getId());
    }

    @Override
    public Parcel findById(Integer id) {
        return findById(TABLE, id);
    }

    @Override
    public List<Parcel> findByFilter() {
        return findByFilter(TABLE);
    }

    @Override
    public List<Parcel> findByCemeteryId(Integer cemeteryId) {
        return getSession().createQuery(makeSelectQuery(TABLE) + addParentEntityConstraint(cemeteryId)).list();
    }

    private String addParentEntityConstraint(Integer cemeteryId) {
        return " WHERE cemetery_id = " + cemeteryId;
    }
}
