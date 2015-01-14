package ro.InnovaTeam.cemeteryApp.tests;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ro.InnovaTeam.cemeteryApp.*;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static ro.InnovaTeam.cemeteryApp.ValidationErrors.*;

/**
 * Created by robert on 1/10/2015.
 */
public class ExceptionHandlerTest {

    private ObjectMapper om = new ObjectMapper();

    @Test
    public void test_Cemetery_Create() throws Exception {
        RestTemplate restTemplate = getJSONRestTemplate();

        try {
            restTemplate.put("http://localhost:8080/rest-server/cemetery", authorizationWrapper(new CemeteryDTO()));
        } catch (HttpClientErrorException e) {
            ErrorDTO error = om.readValue(e.getResponseBodyAsString(), ErrorDTO.class);
            assertThat(error.getErrors().size(), equalTo(2));
            assertThat(error.getErrors().contains(CEMETERY_NAME_BLANK), equalTo(true));
            assertThat(error.getErrors().contains(CEMETERY_ADDRESS_BLANK), equalTo(true));
        }
    }

    @Test
    public void test_Cemetery_Update() throws Exception {
        RestTemplate restTemplate = getJSONRestTemplate();

        try {
            restTemplate.postForObject("http://localhost:8080/rest-server/cemetery/{cemeteryId}", authorizationWrapper(new CemeteryDTO()), CemeteryDTO.class, 1);
        } catch (HttpClientErrorException e) {
            ErrorDTO error = om.readValue(e.getResponseBodyAsString(), ErrorDTO.class);
            assertThat(error.getErrors().size(), equalTo(2));
            assertThat(error.getErrors().contains(CEMETERY_NAME_BLANK), equalTo(true));
            assertThat(error.getErrors().contains(CEMETERY_ADDRESS_BLANK), equalTo(true));
        }
    }

    @Test
    public void test_Parcel_Create() throws Exception {
        RestTemplate restTemplate = getJSONRestTemplate();

        try {
            restTemplate.put("http://localhost:8080/rest-server/parcel", authorizationWrapper(new ParcelDTO()));
        } catch (HttpClientErrorException e) {
            ErrorDTO error = om.readValue(e.getResponseBodyAsString(), ErrorDTO.class);
            assertThat(error.getErrors().size(), equalTo(2));
            assertThat(error.getErrors().contains(PARCEL_NAME_BLANK), equalTo(true));
            assertThat(error.getErrors().contains(PARCEL_CEMETERY_ID_INVALID), equalTo(true));
        }

        try {
            restTemplate.put("http://localhost:8080/rest-server/parcel", authorizationWrapper(new ParcelDTO(){{setName("asdasd");}}));
        } catch (HttpClientErrorException e) {
            ErrorDTO error = om.readValue(e.getResponseBodyAsString(), ErrorDTO.class);
            assertThat(error.getErrors().size(), equalTo(1));
            assertThat(error.getErrors().contains(PARCEL_CEMETERY_ID_INVALID), equalTo(true));
        }
    }

    @Test
    public void test_Parcel_Update() throws Exception {
        RestTemplate restTemplate = getJSONRestTemplate();

        try {
            restTemplate.postForObject("http://localhost:8080/rest-server/parcel/{parcelId}", authorizationWrapper(new ParcelDTO()), ParcelDTO.class, 1);
        } catch (HttpClientErrorException e) {
            ErrorDTO error = om.readValue(e.getResponseBodyAsString(), ErrorDTO.class);
            assertThat(error.getErrors().size(), equalTo(2));
            assertThat(error.getErrors().contains(PARCEL_NAME_BLANK), equalTo(true));
            assertThat(error.getErrors().contains(PARCEL_CEMETERY_ID_INVALID), equalTo(true));
        }
    }

