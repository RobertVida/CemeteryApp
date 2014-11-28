package ro.InnovaTeam.cemeteryApp.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ro.InnovaTeam.cemeteryApp.ClientDTO;
import ro.InnovaTeam.cemeteryApp.ClientList;

/**
* Created by Roxana on 11/28/2014.
*/
@RunWith(SpringJUnit4ClassRunner.class)
public class ClientTest extends BaseTest {

    @Test
    public void getClientTest() throws Exception {
        requestPerformer.testUseCase(GET + "/getClientTest.json", ClientDTO.class);
    }

    @Test
    public void updateClientTest() throws Exception {
        requestPerformer.testSuite(UPDATE + "/updateClientTest.json", ClientDTO.class);
    }

    @Test
    public void getClientsTests() throws Exception {
        requestPerformer.testSuite(GET + "/getClientsTest.json", ClientList.class);
    }
}
