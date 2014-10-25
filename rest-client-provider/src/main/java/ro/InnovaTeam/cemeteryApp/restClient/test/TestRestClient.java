package ro.InnovaTeam.cemeteryApp.restClient.test;

import org.springframework.web.client.RestTemplate;
import ro.InnovaTeam.cemeteryApp.restClient.BaseRestClient;
import ro.InnovaTeam.cemeteryApp.test.TestDTO;

/**
 * Created by Cata on 10/25/2014.
 */
public class TestRestClient extends BaseRestClient {

    public static final String TEST_URL = BASE_URL + "/test";

    public static TestDTO getTestForId(Long testId) {
        RestTemplate restTemplate = getJSONRestTemplate();

        String endpoint =  TEST_URL + "/get/{testId}";

        return restTemplate.getForObject(endpoint, TestDTO.class, testId);
    }
}
