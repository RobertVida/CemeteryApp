package ro.InnovaTeam.cemeteryApp.tests.helpers;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ro.InnovaTeam.cemeteryApp.model.Cemetery;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by robert on 11/24/2014.
 */
public class RequestPerformer {

    private MockMvc mvc;
    @Autowired
    private RequestBuilder requestBuilder;
    @Autowired
    private ObjectMapper om;


    public void testUseCase(String file, Class targetClass) throws Exception{
        TestCase test = getTestCase(file);
        testUseCase(test, targetClass);
    }

    public void testSuite(String file, Class targetClass) throws Exception{
        TestSuite suite = getTestSuite(file);
        for(TestCase testCase : suite.getTests()){
            testUseCase(testCase, targetClass);
        }
    }

    private void testUseCase(TestCase test, Class targetClass) throws Exception {
        MvcResult result = perform(test);

        assertThat(result.getResponse().getStatus(), equalTo(test.getResponse().getStatus()));
        if(targetClass.isArray()) {
            assertThat(Arrays.toString((Object[])readJson(result, targetClass)), equalTo(Arrays.toString((Object[])readJson(test, targetClass))));
        } else {
            assertThat(readJson(result, targetClass).toString(), equalTo(readJson(test, targetClass).toString()));
        }
    }

    public TestSuite getTestSuite(String file) throws Exception {
        return readJsonFromFile(file, TestSuite.class);
    }

    public TestCase getTestCase(String file) throws Exception {
        return readJsonFromFile(file, TestCase.class);
    }

    public MvcResult perform(TestCase test) throws Exception {
        requestBuilder.replacePlaceholders(test);
        return mvc.perform(requestBuilder.build(test.getRequest())).andReturn();
    }

    public <T> T readJsonFromFile(String file, Class<T> targetClass) throws Exception{
        return om.readValue(new File(getFile(file)), targetClass);
    }

    public <T> T readJsonFromString(String string, Class<T> targetClass) throws Exception{
        return om.readValue(string, targetClass);
    }

    public <T> T readJsonFromJsonNode(JsonNode json, Class<T> targetClass) throws Exception{
        return om.readValue(json, targetClass);
    }

    public <T> T readJson(TestCase test, Class<T> targetClass) throws Exception {
        return readJsonFromJsonNode(test.getResponse().getData(), targetClass);
    }

    public <T> T readJson(MvcResult result, Class<T> targetClass) throws Exception {
        return readJsonFromString(result.getResponse().getContentAsString(), targetClass);
    }

    public URI getFile(String file) {
        try {
            return this.getClass().getResource(file).toURI();
        } catch (Exception e) {
            throw new RuntimeException("File '" + file + "' not found!\n");
        }
    }

    public MockMvc getMvc() {
        return mvc;
    }

    public void setMvc(MockMvc mvc) {
        this.mvc = mvc;
    }

    public RequestBuilder getRequestBuilder() {
        return requestBuilder;
    }

    public void setRequestBuilder(RequestBuilder requestBuilder) {
        this.requestBuilder = requestBuilder;
    }

    public ObjectMapper getOm() {
        return om;
    }

    public void setOm(ObjectMapper om) {
        this.om = om;
    }
}
