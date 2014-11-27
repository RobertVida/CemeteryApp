package ro.InnovaTeam.cemeteryApp.eao.impl;

import org.springframework.stereotype.Component;
import ro.InnovaTeam.cemeteryApp.eao.CemeteryEAO;
import ro.InnovaTeam.cemeteryApp.helpers.FilteredQueryBuilder;
import ro.InnovaTeam.cemeteryApp.model.Cemetery;
import ro.InnovaTeam.cemeteryApp.model.Filter;

import java.util.List;

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
        return findById(TABLE, cemetery.getId());
    }

    @Override
    public Cemetery findById(Integer id) {
        return findById(TABLE, id);
    }

    @Override
    public List<Cemetery> findByFilter(Filter filter) {
        return findByFilter(TABLE, filter);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Cemetery> findByFilter(String tableName, Filter filter) {
        return FilteredQueryBuilder.instance()
                .from(tableName)
                .setFilter(filter)
                .setCriteriaSearchableColumns("name", "address")
                .build(getSession()).list();
    }
}
