package ro.InnovaTeam.cemeteryApp.eao.impl;

import org.springframework.stereotype.Component;
import ro.InnovaTeam.cemeteryApp.eao.DeceasedEAO;
import ro.InnovaTeam.cemeteryApp.helpers.QueryBuilder;
import ro.InnovaTeam.cemeteryApp.model.Deceased;
import ro.InnovaTeam.cemeteryApp.model.Filter;

import java.util.List;

import static ro.InnovaTeam.cemeteryApp.helpers.AliasBuilder.from;
import static ro.InnovaTeam.cemeteryApp.helpers.AndWithOrRestrictionBuilder.allOf;

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
    @SuppressWarnings("unchecked")
    public List<Deceased> findByFilter(Filter filter) {
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
                        from(TABLE).as("d")
                ).where(
                        allOf(filter.getSearchCriteria())
                                .areAtLeastOnceInAnyOf("d.firstName", "d.lastName", "d.cnp", "d.religion")
                )
                .setMaxResults(filter.getPageSize())
                .setFirstResult(filter.getPageNo());
    }
}
