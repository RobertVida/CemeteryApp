package ro.InnovaTeam.cemeteryApp.eao.impl;

import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;
import ro.InnovaTeam.cemeteryApp.eao.SearchEAO;
import ro.InnovaTeam.cemeteryApp.model.Filter;
import ro.InnovaTeam.cemeteryApp.model.registers.BurialRegistryEntry;
import ro.InnovaTeam.cemeteryApp.model.registers.GraveRegistryEntry;
import ro.InnovaTeam.cemeteryApp.model.registers.MonumentRegistryEntry;
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
                "WHERE " + allOf(filter.getSearchCriteria()).areAtLeastOnceInAnyOf("first_name", "last_name", "religion", "p.name");
    }

    public  String graveRegister(Filter filter) {
        return "INNER JOIN parcels P ON P.cemetery_id = C.cemetery_id " +
                "INNER JOIN structures S ON S.parcel_id = P.parcel_id AND S.type = \"Grave\" " +
                "LEFT JOIN contracts CON ON CON.structure_id = S.structure_id " +
                "LEFT JOIN restingplacerequests R ON R.request_id = CON.request_id " +
                "LEFT JOIN clients CL ON CL.client_id= R.client_id " +
                "LEFT JOIN burialdocuments B ON B.structure_id = S.structure_id " +
                "LEFT JOIN deceased D ON D.deceased_id = B.deceased_id " +
                "WHERE " + allOf(filter.getSearchCriteria()).areAtLeastOnceInAnyOf("C.name", "P.name", "CL.first_name", "CL.last_name", "CL.home_address", "D.first_name", "D.last_name");
    }

    public  String monumentRegister(Filter filter) {
        return "INNER JOIN parcels P ON P.cemetery_id = C.cemetery_id " +
                "INNER JOIN structures S ON S.parcel_id = P.parcel_id AND S.type = \"Monument\" " +
                "LEFT JOIN contracts CON ON CON.structure_id = S.structure_id " +
                "LEFT JOIN restingplacerequests R ON R.request_id = CON.request_id " +
                "LEFT JOIN clients CL ON CL.client_id= R.client_id " +
                "LEFT JOIN burialdocuments B ON B.structure_id = S.structure_id " +
                "LEFT JOIN deceased D ON D.deceased_id = B.deceased_id " +
                "WHERE " + allOf(filter.getSearchCriteria()).areAtLeastOnceInAnyOf("C.name", "P.name", "CL.first_name", "CL.last_name", "CL.home_address", "D.first_name", "D.last_name");
    }

    protected Session getSession() {
        return hibernateTemplate.getSessionFactory().getCurrentSession();
    }

}
