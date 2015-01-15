package ro.InnovaTeam.cemeteryApp.service;

import ro.InnovaTeam.cemeteryApp.model.PrintableContract;
import ro.InnovaTeam.cemeteryApp.model.PrintableStatistics;

/**
 * Created by robert on 1/15/2015.
 */
public interface PrintableService {

    public PrintableContract getContract(Integer contractId);

    public PrintableStatistics getStatistics();
}
