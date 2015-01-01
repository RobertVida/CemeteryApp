package ro.InnovaTeam.cemeteryApp.eao.impl;

import org.springframework.stereotype.Component;
import ro.InnovaTeam.cemeteryApp.eao.BurialDocumentEAO;
import ro.InnovaTeam.cemeteryApp.helpers.QueryBuilder;
import ro.InnovaTeam.cemeteryApp.model.BurialDocument;
import ro.InnovaTeam.cemeteryApp.model.Filter;

import java.util.List;

import static ro.InnovaTeam.cemeteryApp.helpers.AliasBuilder.from;
import static ro.InnovaTeam.cemeteryApp.helpers.ColumnConstraintBuilder.column;

/**
 * Created by robert on 12/31/2014.
 */
@Component
public class BurialDocumentEAOImpl extends EntityEAOImpl<BurialDocument> implements BurialDocumentEAO {

    private static final String TABLE = "burialdocuments";

    @Override
    public Integer create(BurialDocument burialDocument) {
        Integer id = create(TABLE, burialDocument);
        flush();
        return id;
    }

    @Override
    public BurialDocument delete(Integer id) {
        BurialDocument burialDocument = delete(TABLE, id);
        flush();
        return burialDocument;
    }

    @Override
    public BurialDocument update(BurialDocument burialDocument) {
        update(TABLE, burialDocument);
        flush();
        return findById(burialDocument.getId());
    }

    @Override
    public BurialDocument findById(Integer id) {
        BurialDocument burialDocument = findById(TABLE, id);
        flush();
        return burialDocument;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<BurialDocument> findByFilter(Filter filter) {
        return makeFilterQuery(filter)
                .build().list();
    }

    @Override
    public BurialDocument findByDeceasedId(Integer id) {
        List list = makeFilterQuery(new Filter(1))
                .where(
                        column("d.deceasedId").is(id)
                )
                .build().list();
        return list != null && list.size() > 0 ? (BurialDocument)list.get(0) : null;
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
