package ro.InnovaTeam.cemeteryApp.tests.helpers;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

/**
 * Created by robert on 11/23/2014.
 */
public class RequestBuilder {

    private EntityHolder entities;
    @Autowired
    private ObjectMapper om;

    public RequestBuilder() {
    }

    public RequestBuilder(EntityHolder entities) {
        this.entities = entities;
    }

    public MockHttpServletRequestBuilder build(Request request){
        MockHttpServletRequestBuilder builder = getUrlAndMethod(request);
        builder.headers(getIfHasHeaders(request));
        builder.content(getIfHasContent(request));

        return builder;
    }

    private byte[] getIfHasContent(Request request) {
        return request.getData() != null ? processData(request) : null;
    }

    private HttpHeaders getIfHasHeaders(Request request) {
        return request.getHeaders() != null ? processHeaders(request) : new HttpHeaders();
    }

    private byte[] processData(Request request) {
        return request.getData().toString().getBytes();
    }

    private HttpHeaders processHeaders(Request request) {
        HttpHeaders headers = new HttpHeaders();
        for(String key : request.getHeaders().keySet()){
            headers.add(key, request.getHeaders().get(key));
        }
        return headers;
    }

    private MockHttpServletRequestBuilder getUrlAndMethod(Request request) {
        String url = request.getUrl();

        switch (request.getMethod()){
            case "GET" : return get(url);
            case "PUT" : return put(url);
            case "POST" : return post(url);
            case "DELETE" : return delete(url);
            default: throw new RuntimeException("Invalid Method '" + request.getMethod() + "'");
        }
    }

    public void replacePlaceholders(TestCase testCase) throws Exception{
        String testCaseString = om.writeValueAsString(testCase);

        for(EntityLoader entity : entities.getEntities()){
            String id = "${" + entity.getId() + "}";
            Integer value = entity.getValue();
            testCaseString = testCaseString.replace(id, value != null ? Integer.toString(value) : "");
        }

        testCase.copy(om.readValue(testCaseString, TestCase.class));
    }
}
