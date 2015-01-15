package ro.InnovaTeam.cemeteryApp.restClient;


import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class BaseRestClient {

    public static final String BASE_URL = getBasePath();

    private static String getBasePath() {
        String port = "8080";
        String name = "rest-server";
        String address = "localhost";
        return String.format("http://%s:%s/%s", address, port, name);
    }

    protected static RestTemplate getJSONRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        messageConverters.add(new MappingJacksonHttpMessageConverter());
        restTemplate.setMessageConverters(messageConverters);
        return restTemplate;
    }

    protected static String getLoggedInUserToken() {
        String token = "";
        SecurityContext context = SecurityContextHolder.getContext();
        if (context != null) {
            Authentication authentication = context.getAuthentication();
            if (authentication != null) {
                token = (String) authentication.getCredentials();
            }
        }
        return token;
    }
}
