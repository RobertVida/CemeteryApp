package ro.InnovaTeam.cemeteryApp.eao.impl;

import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;
import ro.InnovaTeam.cemeteryApp.eao.PrintableEAO;
import ro.InnovaTeam.cemeteryApp.model.PrintableContract;
import ro.InnovaTeam.cemeteryApp.model.PrintableStatistics;

import java.util.List;

/**
 * Created by robert on 1/15/2015.
 */
@Component
public class PrintableEAOImpl implements PrintableEAO {

    @Autowired
    protected HibernateTemplate hibernateTemplate;

    @Override
    public PrintableContract getContract(Integer contractId) {
        return interpretContract(getSession().createSQLQuery(
                " SELECT C.contract_id AS cId, C.signed_on AS cSO, C.expires_on AS cEO, CL.first_name AS clFN, CL.last_name AS clLN, CL.cnp AS clCNP, CL.home_address AS clADRS, R.request_id AS rId, R.created_on AS rCO, R.infocet_number AS rIN, S.structure_id AS sId, (S.width * S.length) AS sSize, P.name AS pN, CEM.name AS cemN " +
                " FROM contracts C " +
                " JOIN restingplacerequests R ON R.request_id = C.request_id " +
                " JOIN clients CL ON CL.client_id = R.client_id " +
                " JOIN structures S ON S.structure_id = C.structure_id " +
                " JOIN parcels P ON P.parcel_id = S.parcel_id " +
                " JOIN cemeteries CEM ON CEM.cemetery_id = P.cemetery_id " +
                " WHERE C.contract_id = " + contractId
        ).list());
    }

    private PrintableContract interpretContract(List<Object[]> obj) {
        if(obj == null || obj.size() == 0) {
            return null;
        }
        Object[] o = obj.get(0);
        PrintableContract contract = new PrintableContract();
        contract.add(PrintableContract.Fields.CONTRACT_ID, o[0]);
        contract.add(PrintableContract.Fields.CONTRACT_SIGNED_ON, o[1]);
        contract.add(PrintableContract.Fields.CONTRACT_EXPIRES_ON, o[2]);
        contract.add(PrintableContract.Fields.CLIENT_FNAME, o[3]);
        contract.add(PrintableContract.Fields.CLIENT_LNAME, o[4]);
        contract.add(PrintableContract.Fields.CLIENT_CNP, o[5]);
        contract.add(PrintableContract.Fields.CLIENT_ADDRESS, o[6]);
        contract.add(PrintableContract.Fields.REQUEST_ID, o[7]);
        contract.add(PrintableContract.Fields.REQUEST_CREATED_ON, o[8]);
        contract.add(PrintableContract.Fields.REQUEST_INFOCET, o[9]);
        contract.add(PrintableContract.Fields.STRUCTURE_ID, o[10]);
        contract.add(PrintableContract.Fields.STRUCTURE_SIZE, o[11]);
        contract.add(PrintableContract.Fields.PARCEL_NAME, o[12]);
        contract.add(PrintableContract.Fields.CEMETERY_NAME, o[13]);

        return contract;
    }

