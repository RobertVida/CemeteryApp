package ro.InnovaTeam.cemeteryApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ro.InnovaTeam.cemeteryApp.restClient.test.TestRestClient;
import ro.InnovaTeam.cemeteryApp.test.TestDTO;

/**
 * Created by Cata on 10/25/2014.
 */
@Controller
@RequestMapping("test")
public class TestController {

    @RequestMapping(value = "/test.htm", method = RequestMethod.GET)
    public String getTest(Model model) {
        TestDTO testDTO = TestRestClient.getTestForId(1L);
        model.addAttribute("test", testDTO);

        return "test";
    }
}
