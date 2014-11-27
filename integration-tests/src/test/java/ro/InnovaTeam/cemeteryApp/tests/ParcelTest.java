package ro.InnovaTeam.cemeteryApp.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ro.InnovaTeam.cemeteryApp.ParcelDTO;
import ro.InnovaTeam.cemeteryApp.ParcelList;

/**
 * Created by robert on 11/27/2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ParcelTest extends BaseTest {

    @Test
    public void getParcelTest() throws Exception {
        requestPerformer.testUseCase(GET + "/getParcelTest.json", ParcelDTO.class);
    }

    @Test
    public void updateParcelTest() throws Exception {
        requestPerformer.testSuite(UPDATE + "/updateParcelTest.json", ParcelDTO.class);
    }

    @Test
    public void getParcelsTests() throws Exception {
        requestPerformer.testSuite(GET + "/getParcelsTest.json", ParcelList.class);
    }
}