    @Test
    public void test_Grave_Create() throws Exception {
        RestTemplate restTemplate = getJSONRestTemplate();

        try {
            restTemplate.put("http://localhost:8080/rest-server/grave", authorizationWrapper(new GraveDTO()));
        } catch (HttpClientErrorException e) {
            ErrorDTO error = om.readValue(e.getResponseBodyAsString(), ErrorDTO.class);
            assertThat(error.getErrors().size(), equalTo(5));
            assertThat(error.getErrors().contains(GRAVE_PARCEL_ID_INVALID), equalTo(true));
            assertThat(error.getErrors().contains(GRAVE_TYPE_BLANK), equalTo(true));
            assertThat(error.getErrors().contains(GRAVE_CREATED_ON_INVALID), equalTo(true));
            assertThat(error.getErrors().contains(GRAVE_LENGTH_INVALID), equalTo(true));
            assertThat(error.getErrors().contains(GRAVE_WIDTH_INVALID), equalTo(true));
        }
    }

    @Test
    public void test_Grave_Update() throws Exception {
        RestTemplate restTemplate = getJSONRestTemplate();

        try {
            restTemplate.postForObject("http://localhost:8080/rest-server/grave/{graveId}", authorizationWrapper(new GraveDTO()), GraveDTO.class, 1);
        } catch (HttpClientErrorException e) {
            ErrorDTO error = om.readValue(e.getResponseBodyAsString(), ErrorDTO.class);
            assertThat(error.getErrors().size(), equalTo(5));
            assertThat(error.getErrors().contains(GRAVE_PARCEL_ID_INVALID), equalTo(true));
            assertThat(error.getErrors().contains(GRAVE_TYPE_BLANK), equalTo(true));
            assertThat(error.getErrors().contains(GRAVE_CREATED_ON_INVALID), equalTo(true));
            assertThat(error.getErrors().contains(GRAVE_LENGTH_INVALID), equalTo(true));
            assertThat(error.getErrors().contains(GRAVE_WIDTH_INVALID), equalTo(true));
        }
    }

    @Test
    public void test_Monument_Create() throws Exception {
        RestTemplate restTemplate = getJSONRestTemplate();

        try {
            restTemplate.put("http://localhost:8080/rest-server/monument", authorizationWrapper(new MonumentDTO()));
        } catch (HttpClientErrorException e) {
            ErrorDTO error = om.readValue(e.getResponseBodyAsString(), ErrorDTO.class);
            assertThat(error.getErrors().size(), equalTo(7));
            assertThat(error.getErrors().contains(MONUMENT_PARCEL_ID_INVALID), equalTo(true));
            assertThat(error.getErrors().contains(MONUMENT_TYPE_BLANK), equalTo(true));
            assertThat(error.getErrors().contains(MONUMENT_CREATED_ON_INVALID), equalTo(true));
            assertThat(error.getErrors().contains(MONUMENT_LENGTH_INVALID), equalTo(true));
            assertThat(error.getErrors().contains(MONUMENT_WIDTH_INVALID), equalTo(true));
            assertThat(error.getErrors().contains(MONUMENT_NAME_BLANK), equalTo(true));
            assertThat(error.getErrors().contains(MONUMENT_DESCRIPTION_BLANK), equalTo(true));
        }
    }

    @Test
    public void test_Monument_Update() throws Exception {
        RestTemplate restTemplate = getJSONRestTemplate();

        try {
            restTemplate.postForObject("http://localhost:8080/rest-server/monument/{monumentId}", authorizationWrapper(new MonumentDTO()), MonumentDTO.class, 1);
        } catch (HttpClientErrorException e) {
            ErrorDTO error = om.readValue(e.getResponseBodyAsString(), ErrorDTO.class);
            assertThat(error.getErrors().size(), equalTo(7));
            assertThat(error.getErrors().contains(MONUMENT_PARCEL_ID_INVALID), equalTo(true));
            assertThat(error.getErrors().contains(MONUMENT_TYPE_BLANK), equalTo(true));
            assertThat(error.getErrors().contains(MONUMENT_CREATED_ON_INVALID), equalTo(true));
            assertThat(error.getErrors().contains(MONUMENT_LENGTH_INVALID), equalTo(true));
            assertThat(error.getErrors().contains(MONUMENT_WIDTH_INVALID), equalTo(true));
            assertThat(error.getErrors().contains(MONUMENT_NAME_BLANK), equalTo(true));
            assertThat(error.getErrors().contains(MONUMENT_DESCRIPTION_BLANK), equalTo(true));
        }
    }

