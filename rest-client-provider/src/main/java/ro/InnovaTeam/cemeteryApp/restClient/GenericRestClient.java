package ro.InnovaTeam.cemeteryApp.restClient;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import ro.InnovaTeam.cemeteryApp.*;

import java.util.List;

/**
 * Created by robert on 12/21/2014.
 */
public class GenericRestClient extends BaseRestClient {

    public static <E extends BaseDTO, EL extends BaseList<E>, F extends FilterDTO> List<E> getByFilter(F filter, String endPointURL, Class<EL> listClass) {
        return getJSONRestTemplate().postForObject(endPointURL, authorizationWrapper(filter), listClass).getContent();
    }

    public static <E extends BaseDTO> E findById(Integer id, String endPointURL, Class<E> entityClass) {
        return getJSONRestTemplate().exchange(endPointURL, HttpMethod.GET, authorizationWrapper(entityClass), entityClass, id).getBody();
    }

    public static <E extends BaseDTO> E update(Integer id, String endPointURL, E entity, Class<E> entityClass) {
        RestTemplate restTemplate = getJSONRestTemplate();

        return restTemplate.postForObject(endPointURL, authorizationWrapper(entity), entityClass, id);
    }

    public static <E extends BaseDTO> void add(E entity, String endPointURL) {
        RestTemplate restTemplate = getJSONRestTemplate();

        restTemplate.put(endPointURL, authorizationWrapper(entity));
    }

    public static void delete(Integer id, String endPointURL) {
        RestTemplate restTemplate = getJSONRestTemplate();

        restTemplate.exchange(endPointURL, HttpMethod.DELETE, authorizationWrapper(null), Void.class, id).getBody();
    }

    public static Integer getCount(FilterDTO filterDTO, String endPointURL) {
        return getJSONRestTemplate().postForObject(endPointURL, authorizationWrapper(filterDTO), Integer.class);
    }

    private static HttpEntity<Object> authorizationWrapper(Object entity) {
        return new HttpEntity<Object>(entity, new LinkedMultiValueMap<String, String>(){{
            add("Content-Type", "application/json");
            add("Authorization-Token", getLoggedInUserToken());
        }});
    }
}
