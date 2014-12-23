package ro.InnovaTeam.cemeteryApp.eao.impl;

import org.springframework.stereotype.Component;
import ro.InnovaTeam.cemeteryApp.eao.ParcelEAO;
import ro.InnovaTeam.cemeteryApp.helpers.QueryBuilder;
import ro.InnovaTeam.cemeteryApp.model.Filter;
import ro.InnovaTeam.cemeteryApp.model.Parcel;

import java.util.List;

import static ro.InnovaTeam.cemeteryApp.helpers.AliasBuilder.from;
import static ro.InnovaTeam.cemeteryApp.helpers.AndWithOrRestrictionBuilder.allOf;
import static ro.InnovaTeam.cemeteryApp.helpers.ColumnConstraintBuilder.column;
import static ro.InnovaTeam.cemeteryApp.helpers.ConstraintWrapper.AndConstraintWrapper.and;

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
    @SuppressWarnings("unchecked")
    public List<Parcel> findByFilter(Filter filter) {
        return QueryBuilder.instance(getSession())
                .select(
                        from(TABLE).as("p")
                ).where(
                        and(
                                allOf(filter.getSearchCriteria()).areAtLeastOnceInAnyOf("p.name"),
                                column("p.cemeteryId").is(filter.getParentId())
                        )
                )
                .setMaxResults(filter.getPageSize())
                .setFirstResult(filter.getPageNo())
                .build().list();
    }
}
