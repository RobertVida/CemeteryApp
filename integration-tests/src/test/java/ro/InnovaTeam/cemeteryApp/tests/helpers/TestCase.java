package ro.InnovaTeam.cemeteryApp.tests.helpers;

/**
 * Created by robert on 11/23/2014.
 */
public class TestCase {

    private Request request;
    private Response response;

    public TestCase() {
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public void copy(TestCase test) {
        this.request = test.request;
        this.response = test.response;
    }

    @Override
    public String toString() {
        return "TestCase{" +
                "request=" + request +
                ", response=" + response +
                '}';
    }
}
