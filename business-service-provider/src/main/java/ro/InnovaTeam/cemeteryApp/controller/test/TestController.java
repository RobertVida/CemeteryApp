package ro.InnovaTeam.cemeteryApp.controller.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.InnovaTeam.cemeteryApp.service.test.TestService;
import ro.InnovaTeam.cemeteryApp.test.TestDTO;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/get/{testId}", method = RequestMethod.GET)
    @ResponseBody
    public TestDTO getTestForId(@PathVariable Long testId) {
        return testService.getTestForId(testId);
    }
}