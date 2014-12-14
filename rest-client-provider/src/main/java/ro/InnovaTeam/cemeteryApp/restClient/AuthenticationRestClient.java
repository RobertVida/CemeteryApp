package ro.InnovaTeam.cemeteryApp.restClient;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import ro.InnovaTeam.cemeteryApp.UserDTO;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Cata on 12/14/2014.
 */
public class AuthenticationRestClient extends BaseRestClient {

    public static UserDTO checkCredentials(@PathVariable String username, @PathVariable String password) {
        RestTemplate restTemplate = getJSONRestTemplate();
        String endPointURL = BASE_URL + "/login/{username}/{password}";

        Map<String, String> urlVariables = new HashMap<String, String>();
        urlVariables.put("username", username);
        urlVariables.put("password", password);

        return restTemplate.getForObject(endPointURL, UserDTO.class, urlVariables);
    }
}
