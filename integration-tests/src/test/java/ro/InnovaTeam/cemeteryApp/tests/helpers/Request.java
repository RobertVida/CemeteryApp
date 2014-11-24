package ro.InnovaTeam.cemeteryApp.tests.helpers;

import org.codehaus.jackson.JsonNode;

import java.util.Map;

/**
 * Created by robert on 11/23/2014.
 */
public class Request {

    private String url;
    private String method;
    private Map<String, String> headers;
    private JsonNode data;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public JsonNode getData() {
        return data;
    }

    public void setData(JsonNode data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Request{" +
                "url='" + url + '\'' +
                ", method='" + method + '\'' +
                ", headers=" + headers +
                ", data=" + data +
                '}';
    }
}
