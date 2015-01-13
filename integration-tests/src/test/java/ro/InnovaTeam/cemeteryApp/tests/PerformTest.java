package ro.InnovaTeam.cemeteryApp.tests;

import org.junit.Before;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import ro.InnovaTeam.cemeteryApp.FilterDTO;
import ro.InnovaTeam.cemeteryApp.UserDTO;

/**
 * Created by robert on 12/24/2014.
 */
public class PerformTest extends BaseTest {

    protected static HttpHeaders dataHeaders = new HttpHeaders() {{
        add("Content-Type", "application/json");
    }};

    protected static HttpHeaders authHeaders = new HttpHeaders();

    @Before
    public void login() throws Exception {
        if(dataHeaders.get("Authorization-Token") == null) {
            UserDTO user = getResultAsObject(mvc.perform(getRequest("/login/admin/admin")).andReturn(), UserDTO.class);
            dataHeaders.add("Authorization-Token", user.getToken());
            authHeaders.add("Authorization-Token", user.getToken());
        }
    }

    protected ResultActions performGet(String url) throws Exception {
        return mvc.perform(getRequest(url));
    }

    protected ResultActions performCreate(String url, Object object) throws Exception {
        return mvc.perform(createRequest(url, om.writeValueAsBytes(object)));
    }

    protected ResultActions performUpdate(String url, Object object) throws Exception {
        return mvc.perform(updateRequest(url, om.writeValueAsBytes(object)));
    }

    protected ResultActions performDelete(String url) throws Exception {
        return mvc.perform(deleteRequest(url));
    }

    protected ResultActions performFilter(String url, FilterDTO filterDTO) throws Exception {
        return mvc.perform(filterRequest(url, om.writeValueAsBytes(filterDTO)));
    }

    protected ResultActions performFilter(String url) throws Exception {
        return mvc.perform(filterRequest(url));
    }

    protected ResultActions performCount(String url, FilterDTO filterDTO) throws Exception {
        return mvc.perform(countRequest(url, om.writeValueAsBytes(filterDTO)));
    }

    protected RequestBuilder getRequest(String url) {
        MockHttpServletRequestBuilder builder = getUrlAndMethod("GET", url);
        builder.headers(authHeaders);

        return builder;
    }

    protected RequestBuilder createRequest(String url, byte[] data) {
        MockHttpServletRequestBuilder builder = getUrlAndMethod("PUT", url);
        builder.headers(dataHeaders);
        builder.content(data);

        return builder;
    }

    private RequestBuilder updateRequest(String url, byte[] data) {
        MockHttpServletRequestBuilder builder = getUrlAndMethod("POST", url);
        builder.headers(dataHeaders);
        builder.content(data);

        return builder;
    }

    private RequestBuilder deleteRequest(String url) {
        MockHttpServletRequestBuilder builder = getUrlAndMethod("DELETE", url);
        builder.headers(authHeaders);

        return builder;
    }

    private RequestBuilder filterRequest(String url, byte[] data) {
        MockHttpServletRequestBuilder builder = getUrlAndMethod("POST", url);
        builder.headers(dataHeaders);
        builder.content(data);

        return builder;
    }

    private RequestBuilder countRequest(String url, byte[] data) {
        MockHttpServletRequestBuilder builder = getUrlAndMethod("POST", url);
        builder.headers(dataHeaders);
        builder.content(data);

        return builder;
    }

    private RequestBuilder filterRequest(String url) {
        MockHttpServletRequestBuilder builder = getUrlAndMethod("GET", url);
        builder.headers(authHeaders);

        return builder;
    }
}
