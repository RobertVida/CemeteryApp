package utils;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import ro.InnovaTeam.cemeteryApp.UserDTO;

import java.io.File;
import java.net.URI;
import java.util.*;

/**
 * Created by robert on 1/16/2015.
 */
public class Utils {

    public static ObjectMapper om = new ObjectMapper();
    public static Random random = new Random();

    public static <T> T readJsonFromFile(String file, Class<T> targetClass) throws Exception {
        return om.readValue(new File(getFile(file)), targetClass);
    }

    public static URI getFile(String file) {
        try {
            return Utils.class.getResource(file).toURI();
        } catch (Exception e) {
            throw new RuntimeException("File '" + file + "' not found!\n");
        }
    }

    public static RestTemplate getJSONRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        messageConverters.add(new MappingJacksonHttpMessageConverter());
        restTemplate.setMessageConverters(messageConverters);
        return restTemplate;
    }

    public static HttpEntity<Object> authorizationWrapper(Object entity, final String token) {
        return new HttpEntity<Object>(entity, new LinkedMultiValueMap<String, String>(){{
            add("Content-Type", "application/json");
            add("Authorization-Token", token);
        }});
    }

    public static String getLoggedInUserToken(String username, String password) {
        RestTemplate restTemplate = getJSONRestTemplate();
        String endPointURL = "http://localhost:8080/rest-server/login/{username}/{password}";

        Map<String, String> urlVariables = new HashMap<String, String>();
        urlVariables.put("username", username);
        urlVariables.put("password", password);

        return restTemplate.getForObject(endPointURL, UserDTO.class, urlVariables).getToken();
    }

    public static Integer getRandom(Integer min, Integer max) {
        return random.nextInt((max - min) + 1) + min;
    }

    public static Long getRandom(Long min, Long max) {
        return (random.nextLong() % (max - min)) + min;
    }
}