    @Test
    public void test_Contract_Create() throws Exception {
        RestTemplate restTemplate = getJSONRestTemplate();

        try {
            restTemplate.put("http://localhost:8080/rest-server/contract", authorizationWrapper(new ContractDTO()));
        } catch (HttpClientErrorException e) {
            ErrorDTO error = om.readValue(e.getResponseBodyAsString(), ErrorDTO.class);
            assertThat(error.getErrors().size(), equalTo(3));
            assertThat(error.getErrors().contains(CONTRACT_STRUCTURE_ID_NULL), equalTo(true));
            assertThat(error.getErrors().contains(CONTRACT_REQUEST_ID_NULL), equalTo(true));
            assertThat(error.getErrors().contains(CONTRACT_SIGNED_ON_NULL), equalTo(true));
        }
    }

    @Test
    public void test_Contract_Update() throws Exception {
        RestTemplate restTemplate = getJSONRestTemplate();

        ContractDTO c = new ContractDTO();
        c.setSignedOn(new Date());
        try {
            restTemplate.postForObject("http://localhost:8080/rest-server/contract/{contractId}", authorizationWrapper(c), ContractDTO.class, 1);
        } catch (HttpClientErrorException e) {
            ErrorDTO error = om.readValue(e.getResponseBodyAsString(), ErrorDTO.class);
            assertThat(error.getErrors().size(), equalTo(2));
            assertThat(error.getErrors().contains(CONTRACT_STRUCTURE_ID_NULL), equalTo(true));
            assertThat(error.getErrors().contains(CONTRACT_REQUEST_ID_NULL), equalTo(true));
        }

        c.setUpdatedOn(new Date(new Date().getTime()-100000));
        try {
            restTemplate.postForObject("http://localhost:8080/rest-server/contract/{contractId}", authorizationWrapper(c), ContractDTO.class, 1);
        } catch (HttpClientErrorException e) {
            ErrorDTO error = om.readValue(e.getResponseBodyAsString(), ErrorDTO.class);
            assertThat(error.getErrors().size(), equalTo(4));
            assertThat(error.getErrors().contains(CONTRACT_STRUCTURE_ID_NULL), equalTo(true));
            assertThat(error.getErrors().contains(CONTRACT_REQUEST_ID_NULL), equalTo(true));
            assertThat(error.getErrors().contains(CONTRACT_UPDATED_INVALID), equalTo(true));
            assertThat(error.getErrors().contains(CONTRACT_EXPIRES_INVALID), equalTo(true));
        }

        c.setUpdatedOn(new Date(new Date().getTime() + 10000));
        c.setExpiresOn(new Date(new Date().getTime()-100000));
        try {
            restTemplate.postForObject("http://localhost:8080/rest-server/contract/{contractId}", authorizationWrapper(c), ContractDTO.class, 1);
        } catch (HttpClientErrorException e) {
            ErrorDTO error = om.readValue(e.getResponseBodyAsString(), ErrorDTO.class);
            assertThat(error.getErrors().size(), equalTo(3));
            assertThat(error.getErrors().contains(CONTRACT_STRUCTURE_ID_NULL), equalTo(true));
            assertThat(error.getErrors().contains(CONTRACT_REQUEST_ID_NULL), equalTo(true));
            assertThat(error.getErrors().contains(CONTRACT_EXPIRES_INVALID), equalTo(true));
        }
    }

    @Test
    public void test_Deceased_Create() throws Exception {
        RestTemplate restTemplate = getJSONRestTemplate();

        try {
            restTemplate.put("http://localhost:8080/rest-server/deceased", authorizationWrapper(new DeceasedDTO()));
        } catch (HttpClientErrorException e) {
            ErrorDTO error = om.readValue(e.getResponseBodyAsString(), ErrorDTO.class);
            assertThat(error.getErrors().size(), equalTo(7));
            assertThat(error.getErrors().contains(DECEASED_FIRST_NAME_BLANK), equalTo(true));
            assertThat(error.getErrors().contains(DECEASED_LAST_NAME_BLANK), equalTo(true));
            assertThat(error.getErrors().contains(DECEASED_CNP_INVALID), equalTo(true));
            assertThat(error.getErrors().contains(DECEASED_RELIGION_BLANK), equalTo(true));
            assertThat(error.getErrors().contains(DECEASED_DIED_ON_INVALID), equalTo(true));
            assertThat(error.getErrors().contains(DECEASED_STRUCTURE_ID_INVALID), equalTo(true));
            assertThat(error.getErrors().contains(DECEASED_BURIAL_ON_INVALID), equalTo(true));
        }
    }

