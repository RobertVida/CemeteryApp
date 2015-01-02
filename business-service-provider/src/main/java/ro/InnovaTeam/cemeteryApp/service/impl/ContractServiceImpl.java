package ro.InnovaTeam.cemeteryApp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.InnovaTeam.cemeteryApp.eao.ContractEAO;
import ro.InnovaTeam.cemeteryApp.model.Contract;
import ro.InnovaTeam.cemeteryApp.model.Filter;
import ro.InnovaTeam.cemeteryApp.service.ContractService;
import ro.InnovaTeam.cemeteryApp.service.LogEntryService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by robert on 1/2/2015.
 */
@Transactional
@Service
public class ContractServiceImpl extends LoggableService<Contract, ContractEAO, LogEntryService> implements ContractService {

    @Autowired
    private ContractEAO contractEAO;
    @Autowired
    private LogEntryService logService;

    @Override
    public Integer create(Contract contract) {
        contract.setExpiresOn(calculateExpireDate(contract.getSignedOn()));
        return loggedCreate(contractEAO, logService, contract);
    }

    @Override
    public Contract delete(Integer userId, Integer id) {
        return loggedDelete(contractEAO, logService, userId, id);
    }

    @Override
    public Contract update(Contract contract) {
        if(contract.getSignedOn().compareTo(contract.getUpdatedOn()) > 0){
            throw new RuntimeException("some exception");
        }
        if(contract.getUpdatedOn().compareTo(contract.getExpiresOn()) > 0){
            throw new RuntimeException("some exception2");
        }
        return loggedUpdate(contractEAO, logService, contract);
    }

    @Override
    public Contract findById(Integer id) {
        return contractEAO.findById(id);
    }

    @Override
    public List<Contract> findByFilter(Filter filter) {
        return contractEAO.findByFilter(filter);
    }

    @Override
    public Integer countByFilter(Filter filter) {
        return contractEAO.countByFilter(filter);
    }

    private Date calculateExpireDate(Date signedOn) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(signedOn);
        calendar.add(Calendar.YEAR, 20);
        return calendar.getTime();
    }
}
