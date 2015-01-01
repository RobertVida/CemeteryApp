package ro.InnovaTeam.cemeteryApp.restClient;

import org.springframework.web.client.RestTemplate;
import ro.InnovaTeam.cemeteryApp.*;

import java.util.List;

/**
 * Created by robert on 12/21/2014.
 */
public class GenericRestClient extends BaseRestClient {

    public static <E extends BaseDTO, EL extends BaseList<E>, F extends FilterDTO> List<E> getByFilter(F filter, String endPointURL, Class<EL> listClass) {
        return getJSONRestTemplate().postForObject(endPointURL, filter, listClass).getContent();
    }

    public static <E extends BaseDTO> E findById(Integer id, String endPointURL, Class<E> entityClass) {
        return getJSONRestTemplate().getForObject(endPointURL, entityClass, id);
    }

    public static <E extends BaseDTO> E update(Integer id, String endPointURL, E entity, Class<E> entityClass) {
        RestTemplate restTemplate = getJSONRestTemplate();
        entity.setUserId(getLoggedInUserId());

        return restTemplate.postForObject(endPointURL, entity, entityClass, id);
    }

    public static <E extends BaseDTO> void add(E entity, String endPointURL) {
        RestTemplate restTemplate = getJSONRestTemplate();
        entity.setUserId(getLoggedInUserId());

        restTemplate.put(endPointURL, entity);
    }

    public static void delete(Integer id, String endPointURL) {
        RestTemplate restTemplate = getJSONRestTemplate();

        restTemplate.delete(endPointURL, getLoggedInUserId(), id);
    }

    public static Integer getCount(FilterDTO filterDTO, String endPointURL) {
        return getJSONRestTemplate().postForObject(endPointURL, filterDTO, Integer.class);
    }
}
