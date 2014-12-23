package ro.InnovaTeam.cemeteryApp.eao.impl;

import org.springframework.stereotype.Component;
import ro.InnovaTeam.cemeteryApp.eao.CemeteryEAO;
import ro.InnovaTeam.cemeteryApp.helpers.QueryBuilder;
import ro.InnovaTeam.cemeteryApp.model.Cemetery;
import ro.InnovaTeam.cemeteryApp.model.Filter;

import java.util.List;

import static ro.InnovaTeam.cemeteryApp.helpers.AliasBuilder.from;
import static ro.InnovaTeam.cemeteryApp.helpers.AndWithOrRestrictionBuilder.allOf;

/**
 * Created by robert on 11/18/2014.
 */
@Component
public class CemeteryEAOImpl extends EntityEAOImpl<Cemetery> implements CemeteryEAO {

    private static final String TABLE = "cemeteries";

    @Override
    public Integer create(Cemetery cemetery) {
        Integer id = create(TABLE, cemetery);
        flush();
        return id;
    }

    @Override
    public Cemetery delete(Integer id) {
        Cemetery cemetery = delete(TABLE, id);
        flush();
        return cemetery;
    }

    @Override
    public Cemetery update(Cemetery cemetery) {
        update(TABLE, cemetery);
        flush();
        return findById(cemetery.getId());
    }

    @Override
    public Cemetery findById(Integer id) {
        Cemetery cemetery = findById(TABLE, id);
        flush();
        return cemetery;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Cemetery> findByFilter(Filter filter) {
        return QueryBuilder.instance(getSession())
                .select(
                        from(TABLE).as("c")
                ).where(
                        allOf(filter.getSearchCriteria())
                                .areAtLeastOnceInAnyOf("c.name", "c.address")
                )
                .setMaxResults(filter.getPageSize())
                .setFirstResult(filter.getPageNo())
                .build().list();
    }
}
