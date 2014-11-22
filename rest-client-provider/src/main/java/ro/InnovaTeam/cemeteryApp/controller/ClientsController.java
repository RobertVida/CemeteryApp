package ro.InnovaTeam.cemeteryApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Catalin Sorecau on 11/15/2014.
 */
@Controller
@RequestMapping("/clients")
public class ClientsController {

    @RequestMapping
    public String showDefaultView(Model model) {

        return "clientsPage";
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public String getClientById(Model model,  @PathVariable String id) {

        return "clientDetailsPage";
    }
}
