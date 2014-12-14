package ro.InnovaTeam.cemeteryApp.restClient;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import ro.InnovaTeam.cemeteryApp.*;

import java.util.List;

/**
 * Created by Catalin Sorecau on 11/30/2014.
 */
public class ClientRestClient extends BaseRestClient {

    public static final String CLIENT_URL = "/client";
    public static final String CLIENTS_URL = "/clients";
    public static final String SPECIFIC_CLIENT_URL = CLIENT_URL + "/{clientId}";
    public static final String SPECIFIC_USER_CLIENT_URL = CLIENT_URL + "/{userId}/{clientId}";

    public static List<ClientDTO> getClientsByFilter(FilterDTO clientFilterDTO) {
        RestTemplate restTemplate = getJSONRestTemplate();
        String endPointURL = BASE_URL + CLIENTS_URL;

        ClientList clientList = restTemplate.postForObject(endPointURL, clientFilterDTO, ClientList.class);

        return clientList.getContent();
    }

    public static ClientDTO findById(@PathVariable Integer clientId) {
        RestTemplate restTemplate = getJSONRestTemplate();
        String endPointURL = BASE_URL + SPECIFIC_CLIENT_URL;

        return restTemplate.getForObject(endPointURL, ClientDTO.class, clientId);
    }

    public static ClientDTO update(@PathVariable Integer clientId, ClientDTO clientDTO) {
        RestTemplate restTemplate = getJSONRestTemplate();
        String endPointURL = BASE_URL + SPECIFIC_CLIENT_URL;
        clientDTO.setUserId(getLoggedInUserId());

        return restTemplate.postForObject(endPointURL, clientDTO, ClientDTO.class, clientId);
    }

    public static void add(ClientDTO clientDTO) {
        RestTemplate restTemplate = getJSONRestTemplate();
        String endPointURL = BASE_URL + CLIENT_URL;

        restTemplate.put(endPointURL, clientDTO);
    }

    public static void delete(@PathVariable Integer clientId) {
        RestTemplate restTemplate = getJSONRestTemplate();
        String endPointURL = BASE_URL + SPECIFIC_USER_CLIENT_URL;

        restTemplate.delete(endPointURL, getLoggedInUserId(), clientId);
    }
}
