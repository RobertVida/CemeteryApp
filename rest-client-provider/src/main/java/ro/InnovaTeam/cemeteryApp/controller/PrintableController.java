package ro.InnovaTeam.cemeteryApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Catalin Sorecau on 1/13/2015.
 */
@Controller
@RequestMapping("/printable")
public class PrintableController {

    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    public ModelAndView showStatisticsPDF() {
        return new ModelAndView("printableStatistics");
    }
}
