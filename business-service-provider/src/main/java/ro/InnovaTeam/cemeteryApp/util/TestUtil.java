package ro.InnovaTeam.cemeteryApp.util;

import ro.InnovaTeam.cemeteryApp.model.test.Test;
import ro.InnovaTeam.cemeteryApp.test.TestDTO;

/**
 * Created by Cata on 10/25/2014.
 */
public class TestUtil {

    public static TestDTO getTestDTO(Test test) {
        TestDTO testDTO = new TestDTO();

        testDTO.setId(test.getId());
        testDTO.setContent(test.getContent());

        return testDTO;
    }
}
