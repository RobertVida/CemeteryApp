package ro.InnovaTeam.cemeteryApp.tests.helpers;

import org.codehaus.jackson.JsonNode;

import java.util.Map;

/**
 * Created by robert on 11/23/2014.
 */
public class Response {

    private Integer status;
    private Map<String, Object> headers;
    private JsonNode data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Map<String, Object> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, Object> headers) {
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
        return "Response{" +
                "status=" + status +
                ", headers=" + headers +
                ", data=" + data +
                '}';
    }
}
