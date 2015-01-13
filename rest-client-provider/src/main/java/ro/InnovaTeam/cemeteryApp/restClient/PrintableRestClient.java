package ro.InnovaTeam.cemeteryApp.restClient;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import ro.InnovaTeam.cemeteryApp.PrintableContractDTO;
import ro.InnovaTeam.cemeteryApp.PrintableStatisticsDTO;

/**
 * Created by Catalin Sorecau on 1/13/2015.
 */
public class PrintableRestClient extends GenericRestClient {

    public static final String PRINTABLE_CONTRACT_URL = "/printableContract/{contractId}";
    public static final String PRINTABLE_STATISTICS_URL = "/printableStatistics";

    public static PrintableStatisticsDTO getStatistics() {
        return getJSONRestTemplate().exchange(BASE_URL + PRINTABLE_STATISTICS_URL, HttpMethod.GET, authorizationWrapper(null), PrintableStatisticsDTO.class).getBody();
    }

    public static PrintableContractDTO getContractById(Integer contractId) {
        return findById(contractId, BASE_URL + PRINTABLE_CONTRACT_URL, PrintableContractDTO.class);
    }

    private static HttpEntity<Object> authorizationWrapper(Object entity) {
        return new HttpEntity<Object>(entity, new LinkedMultiValueMap<String, String>(){{
            add("Content-Type", "application/json");
            add("Authorization-Token", getLoggedInUserToken());
        }});
    }
}
