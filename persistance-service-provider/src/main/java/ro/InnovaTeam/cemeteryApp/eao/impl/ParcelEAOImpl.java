package ro.InnovaTeam.cemeteryApp.eao.impl;

import org.springframework.stereotype.Component;
import ro.InnovaTeam.cemeteryApp.eao.ParcelEAO;
import ro.InnovaTeam.cemeteryApp.helpers.FilteredQueryBuilder;
import ro.InnovaTeam.cemeteryApp.model.Filter;
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
        return findById(parcel.getId());
    }

    @Override
    public Parcel findById(Integer id) {
        Parcel parcel = findById(TABLE, id);
        flush();
        return parcel;
    }

    @Override
    public List<Parcel> findByFilter(Filter filter) {
        return findByFilter(TABLE, filter);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Parcel> findByFilter(String tableName, Filter filter) {
        return FilteredQueryBuilder.instance()
                .from(tableName)
                .setFilter(filter)
                .setCriteriaSearchableColumns("name")
                .setParentIdColumn("cemetery_id")
                .build(getSession()).list();
    }
}
