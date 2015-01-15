package ro.InnovaTeam.cemeteryApp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.InnovaTeam.cemeteryApp.eao.PrintableEAO;
import ro.InnovaTeam.cemeteryApp.model.PrintableContract;
import ro.InnovaTeam.cemeteryApp.model.PrintableStatistics;
import ro.InnovaTeam.cemeteryApp.service.PrintableService;

/**
 * Created by robert on 1/15/2015.
 */
@Transactional
@Service
public class PrintableServiceImpl implements PrintableService {

    @Autowired
    private PrintableEAO printableEAO;

    @Override
    public PrintableContract getContract(Integer contractId) {
        return printableEAO.getContract(contractId);
    }

    @Override
    public PrintableStatistics getStatistics() {
        return printableEAO.getStatistics();
    }
}
