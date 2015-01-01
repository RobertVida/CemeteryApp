package ro.InnovaTeam.cemeteryApp.restClient;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import ro.InnovaTeam.cemeteryApp.*;

import java.util.List;

/**
 * Created by Catalin Sorecau on 11/30/2014.
 */
public class ClientRestClient extends GenericRestClient {

    public static final String CLIENT_URL = "/client";
    public static final String CLIENTS_URL = "/clients";
    public static final String SPECIFIC_CLIENT_URL = CLIENT_URL + "/{clientId}";
    public static final String SPECIFIC_USER_CLIENT_URL = CLIENT_URL + "/{userId}/{clientId}";

    public static List<ClientDTO> getClientsByFilter(FilterDTO clientFilterDTO) {
        return getByFilter(clientFilterDTO, BASE_URL + CLIENTS_URL, ClientList.class);
    }

    public static ClientDTO findById(@PathVariable Integer clientId) {
        return findById(clientId, BASE_URL + SPECIFIC_CLIENT_URL, ClientDTO.class);
    }

    public static ClientDTO update(@PathVariable Integer clientId, ClientDTO clientDTO) {
        return update(clientId, BASE_URL + SPECIFIC_CLIENT_URL, clientDTO, ClientDTO.class);
    }

    public static void add(ClientDTO clientDTO) {
        add(clientDTO, BASE_URL + CLIENT_URL);
    }

    public static void delete(@PathVariable Integer clientId) {
        delete(clientId,BASE_URL + SPECIFIC_USER_CLIENT_URL);
    }

    public static Integer getClientCount(FilterDTO filterDTO) {
        return getCount(filterDTO, BASE_URL + CLIENTS_URL + "/count");
    }
}
