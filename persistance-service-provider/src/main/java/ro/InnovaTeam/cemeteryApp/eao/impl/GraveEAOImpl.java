package ro.InnovaTeam.cemeteryApp.eao.impl;

import org.springframework.stereotype.Component;
import ro.InnovaTeam.cemeteryApp.eao.GraveEAO;
import ro.InnovaTeam.cemeteryApp.helpers.QueryBuilder;
import ro.InnovaTeam.cemeteryApp.model.Filter;
import ro.InnovaTeam.cemeteryApp.model.Grave;
import ro.InnovaTeam.cemeteryApp.model.StructureType;

import java.util.List;

import static ro.InnovaTeam.cemeteryApp.helpers.AliasBuilder.from;
import static ro.InnovaTeam.cemeteryApp.helpers.ColumnConstraintBuilder.column;
import static ro.InnovaTeam.cemeteryApp.helpers.ConstraintWrapper.AndConstraintWrapper.and;

/**
 * Created by robert on 11/28/2014.
 */
@Component
public class GraveEAOImpl extends EntityEAOImpl<Grave> implements GraveEAO {

    private static final String TABLE = "graves";

    @Override
    public Integer create(Grave grave) {
        Integer id = create(TABLE, grave);
        flush();
        return id;
    }

    @Override
    public Grave delete(Integer id) {
        Grave grave = delete(TABLE, id);
        flush();
        return grave;
    }

    @Override
    public Grave update(Grave grave) {
        update(TABLE, grave);
        flush();
        return findById(grave.getId());
    }

    @Override
    public Grave findById(Integer id) {
        Grave grave = findById(TABLE, id);
        flush();
        return grave;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Grave> findByFilter(Filter filter) {
        return QueryBuilder.instance(getSession())
                .select(
                        from(TABLE).as("g")
                ).where(
                        and(
                            column("g.parcelId").is(filter.getParentId()),
                            column("g.type").like(StructureType.Grave.toString())
                        )
                )
                .setMaxResults(filter.getPageSize())
                .setFirstResult(filter.getPageNo())
                .build().list();
    }
}