    @Test
    public void test_Deceased_Update() throws Exception {
        RestTemplate restTemplate = getJSONRestTemplate();

        try {
            restTemplate.postForObject("http://localhost:8080/rest-server/deceased/{deceasedId}", authorizationWrapper(new DeceasedDTO()), DeceasedDTO.class, 1);
        } catch (HttpClientErrorException e) {
            ErrorDTO error = om.readValue(e.getResponseBodyAsString(), ErrorDTO.class);
            assertThat(error.getErrors().size(), equalTo(7));
            assertThat(error.getErrors().contains(DECEASED_FIRST_NAME_BLANK), equalTo(true));
            assertThat(error.getErrors().contains(DECEASED_LAST_NAME_BLANK), equalTo(true));
            assertThat(error.getErrors().contains(DECEASED_CNP_INVALID), equalTo(true));
            assertThat(error.getErrors().contains(DECEASED_RELIGION_BLANK), equalTo(true));
            assertThat(error.getErrors().contains(DECEASED_DIED_ON_INVALID), equalTo(true));
            assertThat(error.getErrors().contains(DECEASED_STRUCTURE_ID_INVALID), equalTo(true));
            assertThat(error.getErrors().contains(DECEASED_BURIAL_ON_INVALID), equalTo(true));
        }
    }

    @Test
    public void test_Client_Create() throws Exception {
        RestTemplate restTemplate = getJSONRestTemplate();

        try {
            restTemplate.put("http://localhost:8080/rest-server/client", authorizationWrapper(new ClientDTO()));
        } catch (HttpClientErrorException e) {
            ErrorDTO error = om.readValue(e.getResponseBodyAsString(), ErrorDTO.class);
            assertThat(error.getErrors().size(), equalTo(5));
            assertThat(error.getErrors().contains(CLIENT_FIRST_NAME_BLANK), equalTo(true));
            assertThat(error.getErrors().contains(CLIENT_LAST_NAME_BLANK), equalTo(true));
            assertThat(error.getErrors().contains(CLIENT_CNP_INVALID), equalTo(true));
            assertThat(error.getErrors().contains(CLIENT_PHONE_NUMBER_INVALID), equalTo(true));
            assertThat(error.getErrors().contains(CLIENT_ADDRESS_BLANK), equalTo(true));
        }
    }

    @Test
    public void test_Client_Update() throws Exception {
        RestTemplate restTemplate = getJSONRestTemplate();

        try {
            restTemplate.postForObject("http://localhost:8080/rest-server/client/{clientId}", authorizationWrapper(new ClientDTO()), ClientDTO.class, 1);
        } catch (HttpClientErrorException e) {
            ErrorDTO error = om.readValue(e.getResponseBodyAsString(), ErrorDTO.class);
            assertThat(error.getErrors().size(), equalTo(5));
            assertThat(error.getErrors().contains(CLIENT_FIRST_NAME_BLANK), equalTo(true));
            assertThat(error.getErrors().contains(CLIENT_LAST_NAME_BLANK), equalTo(true));
            assertThat(error.getErrors().contains(CLIENT_CNP_INVALID), equalTo(true));
            assertThat(error.getErrors().contains(CLIENT_PHONE_NUMBER_INVALID), equalTo(true));
            assertThat(error.getErrors().contains(CLIENT_ADDRESS_BLANK), equalTo(true));
        }
    }

    @Test
    public void test_Request_Create() throws Exception {
        RestTemplate restTemplate = getJSONRestTemplate();

        try {
            restTemplate.put("http://localhost:8080/rest-server/request", authorizationWrapper(new RestingPlaceRequestDTO()));
        } catch (HttpClientErrorException e) {
            ErrorDTO error = om.readValue(e.getResponseBodyAsString(), ErrorDTO.class);
            assertThat(error.getErrors().size(), equalTo(4));
            assertThat(error.getErrors().contains(REQUEST_CLIENT_ID_INVALID), equalTo(true));
            assertThat(error.getErrors().contains(REQUEST_CREATED_ON_INVALID), equalTo(true));
            assertThat(error.getErrors().contains(REQUEST_INFOCET_NUMBER_INVALID), equalTo(true));
            assertThat(error.getErrors().contains(REQUEST_STATUS_BLANK), equalTo(true));
        }
    }

