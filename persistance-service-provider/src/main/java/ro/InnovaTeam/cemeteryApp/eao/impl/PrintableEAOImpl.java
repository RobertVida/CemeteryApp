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
        return null;
    }

    protected Session getSession() {
        return hibernateTemplate.getSessionFactory().getCurrentSession();
    }
}
