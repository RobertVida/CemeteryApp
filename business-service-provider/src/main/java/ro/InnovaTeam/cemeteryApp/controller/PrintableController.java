package ro.InnovaTeam.cemeteryApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.InnovaTeam.cemeteryApp.PrintableContractDTO;
import ro.InnovaTeam.cemeteryApp.PrintableStatisticsDTO;
import ro.InnovaTeam.cemeteryApp.service.AuthenticationService;

/**
 * Created by robert on 1/13/2015.
 */
@Controller
public class PrintableController extends ExceptionHandledController {

    public static final String PRINTABLE_CONTRACT_URL = "/printableContract/{contractId}";
    public static final String PRINTABLE_STATISTICS_URL = "/printableStatistics";

    @Autowired
    private AuthenticationService authService;

    @RequestMapping(value = PRINTABLE_CONTRACT_URL, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public PrintableContractDTO getContract(@RequestHeader("Authorization-Token") String token, @PathVariable Integer contractId) {
        isLoggedIn(token);
        PrintableContractDTO contractDTO = new PrintableContractDTO();
        contractDTO.add(PrintableContractDTO.Fields.TITLE, "data de test contract");
        return contractDTO;
    }

    @RequestMapping(value = PRINTABLE_STATISTICS_URL, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public PrintableStatisticsDTO getStatistics(@RequestHeader("Authorization-Token") String token) {
        isLoggedIn(token);
        PrintableStatisticsDTO statisticsDTO = new PrintableStatisticsDTO();
        statisticsDTO.add(PrintableStatisticsDTO.Fields.TITLE, "data de test statistics");
        return statisticsDTO;
    }

    private void isLoggedIn(String token) {
        authService.hasGuestAccess(token);
    }
}