    @Test
    public void test_Request_Update() throws Exception {
        RestTemplate restTemplate = getJSONRestTemplate();

        try {
            restTemplate.postForObject("http://localhost:8080/rest-server/request/{requestId}", authorizationWrapper(new RestingPlaceRequestDTO()), RestingPlaceRequestDTO.class, 1);
        } catch (HttpClientErrorException e) {
            ErrorDTO error = om.readValue(e.getResponseBodyAsString(), ErrorDTO.class);
            assertThat(error.getErrors().size(), equalTo(4));
            assertThat(error.getErrors().contains(REQUEST_CLIENT_ID_INVALID), equalTo(true));
            assertThat(error.getErrors().contains(REQUEST_CREATED_ON_INVALID), equalTo(true));
            assertThat(error.getErrors().contains(REQUEST_INFOCET_NUMBER_INVALID), equalTo(true));
            assertThat(error.getErrors().contains(REQUEST_STATUS_BLANK), equalTo(true));
        }
    }

    @Test
    public void test_History_Create() throws Exception {
        RestTemplate restTemplate = getJSONRestTemplate();

        try {
            restTemplate.put("http://localhost:8080/rest-server/structureHistory", authorizationWrapper(new StructureHistoryEntryDTO()));
        } catch (HttpClientErrorException e) {
            ErrorDTO error = om.readValue(e.getResponseBodyAsString(), ErrorDTO.class);
            assertThat(error.getErrors().size(), equalTo(3));
            assertThat(error.getErrors().contains(STRUCTURE_HISTORY_STRUCTURE_ID_INVALID), equalTo(true));
            assertThat(error.getErrors().contains(STRUCTURE_HISTORY_DESCRIPTION_BLANK), equalTo(true));
            assertThat(error.getErrors().contains(STRUCTURE_HISTORY_DATE_INVALID), equalTo(true));
        }
    }

    @Test
    public void test_History_Update() throws Exception {
        RestTemplate restTemplate = getJSONRestTemplate();

        try {
            restTemplate.postForObject("http://localhost:8080/rest-server/structureHistory/{structureHistoryId}", authorizationWrapper(new StructureHistoryEntryDTO()), StructureHistoryEntryDTO.class, 1);
        } catch (HttpClientErrorException e) {
            ErrorDTO error = om.readValue(e.getResponseBodyAsString(), ErrorDTO.class);
            assertThat(error.getErrors().size(), equalTo(3));
            assertThat(error.getErrors().contains(STRUCTURE_HISTORY_STRUCTURE_ID_INVALID), equalTo(true));
            assertThat(error.getErrors().contains(STRUCTURE_HISTORY_DESCRIPTION_BLANK), equalTo(true));
            assertThat(error.getErrors().contains(STRUCTURE_HISTORY_DATE_INVALID), equalTo(true));
        }
    }

    protected RestTemplate getJSONRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        messageConverters.add(new MappingJacksonHttpMessageConverter());
        restTemplate.setMessageConverters(messageConverters);
        return restTemplate;
    }

    private static String token;

    private HttpEntity<Object> authorizationWrapper(Object entity) {
        return new HttpEntity<Object>(entity, new LinkedMultiValueMap<String, String>(){{
            add("Content-Type", "application/json");
            add("Authorization-Token", token != null ? token : getLoggedInUserToken());
        }});
    }

    private String getLoggedInUserToken() {
        RestTemplate restTemplate = getJSONRestTemplate();
        String endPointURL = "http://localhost:8080/rest-server/login/{username}/{password}";

        Map<String, String> urlVariables = new HashMap<String, String>();
        urlVariables.put("username", "admin");
        urlVariables.put("password", "admin");

        return restTemplate.getForObject(endPointURL, UserDTO.class, urlVariables).getToken();
    }
}
