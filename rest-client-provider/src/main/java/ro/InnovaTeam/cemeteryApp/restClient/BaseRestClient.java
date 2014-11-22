package ro.InnovaTeam.cemeteryApp.restClient;


import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class BaseRestClient {

    public static final String BASE_URL = getBasePath();

    private static String getBasePath() {
        String basePath = "http://localhost:8080/businessServiceProvider";

        return basePath;
    }

    protected static RestTemplate getJSONRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        messageConverters.add(new MappingJacksonHttpMessageConverter());
        restTemplate.setMessageConverters(messageConverters);
        return restTemplate;
    }
}
