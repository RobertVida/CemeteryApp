package ro.InnovaTeam.cemeteryApp.tests;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.File;
import java.net.URI;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

/**
 * Created by robert on 11/23/2014.
 */
@ContextConfiguration(locations = {"classpath:business-service-provider-application-context.xml", "classpath:integration-context.xml"})
@WebAppConfiguration
@EnableWebMvc
public class BaseTest {

    @Autowired
    private WebApplicationContext wac;
    @Autowired
    protected ObjectMapper om;

    protected MockMvc mvc;

    @Before
    public void before() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    protected <T> T getResultAsObject(MvcResult mvcResult, Class<T> targetClass) throws Exception {
        return readJsonFromString(mvcResult.getResponse().getContentAsString(), targetClass);
    }

    protected Integer getResultAsInt(MvcResult resultActions) throws Exception {
        return Integer.parseInt(resultActions.getResponse().getContentAsString());
    }

    protected MockHttpServletRequestBuilder getUrlAndMethod(String method, String url) {
        switch (method) {
            case "GET":
                return get(url);
            case "PUT":
                return put(url);
            case "POST":
                return post(url);
            case "DELETE":
                return delete(url);
            default:
                throw new RuntimeException("Invalid Method '" + method + "'");
        }
    }

    protected <T> T readJsonFromFile(String file, Class<T> targetClass) throws Exception {
        return om.readValue(new File(getFile(file)), targetClass);
    }

    protected <T> T readJsonFromString(String string, Class<T> targetClass) throws Exception {
        if (string.equals("")) {
            return null;
        }
        return om.readValue(string, targetClass);
    }

    protected URI getFile(String file) {
        try {
            return this.getClass().getResource(file).toURI();
        } catch (Exception e) {
            throw new RuntimeException("File '" + file + "' not found!\n");
        }
    }
}
