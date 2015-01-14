package ro.InnovaTeam.cemeteryApp.eao.impl;

import org.springframework.stereotype.Component;
import ro.InnovaTeam.cemeteryApp.eao.DeceasedEAO;
import ro.InnovaTeam.cemeteryApp.model.Deceased;
import ro.InnovaTeam.cemeteryApp.model.Filter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static ro.InnovaTeam.cemeteryApp.helpers.AndWithOrRestrictionBuilder.allOf;
import static ro.InnovaTeam.cemeteryApp.helpers.ColumnConstraintBuilder.column;
import static ro.InnovaTeam.cemeteryApp.helpers.ConstraintWrapper.AndConstraintWrapper.and;

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
        return translate(getSession().createSQLQuery(
                "SELECT D.* FROM deceased D " +
                        makeFilterQuery(filter)
        ).list());
    }

    @Override
    public Integer countByFilter(Filter filter) {
        return ((BigInteger) getSession().createSQLQuery(
                "SELECT COUNT(*) FROM deceased D " +
                        makeFilterQuery(filter)
        ).list().get(0)).intValue();
    }

    private String makeFilterQuery(Filter filter) {
        return (filter.getParentId() != null ? " JOIN burialdocuments B ON D.deceased_id = B.deceased_id" : "" )+
                " WHERE " + and(
                        allOf(filter.getSearchCriteria())
                                .areAtLeastOnceInAnyOf("d.first_name", "d.last_name", "d.cnp", "d.religion"),
                        filter.getParentId() != null ? column("B.structure_id").is(filter.getParentId()) : null
                ) +
                " LIMIT " + (filter.getPageNo() - 1) + " , " + filter.getPageSize() + " ";
    }

    private List<Deceased> translate(List<Object[]> objects) {
        List<Deceased> list = new ArrayList<Deceased>();
        for(Object[] o : objects){
            list.add(translate(o));
        }
        return list;
    }

    private Deceased translate(Object[] o){
        Deceased deceased = new Deceased();
        deceased.setId((Integer)o[0]);
        deceased.setFirstName((String) o[1]);
        deceased.setLastName((String) o[2]);
        deceased.setCnp((String) o[3]);
        deceased.setReligion((String) o[4]);
        deceased.setDiedOn((Date) o[5]);

        return deceased;
    }
}
