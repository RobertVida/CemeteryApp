package ro.InnovaTeam.cemeteryApp.eao.impl;

import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;
import ro.InnovaTeam.cemeteryApp.eao.SearchEAO;
import ro.InnovaTeam.cemeteryApp.model.Filter;
import ro.InnovaTeam.cemeteryApp.model.registers.BurialRegistryEntry;
import ro.InnovaTeam.cemeteryApp.model.registers.RegistryEntry;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static ro.InnovaTeam.cemeteryApp.helpers.AndWithOrRestrictionBuilder.allOf;
import static ro.InnovaTeam.cemeteryApp.helpers.SQLQueryResultTranslator.*;

/**
 * Created by robert on 1/5/2015.
 */
@Component
public class SearchEAOImpl implements SearchEAO {

    @Autowired
    protected HibernateTemplate hibernateTemplate;

    @Override
    @SuppressWarnings("unchecked")
    public List<BurialRegistryEntry> getBurialRegistry(Filter filter) {
        return makeEntryList(getSession().createSQLQuery(
                "SELECT D.deceased_id, first_name, last_name, religion, buried_on , p.parcel_id, p.name FROM deceased D " +
                "INNER JOIN burialdocuments B ON D.deceased_id= B.deceased_id " +
                "INNER JOIN structures S ON S.structure_id= B.structure_id " +
                "INNER JOIN parcels P ON P.parcel_id= S.parcel_id " +
                "WHERE " + allOf(filter.getSearchCriteria()).areAtLeastOnceInAnyOf("first_name", "last_name", "religion", "p.name")
        ).list(), BurialRegistryEntry.class);
    }

    @Override
    public Integer getBurialRegistryCount(Filter filter) {
        return ((BigInteger) getSession().createSQLQuery(
                "SELECT COUNT(*) FROM deceased D " +
                        burialRegister(filter)
        ).list().get(0)).intValue();
    }

    private String burialRegister(Filter filter) {
        return "INNER JOIN burialdocuments B ON D.deceased_id= B.deceased_id " +
                "INNER JOIN structures S ON S.structure_id= B.structure_id " +
                "INNER JOIN parcels P ON P.parcel_id= S.parcel_id " +
                "WHERE " + allOf(filter.getSearchCriteria()).areAtLeastOnceInAnyOf("first_name", "last_name", "religion", "p.name");
    }

    private <T extends RegistryEntry> List<T> makeEntryList(List<Object[]> result, Class<T> clazz) {
        List<T> list = new ArrayList<T>();
        for (Object[] e : result) {
            list.add(translate(e, clazz));
        }
        return list;
    }

    protected Session getSession() {
        return hibernateTemplate.getSessionFactory().getCurrentSession();
    }

}
