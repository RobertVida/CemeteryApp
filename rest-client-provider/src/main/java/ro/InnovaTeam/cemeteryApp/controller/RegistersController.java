package ro.InnovaTeam.cemeteryApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Catalin Sorecau on 11/15/2014.
 */
@Controller
@RequestMapping("/registers")
public class RegistersController {

    @RequestMapping(value = "/deceased", method = RequestMethod.GET)
    public String showDeceasedRegister(Model model){

        return "deceasedPage";
    }
}
