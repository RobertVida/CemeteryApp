package ro.InnovaTeam.cemeteryApp.tests;

import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ro.InnovaTeam.cemeteryApp.tests.helpers.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    private EntityHolder entities;
    @Autowired
    protected RequestPerformer requestPerformer;

    protected MockMvc mvc;

    protected static final String GET = "/get";
    protected static final String UPDATE = "/update";

    @Before public void before() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
        requestPerformer.setMvc(mvc);
        initializeEntities();
    }

    private void initializeEntities() throws Exception {
        for (EntityLoader entity : entities.getEntities()) {
            MvcResult result = requestPerformer.perform(requestPerformer.getTestCase(entity.getCreateFile()));
            entity.setValue(Integer.parseInt(result.getResponse().getContentAsString()));
        }
    }

    @After
    public void after() throws Exception {
        removeEntities();
    }

    private void removeEntities() throws Exception {
        List<String> deleteFiles = getDeleteTestSuitesFiles();
        for (String file : deleteFiles) {
            for (TestCase test : requestPerformer.getTestSuite(file).getTests()) {
                requestPerformer.perform(test);
            }
        }
    }

    private List<String> getDeleteTestSuitesFiles() throws Exception {
        List<EntityLoader> array = new ArrayList<EntityLoader>(entities.getEntities());
        Collections.reverse(array);

        List<String> deleteFiles = new ArrayList<String>();
        for (EntityLoader entity : array) {
            if (!deleteFiles.contains(entity.getDeleteFile())) {
                deleteFiles.add(entity.getDeleteFile());
            }
        }
        return deleteFiles;
    }
}
