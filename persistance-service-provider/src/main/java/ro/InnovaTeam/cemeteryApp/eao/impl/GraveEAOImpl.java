package ro.InnovaTeam.cemeteryApp.eao.impl;

import org.springframework.stereotype.Component;
import ro.InnovaTeam.cemeteryApp.eao.GraveEAO;
import ro.InnovaTeam.cemeteryApp.helpers.FilteredQueryBuilder;
import ro.InnovaTeam.cemeteryApp.model.Filter;
import ro.InnovaTeam.cemeteryApp.model.Grave;

import java.util.List;

/**
 * Created by robert on 11/28/2014.
 */
@Component
public class GraveEAOImpl extends EntityEAOImpl<Grave> implements GraveEAO {

    private static final String TABLE = "structures";
    private static final String ENTITY = "graves";

    @Override
    public Integer create(Grave grave) {
        Integer id = create(ENTITY, grave);
        flush();
        return id;
    }

    @Override
    public Grave delete(Integer id) {
        Grave grave = delete(ENTITY, id);
        flush();
        return grave;
    }

    @Override
    public Grave update(Grave grave) {
        update(ENTITY, grave);
        flush();
        return findById(ENTITY, grave.getId());
    }

    @Override
    public Grave findById(Integer id) {
        return findById(ENTITY, id);
    }

    @Override
    public List<Grave> findByFilter(Filter filter) {
        return findByFilter(ENTITY, filter);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Grave> findByFilter(String tableName, Filter filter) {
        return FilteredQueryBuilder.instance()
                .from(tableName)
                .setFilter(filter)
                .setParentIdColumn("parcel_id")
                .build(getSession()).list();
    }
}
