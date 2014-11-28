package ro.InnovaTeam.cemeteryApp.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ro.InnovaTeam.cemeteryApp.GraveDTO;
import ro.InnovaTeam.cemeteryApp.GraveList;

/**
 * Created by robert on 11/28/2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class GraveTest extends BaseTest {

    @Test
    public void getGraveTest() throws Exception {
        requestPerformer.testUseCase(GET + "/getGraveTest.json", GraveDTO.class);
    }

    @Test
    public void updateGraveTest() throws Exception {
        requestPerformer.testSuite(UPDATE + "/updateGraveTest.json", GraveDTO.class);
    }

    @Test
    public void getGravesTests() throws Exception {
        requestPerformer.testSuite(GET + "/getGravesTest.json", GraveList.class);
    }
}
