package ro.InnovaTeam.cemeteryApp.eao;

import ro.InnovaTeam.cemeteryApp.model.PrintableContract;
import ro.InnovaTeam.cemeteryApp.model.PrintableStatistics;

/**
 * Created by robert on 1/15/2015.
 */
public interface PrintableEAO {

    public PrintableContract getContract(Integer contractId);

    public PrintableStatistics getStatistics();
}
