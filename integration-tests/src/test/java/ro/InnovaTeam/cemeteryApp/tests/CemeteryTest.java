package ro.InnovaTeam.cemeteryApp.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ro.InnovaTeam.cemeteryApp.CemeteryDTO;
import ro.InnovaTeam.cemeteryApp.CemeteryList;

/**
* Created by robert on 11/24/2014.
*/
@RunWith(SpringJUnit4ClassRunner.class)
public class CemeteryTest extends BaseTest {

    @Test
    public void getCemeteryTest() throws Exception {
        requestPerformer.testUseCase(GET + "/getCemeteryTest.json", CemeteryDTO.class);
    }

    @Test
    public void updateCemeteryTest() throws Exception {
        requestPerformer.testSuite(UPDATE + "/updateCemeteryTest.json", CemeteryDTO.class);
    }

    @Test
    public void getCemeteriesTests() throws Exception {
        requestPerformer.testSuite(GET + "/getCemeteriesTest.json", CemeteryList.class);
    }
}
