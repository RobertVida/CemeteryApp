package ro.InnovaTeam.cemeteryApp.eao.impl;

import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;
import ro.InnovaTeam.cemeteryApp.eao.SearchEAO;
import ro.InnovaTeam.cemeteryApp.model.Filter;
import ro.InnovaTeam.cemeteryApp.model.registers.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static ro.InnovaTeam.cemeteryApp.helpers.AndWithOrRestrictionBuilder.allOf;
import static ro.InnovaTeam.cemeteryApp.helpers.ColumnConstraintBuilder.column;
import static ro.InnovaTeam.cemeteryApp.helpers.ConstraintWrapper.AndConstraintWrapper.and;
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
                        burialRegister(filter)
        ).list(), BurialRegistryEntry.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<GraveRegistryEntry> getGraveRegistry(Filter filter) {
        return makeEntryList(getSession().createSQLQuery(
                "SELECT C.cemetery_id, C.name, P.parcel_id, P.name, S.structure_id, CL.first_name, CL.last_name, CL.home_address, CON.contract_id , D.first_name, D.last_name, B.buried_on, width * length FROM cemeteries C " +
                        graveRegister(filter)
        ).list(), GraveRegistryEntry.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<MonumentRegistryEntry> getMonumentRegistry(Filter filter) {
        return makeEntryList(getSession().createSQLQuery(
                "SELECT C.cemetery_id, C.name, P.parcel_id, P.name, S.structure_id, CL.first_name, CL.last_name, CL.home_address, CON.contract_id , D.first_name, D.last_name, B.buried_on, width * length FROM cemeteries C " +
                        monumentRegister(filter)
        ).list(), MonumentRegistryEntry.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<DeceasedRegistryEntry> getDeceasedRegistry(Filter filter, String nameOrder, String diedOnOrder) {
        return makeEntryList(getSession().createSQLQuery(
                "SELECT D.deceased_id, first_name, last_name, C.cemetery_id, C.name, P.parcel_id, P.name, S.structure_id, D.died_on FROM deceased D " +
                        deceasedRegistry(filter, nameOrder, diedOnOrder)
        ).list(), DeceasedRegistryEntry.class);
    }

    @Override
    public Integer getBurialRegistryCount(Filter filter) {
        return ((BigInteger) getSession().createSQLQuery(
                "SELECT COUNT(*) FROM deceased D " +
                        burialRegister(filter)
        ).list().get(0)).intValue();
    }

    @Override
    public Integer getGraveRegistryCount(Filter filter) {
        return ((BigInteger) getSession().createSQLQuery(
                "SELECT COUNT(*) FROM cemeteries C " +
                        graveRegister(filter)
        ).list().get(0)).intValue();
    }

    @Override
    public Integer getMonumentRegistryCount(Filter filter) {
        return ((BigInteger) getSession().createSQLQuery(
                "SELECT COUNT(*) FROM cemeteries C " +
                        monumentRegister(filter)
        ).list().get(0)).intValue();
    }

    @Override
    public Integer getDeceasedRegistryCount(Filter filter, String nameOrder, String diedOnOrder) {
        return ((BigInteger) getSession().createSQLQuery(
                "SELECT COUNT(*) FROM deceased D " +
                        deceasedRegistry(filter, nameOrder, diedOnOrder)
        ).list().get(0)).intValue();
    }

    private <T extends RegistryEntry> List<T> makeEntryList(List<Object[]> result, Class<T> clazz) {
        List<T> list = new ArrayList<T>();
        for (Object[] e : result) {
            list.add(translate(e, clazz));
        }
        return list;
    }


    public  String burialRegister(Filter filter) {
        return "INNER JOIN burialdocuments B ON D.deceased_id= B.deceased_id " +
                "INNER JOIN structures S ON S.structure_id= B.structure_id " +
                "INNER JOIN parcels P ON P.parcel_id= S.parcel_id " +
                "WHERE " + allOf(filter.getSearchCriteria()).areAtLeastOnceInAnyOf("first_name", "last_name", "religion", "p.name") +
                limit(filter);
    }

    public  String graveRegister(Filter filter) {
        return "INNER JOIN parcels P ON P.cemetery_id = C.cemetery_id " +
                "INNER JOIN structures S ON S.parcel_id = P.parcel_id AND S.type = \"Grave\" " +
                "LEFT JOIN contracts CON ON CON.structure_id = S.structure_id " +
                "LEFT JOIN restingplacerequests R ON R.request_id = CON.request_id " +
                "LEFT JOIN clients CL ON CL.client_id= R.client_id " +
                "LEFT JOIN burialdocuments B ON B.structure_id = S.structure_id " +
                "LEFT JOIN deceased D ON D.deceased_id = B.deceased_id " +
                "WHERE " + allOf(filter.getSearchCriteria()).areAtLeastOnceInAnyOf("C.name", "P.name", "CL.first_name", "CL.last_name", "CL.home_address", "D.first_name", "D.last_name") +
                limit(filter);
    }

    public  String monumentRegister(Filter filter) {
        return "INNER JOIN parcels P ON P.cemetery_id = C.cemetery_id " +
                "INNER JOIN structures S ON S.parcel_id = P.parcel_id AND S.type = \"Monument\" " +
                "LEFT JOIN contracts CON ON CON.structure_id = S.structure_id " +
                "LEFT JOIN restingplacerequests R ON R.request_id = CON.request_id " +
                "LEFT JOIN clients CL ON CL.client_id= R.client_id " +
                "LEFT JOIN burialdocuments B ON B.structure_id = S.structure_id " +
                "LEFT JOIN deceased D ON D.deceased_id = B.deceased_id " +
                "WHERE " + allOf(filter.getSearchCriteria()).areAtLeastOnceInAnyOf("C.name", "P.name", "CL.first_name", "CL.last_name", "CL.home_address", "D.first_name", "D.last_name") +
                limit(filter);
    }

    private String deceasedRegistry(Filter filter, String nameOrder, String diedOnOrder) {
        return "LEFT JOIN burialdocuments B ON D.deceased_id = B.deceased_id " +
                "LEFT JOIN structures S ON S.structure_id = B.structure_id " +
                "LEFT JOIN parcels P ON P.parcel_id = S.parcel_id " +
                "LEFT JOIN cemeteries C ON P.cemetery_id = C.cemetery_id " +
                "LEFT JOIN nocaregiverdocuments AS N ON N.deceased_id = D.deceased_id " +
                "WHERE " +
                    and(
                            allOf(filter.getSearchCriteria()).areAtLeastOnceInAnyOf("first_name", "last_name", "C.name"),
                            "isNull(N.no_caregiver_document_id)"
                    ) +
                " ORDER BY last_name " + nameOrder + ", first_name " + nameOrder + " , died_on " + diedOnOrder + " " +
                limit(filter);
    }

    private String limit(Filter filter) {
        return " LIMIT " + (filter.getPageNo() - 1) + " , " + filter.getPageSize() + " ";
    }

    protected Session getSession() {
        return hibernateTemplate.getSessionFactory().getCurrentSession();
    }

}
