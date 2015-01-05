package ro.InnovaTeam.cemeteryApp.eao.impl;

import org.springframework.stereotype.Component;
import ro.InnovaTeam.cemeteryApp.eao.NoCaregiverDocumentEAO;
import ro.InnovaTeam.cemeteryApp.helpers.QueryBuilder;
import ro.InnovaTeam.cemeteryApp.model.Filter;
import ro.InnovaTeam.cemeteryApp.model.NoCaregiverDocument;

import java.util.List;

import static ro.InnovaTeam.cemeteryApp.helpers.AliasBuilder.from;
import static ro.InnovaTeam.cemeteryApp.helpers.ColumnConstraintBuilder.column;

/**
 * Created by robert on 1/5/2015.
 */
@Component
public class NoCaregiverDocumentEAOImpl extends EntityEAOImpl<NoCaregiverDocument> implements NoCaregiverDocumentEAO{

    private static final String TABLE = "nocaregiverdocuments";

    @Override
    public Integer create(NoCaregiverDocument noCaregiverDocument) {
        Integer id = create(TABLE, noCaregiverDocument);
        flush();
        return id;
    }

    @Override
    public NoCaregiverDocument delete(Integer id) {
        NoCaregiverDocument noCaregiverDocument = delete(TABLE, id);
        flush();
        return noCaregiverDocument;
    }

    @Override
    public NoCaregiverDocument update(NoCaregiverDocument noCaregiverDocument) {
        update(TABLE, noCaregiverDocument);
        flush();
        return findById(noCaregiverDocument.getId());
    }

    @Override
    public NoCaregiverDocument findById(Integer id) {
        NoCaregiverDocument noCaregiverDocument = findById(TABLE, id);
        flush();
        return noCaregiverDocument;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<NoCaregiverDocument> findByFilter(Filter filter) {
        return makeFilterQuery(filter)
                .build().list();
    }

    @Override
    public NoCaregiverDocument findByDeceasedId(Integer id) {
        List list = makeFilterQuery(new Filter(1))
                .where(
                        column("d.deceasedId").is(id)
                )
                .build().list();
        return list != null && list.size() > 0 ? (NoCaregiverDocument)list.get(0) : null;
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
                )
                .setMaxResults(filter.getPageSize())
                .setFirstResult(filter.getPageNo());
    }
}
