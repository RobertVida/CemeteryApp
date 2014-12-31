package ro.InnovaTeam.cemeteryApp.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ro.InnovaTeam.cemeteryApp.ClientDTO;
import ro.InnovaTeam.cemeteryApp.ClientList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by robert on 12/26/2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ClientTest extends EntityTest {

    @Test
    public void test_Create_Get_Delete_Client() throws Exception {
        //create
        ClientDTO[] clientDTOs = readJsonFromFile("/clients.json", ClientDTO[].class);
        clientDTOs[0] = client.create(clientDTOs[0]);
        assertThat(client.count(getFilter()), equalTo(1));

        //get
        ClientDTO clientDTO = client.get(clientDTOs[0]);
        assertThat(compare(clientDTOs[0], clientDTO), equalTo(true));

        //delete
        clientDTO = client.delete(clientDTOs[0]);
        assertThat(compare(clientDTOs[0], clientDTO), equalTo(true));

        //get
        clientDTO = client.get(clientDTOs[0]);
        assertThat(clientDTO, equalTo(null));
        assertThat(client.count(getFilter()), equalTo(0));
    }

    @Test
    public void test_Filter_Clients() throws Exception {
        //create
        ClientDTO[] clientDTOs = setupClients();

        //filter
        Integer clientCount = client.count(getFilter());
        assertThat(clientCount, equalTo(clientDTOs.length));

        ClientList filterResult = client.filter(getFilter(1, 20, null, "*"));
        assertThat(filterResult.getContent().size(), equalTo(2));
        assertThat(filterResult.getContent().size(), not(equalTo(clientCount)));
    }

    @Test
    public void test_Update_Client() throws Exception {
        //create
        ClientDTO[] clientDTOs = readJsonFromFile("/clients.json", ClientDTO[].class);
        clientDTOs[0] = client.create(clientDTOs[0]);
        assertThat(client.count(getFilter()), equalTo(1));

        //get
        ClientDTO clientDTO = client.get(clientDTOs[0]);
        assertThat(compare(clientDTOs[0], clientDTO), equalTo(true));

        //update
        clientDTOs[0].setAddress("newAddress");
        clientDTO = client.get(clientDTOs[0]);
        assertThat(compare(clientDTOs[0], clientDTO), equalTo(false));

        client.update(clientDTOs[0]);
        clientDTO = client.get(clientDTOs[0]);
        assertThat(compare(clientDTOs[0], clientDTO), equalTo(true));
    }

    private Boolean compare(ClientDTO request, ClientDTO response) {
        return request.getId().equals(response.getId())
                && request.getFirstName().equals(response.getFirstName())
                && request.getLastName().equals(response.getLastName())
                && request.getCnp().equals(response.getCnp())
                && request.getPhoneNumber().equals(response.getPhoneNumber())
                && request.getAddress().equals(response.getAddress());
    }
}
