package ro.InnovaTeam.cemeteryApp.tests;

import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import ro.InnovaTeam.cemeteryApp.FilterDTO;

/**
 * Created by robert on 12/24/2014.
 */
public class PerformTest extends BaseTest {

    protected static final HttpHeaders dataHeaders = new HttpHeaders() {{
        add("Content-Type", "application/json");
    }};

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
        return getUrlAndMethod("GET", url);
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
        return getUrlAndMethod("DELETE", url);
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
        return getUrlAndMethod("GET", url);
    }
}
