package ro.InnovaTeam.cemeteryApp.eao.impl;

import org.springframework.stereotype.Component;
import ro.InnovaTeam.cemeteryApp.eao.DeceasedEAO;
import ro.InnovaTeam.cemeteryApp.helpers.FilteredQueryBuilder;
import ro.InnovaTeam.cemeteryApp.model.Deceased;
import ro.InnovaTeam.cemeteryApp.model.Filter;

import java.util.List;

/**
 * Created by amalia on 11/27/2014.
 */
@Component
public class DeceasedEAOImpl extends EntityEAOImpl<Deceased> implements DeceasedEAO {

    private static final String TABLE = "deceased";

    @Override
    public Integer create(Deceased deceased) {
        Integer id = create(TABLE, deceased);
        flush();
        return id;
    }

    @Override
    public Deceased delete(Integer id) {
        Deceased deceased = delete(TABLE, id);
        flush();
        return deceased;
    }

    @Override
    public Deceased update(Deceased deceased) {
        update(TABLE, deceased);
        flush();
        return findById(deceased.getId());
    }

    @Override
    public Deceased findById(Integer id) {
        Deceased deceased = findById(TABLE, id);
        flush();
        return deceased;
    }

    @Override
    public List<Deceased> findByFilter(Filter filter) {
        return findByFilter(TABLE, filter);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Deceased> findByFilter(String tableName, Filter filter) {
        return FilteredQueryBuilder.instance()
                .from(tableName)
                .setFilter(filter)
                .setCriteriaSearchableColumns("first_name", "last_name", "cnp", "religion")
                .build(getSession()).list();
    }
}