    @Override
    public PrintableStatistics getStatistics() {
        return interpretStatistics(getSession().createSQLQuery(
                " SELECT " +
                        "(SELECT COUNT(*) FROM cemeteries) AS totalCemeteries," +
                        "(SELECT COUNT(*) FROM parcels) AS totalParcels," +
                        "(SELECT AVG(parcelPerCemetry) FROM " +
                        "(SELECT COUNT(*) AS parcelPerCemetry FROM cemeteries C JOIN parcels P ON P.cemetery_id = C.cemetery_id\n GROUP BY C.cemetery_id) AS averageT) " +
                        "AS averageParcelsPerCemetery," +
                        "(SELECT COUNT(*) FROM structures) AS totalStructures," +
                        "(SELECT COUNT(*) FROM structures S WHERE S.type = 'Grave') AS totalGraves," +
                        "(SELECT COUNT(*) FROM structures S WHERE S.type = 'Monument') AS totalMonuments," +
                        "(SELECT AVG(structurePerParcel) FROM " +
                        "(SELECT COUNT(*) AS structurePerParcel FROM parcels P JOIN structures S ON P.parcel_id = S.parcel_id  GROUP BY P.parcel_id) AS averageT) " +
                        "AS averageStructurePerParcel," +
                        "(SELECT COUNT(*) FROM deceased) AS totalDeceased," +
                        "(SELECT COUNT(*) FROM deceased D WHERE D.deceased_id NOT IN(SELECT deceased_id FROM nocaregiverDocuments)) " +
                        "AS deceasedWidthCaregiver," +
                        "(SELECT COUNT(*) FROM deceased D JOIN nocaregiverDocuments N ON N.deceased_id = D.deceased_id) " +
                        "AS deceasedWidthNoCaregiver," +
                        "(SELECT AVG(deceasedPerStructure) FROM " +
                        "(SELECT COUNT(*) AS deceasedPerStructure FROM structures S JOIN burialdocuments B ON S.structure_id = B.structure_id JOIN deceased D ON B.deceased_id = D.deceased_id GROUP BY S.structure_id) AS averageT) " +
                        "AS averageDeceasedPerStructure," +
                        "(SELECT COUNT(*) FROM clients) AS totalClients," +
                        "(SELECT COUNT(*) FROM restingplacerequests) AS totalRequests," +
                        "(SELECT COUNT(*) FROM restingplacerequests R WHERE R.status = 'Favorabil') AS requestsFavorabil," +
                        "(SELECT COUNT(*) FROM restingplacerequests R WHERE R.status = 'Nefavorabil') AS requestsNefavorabil," +
                        "(SELECT COUNT(*) FROM restingplacerequests R WHERE R.status = 'Partial') AS requestsPartial," +
                        "(SELECT COUNT(*) FROM restingplacerequests R WHERE R.status = 'Declinat') AS requestsDeclinat," +
                        "(SELECT COUNT(*) FROM restingplacerequests R WHERE R.status = 'Intern') AS requestsIntern," +
                        "(SELECT COUNT(*) FROM contracts) AS totalContracts," +
                        "(SELECT AVG(contractPerClient) FROM " +
                        "(SELECT COUNT(*) AS contractPerClient FROM clients CL JOIN restingplacerequests R ON R.client_id = CL.client_id JOIN contracts C ON C.request_id = R.request_id GROUP BY CL.client_id) AS averageT) " +
                        "AS averageContractPerClient"
        ).list());
    }

    private PrintableStatistics interpretStatistics(List<Object[]> obj) {
        if(obj == null || obj.size() == 0) {
            return null;
        }
        Object[] o = obj.get(0);
        PrintableStatistics statistics = new PrintableStatistics();
        statistics.add(PrintableStatistics.Fields.TOTAL_CEMETERIES, o[0]);
        statistics.add(PrintableStatistics.Fields.TOTAL_PARCELS, o[1]);
        statistics.add(PrintableStatistics.Fields.AVERAGE_PARCELS_PER_CEMETERIES, o[2]);
        statistics.add(PrintableStatistics.Fields.TOTAL_STRUCTURES, o[3]);
        statistics.add(PrintableStatistics.Fields.TOTAL_GRAVES, o[4]);
        statistics.add(PrintableStatistics.Fields.TOTAL_MONUMENTS, o[5]);
        statistics.add(PrintableStatistics.Fields.AVERAGE_STRUCTURE_PER_PARCEL, o[6]);
        statistics.add(PrintableStatistics.Fields.TOTAL_DECEASED, o[7]);
        statistics.add(PrintableStatistics.Fields.DECEASED_CAREGIVER, o[8]);
        statistics.add(PrintableStatistics.Fields.DECEASED_NO_CAREGIVER, o[9]);
        statistics.add(PrintableStatistics.Fields.AVERAGE_DECEASED_STRUCTURE, o[10]);
        statistics.add(PrintableStatistics.Fields.TOTAL_CLIENTS, o[11]);
        statistics.add(PrintableStatistics.Fields.TOTAL_REQUESTS, o[12]);
        statistics.add(PrintableStatistics.Fields.REQUESTS_FAVORABLE, o[13]);
        statistics.add(PrintableStatistics.Fields.REQUESTS_NOT_FAVORABLE, o[14]);
        statistics.add(PrintableStatistics.Fields.REQUESTS_PARTIAL, o[15]);
        statistics.add(PrintableStatistics.Fields.REQUESTS_DECLINED, o[16]);
        statistics.add(PrintableStatistics.Fields.REQUESTS_INTERN, o[17]);
        statistics.add(PrintableStatistics.Fields.TOTAL_CONTRACTS, o[18]);
        statistics.add(PrintableStatistics.Fields.AVERAGE_CONTRACT_PER_CLIENT, o[19]);

        return statistics;
    }

    protected Session getSession() {
        return hibernateTemplate.getSessionFactory().getCurrentSession();
    }
}
