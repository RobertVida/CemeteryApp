package ro.InnovaTeam.cemeteryApp.eao.impl;

import org.springframework.stereotype.Component;
import ro.InnovaTeam.cemeteryApp.eao.StructureHistoryEntryEAO;
import ro.InnovaTeam.cemeteryApp.helpers.QueryBuilder;
import ro.InnovaTeam.cemeteryApp.model.Filter;
import ro.InnovaTeam.cemeteryApp.model.StructureHistoryEntry;

import java.util.List;

import static ro.InnovaTeam.cemeteryApp.helpers.AliasBuilder.from;
import static ro.InnovaTeam.cemeteryApp.helpers.AndWithOrRestrictionBuilder.allOf;
import static ro.InnovaTeam.cemeteryApp.helpers.ColumnConstraintBuilder.column;
import static ro.InnovaTeam.cemeteryApp.helpers.ConstraintWrapper.AndConstraintWrapper.and;

/**
 * Created by robert on 1/1/2015.
 */
@Component
public class StructureHistoryEntryEAOImpl extends EntityEAOImpl<StructureHistoryEntry> implements StructureHistoryEntryEAO {

    private static final String TABLE = "structurehistory";

    @Override
    public Integer create(StructureHistoryEntry entry) {
        Integer id = create(TABLE, entry);
        flush();
        return id;
    }

    @Override
    public StructureHistoryEntry delete(Integer id) {
        StructureHistoryEntry entry = delete(TABLE, id);
        flush();
        return entry;
    }

    @Override
    public StructureHistoryEntry update(StructureHistoryEntry entry) {
        update(TABLE, entry);
        flush();
        return findById(entry.getId());
    }

    @Override
    public StructureHistoryEntry findById(Integer id) {
        StructureHistoryEntry entry = findById(TABLE, id);
        flush();
        return entry;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<StructureHistoryEntry> findByFilter(Filter filter) {
        return makeFilterQuery(filter)
                .build().list();
    }

    @Override
    public Integer countByFilter(Filter filter) {
        return ((Long)makeFilterQuery(filter).count()
                .build().iterate().next()).intValue();
    }

    private QueryBuilder makeFilterQuery(Filter filter) {
        return QueryBuilder.instance(getSession())
                .select(
                        from(TABLE).as("h")
                ).where(
                        and(
                                allOf(filter.getSearchCriteria()).areAtLeastOnceInAnyOf("h.description"),
                                column("h.structureId").is(filter.getParentId())
                        )
                )
                .setMaxResults(filter.getPageSize())
                .setFirstResult(filter.getPageNo());
    }
}
