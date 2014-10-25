package ro.InnovaTeam.cemeteryApp.service.test.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.InnovaTeam.cemeteryApp.eao.test.TestEAO;
import ro.InnovaTeam.cemeteryApp.service.test.TestService;
import ro.InnovaTeam.cemeteryApp.test.TestDTO;
import ro.InnovaTeam.cemeteryApp.util.TestUtil;

/**
 * Created by Cata on 10/24/2014.
 */
@Transactional
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestEAO testEAO;

    @Override
    public TestDTO getTestForId(Long id) {
        return TestUtil.getTestDTO(testEAO.getForId(id));
    }
}
