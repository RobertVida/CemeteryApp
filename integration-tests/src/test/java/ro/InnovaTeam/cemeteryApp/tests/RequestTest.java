package ro.InnovaTeam.cemeteryApp.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ro.InnovaTeam.cemeteryApp.ClientDTO;
import ro.InnovaTeam.cemeteryApp.RestingPlaceRequestDTO;
import ro.InnovaTeam.cemeteryApp.RestingPlaceRequestList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by robert on 12/26/2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class RequestTest extends EntityTest {

    @Test
    public void test_Create_Get_Delete_Request() throws Exception {
        //setup
        ClientDTO[] clientDTOs = readJsonFromFile("/clients.json", ClientDTO[].class);
        clientDTOs[0] = client.create(clientDTOs[0]);

        //create
        RestingPlaceRequestDTO[] requestDTOs = readJsonFromFile("/requests.json", RestingPlaceRequestDTO[].class);
        requestDTOs[0].setClientId(clientDTOs[0].getId());
        requestDTOs[0] = request.create(requestDTOs[0]);

        //get
        RestingPlaceRequestDTO requestDTO = request.get(requestDTOs[0]);
        assertThat(compare(requestDTOs[0], requestDTO), equalTo(true));

        //delete
        requestDTO = request.delete(requestDTOs[0]);
        assertThat(compare(requestDTOs[0], requestDTO), equalTo(true));

        //get
        requestDTO = request.get(requestDTOs[0]);
        assertThat(requestDTO, equalTo(null));
    }

    @Test
    public void test_Filter_Request() throws Exception {
        //setup
        ClientDTO[] clientDTOs = setupClients();

        //create
        RestingPlaceRequestDTO[] requestDTOs = setupRequests(clientDTOs);

        //filter
        RestingPlaceRequestList filterResult = request.filter(getFilter());
        assertThat(filterResult.getContent().size(), equalTo(requestDTOs.length));

        filterResult = request.filter(getFilter(1, 20, null, "2"));
        assertThat(filterResult.getContent().size(), equalTo(2));

        filterResult = request.filter(getFilter(1, 20, null, null), "rezolvat");
        assertThat(filterResult.getContent().size(), equalTo(1));
    }

    @Test
    public void test_Update_Request() throws Exception {
        //setup
        ClientDTO[] clientDTOs = readJsonFromFile("/clients.json", ClientDTO[].class);
        clientDTOs[0] = client.create(clientDTOs[0]);

        //create
        RestingPlaceRequestDTO[] requestDTOs = readJsonFromFile("/requests.json", RestingPlaceRequestDTO[].class);
        requestDTOs[0].setClientId(clientDTOs[0].getId());
        requestDTOs[0] = request.create(requestDTOs[0]);

        //get
        RestingPlaceRequestDTO requestDTO = request.get(requestDTOs[0]);
        assertThat(compare(requestDTOs[0], requestDTO), equalTo(true));

        //update
        requestDTOs[0].setStatus("asd");
        requestDTO = request.get(requestDTOs[0]);
        assertThat(compare(requestDTOs[0], requestDTO), equalTo(false));

        request.update(requestDTOs[0]);
        requestDTO = request.get(requestDTOs[0]);
        assertThat(compare(requestDTOs[0], requestDTO), equalTo(true));
    }

    private Boolean compare(RestingPlaceRequestDTO request, RestingPlaceRequestDTO response) {
        return request.getId().equals(response.getId())
                && request.getClientId().equals(response.getClientId())
                && request.getCreatedOn().equals(response.getCreatedOn())
                && request.getInfocetNumber().equals(response.getInfocetNumber())
                && request.getStatus().equals(response.getStatus());
    }
}
