package ro.InnovaTeam.cemeteryApp.tests;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ro.InnovaTeam.cemeteryApp.tests.helpers.Request;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by robert on 11/19/2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/integration-tests.xml"})
public class BaseTest {

    @Resource(name = "createMap")
    private Map<Integer, String> createMap;

    @Resource(name = "deleteMap")
    private Map<Integer, String> deleteMap;

    public static final HttpClient client = HttpClientBuilder.create().build();
    protected static ObjectMapper om = new ObjectMapper();
    private Map<Integer, Integer> entityIds = new HashMap<>();

    public static final Integer CEMETERY = 1;

    @Before
    public void setupEntities() throws Exception{
        for(Integer key : createMap.keySet()){
            entityIds.put(key, getEntityId(create(getEntityFromFile(createMap.get(key), Request.class))));
        }
    }

    @After
    public void cleanEntities() throws Exception{
        for(Integer key : deleteMap.keySet()){
            Request request = new Request();
            request.setUrl(deleteMap.get(key) + entityIds.get(key));
            delete(request);
        }
    }

    private Integer getEntityId(HttpResponse request) throws Exception{
        return Integer.parseInt(getData(request));
    }

    protected String getData(HttpResponse request) throws IOException {
        return new BufferedReader(new InputStreamReader(request.getEntity().getContent())).readLine();
    }

    protected  <T> T getEntityFromFile(String file, Class<T> targetClass) throws Exception{
        return om.readValue(new File(this.getClass().getResource(file).getFile()), targetClass);
    }

    protected  <T> T getEntityFromString(String string, Class<T> targetClass) throws Exception{
        return om.readValue(string, targetClass);
    }

    public HttpResponse create(Request request) throws Exception{
        HttpPut httpRequest = new HttpPut(request.getUrl());
        addHeaders(request, httpRequest);
        httpRequest.setEntity(new StringEntity(request.getData().toString()));

        return client.execute(httpRequest);
    }

    public HttpResponse delete(Request request) throws Exception{
        HttpDelete httpRequest = new HttpDelete(request.getUrl());
        addHeaders(request, httpRequest);

        return client.execute(httpRequest);
    }

    public HttpResponse get(Request request) throws Exception{
        HttpGet httpRequest = new HttpGet(request.getUrl());
        addHeaders(request, httpRequest);

        return client.execute(httpRequest);
    }

    private void addHeaders(Request request, HttpRequest httpRequest) {
        if(request.getHeaders() != null) {
            for (String key : request.getHeaders().keySet()) {
                httpRequest.addHeader(key, request.getHeaders().get(key));
            }
        }
    }

    public Integer getId(Integer entity){
        return entityIds.get(entity);
    }
}
