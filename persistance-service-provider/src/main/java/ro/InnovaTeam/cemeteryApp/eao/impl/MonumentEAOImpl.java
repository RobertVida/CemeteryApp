package ro.InnovaTeam.cemeteryApp.eao.impl;

import org.springframework.stereotype.Component;
import ro.InnovaTeam.cemeteryApp.eao.MonumentEAO;
import ro.InnovaTeam.cemeteryApp.helpers.QueryBuilder;
import ro.InnovaTeam.cemeteryApp.model.Filter;
import ro.InnovaTeam.cemeteryApp.model.Monument;
import ro.InnovaTeam.cemeteryApp.model.StructureType;

import java.util.List;

import static ro.InnovaTeam.cemeteryApp.helpers.AliasBuilder.from;
import static ro.InnovaTeam.cemeteryApp.helpers.AndWithOrRestrictionBuilder.allOf;
import static ro.InnovaTeam.cemeteryApp.helpers.ColumnConstraintBuilder.column;
import static ro.InnovaTeam.cemeteryApp.helpers.ConstraintWrapper.AndConstraintWrapper.and;

/**
 * Created by robert on 11/28/2014.
 */
@Component
public class MonumentEAOImpl extends EntityEAOImpl<Monument> implements MonumentEAO {

    private static final String TABLE = "monuments";

    @Override
    public Integer create(Monument monument) {
        Integer id = create(TABLE, monument);
        flush();
        return id;
    }

    @Override
    public Monument delete(Integer id) {
        Monument monument = delete(TABLE, id);
        flush();
        return monument;
    }

    @Override
    public Monument update(Monument monument) {
        update(TABLE, monument);
        flush();
        return findById(monument.getId());
    }

    @Override
    public Monument findById(Integer id) {
        Monument monument = findById(TABLE, id);
        flush();
        return monument;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Monument> findByFilter(Filter filter) {
        return QueryBuilder.instance(getSession())
                .select(
                        from(TABLE).as("m")
                ).where(
                        and(
                                column("m.parcelId").is(filter.getParentId()),
                                column("m.type").like(StructureType.Monument.toString()),
                                allOf(filter.getSearchCriteria())
                                        .areAtLeastOnceInAnyOf("m.name", "m.description")
                        )
                )
                .setMaxResults(filter.getPageSize())
                .setFirstResult(filter.getPageNo())
                .build().list();
    }
}
