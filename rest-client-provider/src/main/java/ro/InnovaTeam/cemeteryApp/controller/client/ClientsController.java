package ro.InnovaTeam.cemeteryApp.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import ro.InnovaTeam.cemeteryApp.client.ClientDTO;
import ro.InnovaTeam.cemeteryApp.client.FilterDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Catalin Sorecau on 11/15/2014.
 */
@Controller
@RequestMapping("/clients")
public class ClientsController {

    public static final String ADD_CLIENT = "/add";
    public static final String CLIENTS = "/clients";
    public static final String FILTER = "/filter";
    public static final String REFRESH_FILTER = "/refreshFilter";

    private static final String CLIENT_FILTER = "clientFilter";
    public static final int PAGE_SIZE = 20;

    @Autowired
    @Qualifier("clientValidator")
    private Validator clientValidator;
    //TODO: update this class according to CemeteryController
    @RequestMapping(method = RequestMethod.GET)
    public String render(Model model, HttpServletRequest request) {
        FilterDTO clientFilterDTO = (FilterDTO) request.getSession().getAttribute(CLIENT_FILTER);

        if (clientFilterDTO != null) {
            clientFilterDTO.setPageNo(0);
            clientFilterDTO.setPageSize(PAGE_SIZE);
            //model.addAttribute("pages", );
            //model.addAttribute("clientsList", );
        } else {
            //rest call (get first 20 clients)
            //model.addAttribute("pages", );
            //model.addAttribute("clientsList", );
        }

        model.addAttribute("clientsPageURL", CLIENTS);
        model.addAttribute("addPageURL", ADD_CLIENT);
        model.addAttribute("filterURL", FILTER);
        model.addAttribute("refreshFilterURL", REFRESH_FILTER);

        return "clientsPage";
    }

    @RequestMapping(value = CLIENTS, method = RequestMethod.GET)
    public String getClients(Model model, HttpServletRequest request) {
        Integer pageNo = Integer.valueOf(request.getParameter("pageNo"));
        FilterDTO clientFilterDTO = (FilterDTO) request.getSession().getAttribute(CLIENT_FILTER);

        if (clientFilterDTO != null) {
            clientFilterDTO.setPageNo(pageNo);
            clientFilterDTO.setPageSize(PAGE_SIZE);
            //rest call (get clients by filter)
            //add "clientsList" attribute to model (list obtained from rest call)
            //add "pages" attribute to model (total number of pages)
        } else {
            //rest call (get all clients)
            //add "clientsList" attribute to model
            //add "pages" attribute to model
        }

        model.addAttribute("clientsPageURL", CLIENTS);
        model.addAttribute("addPageURL", ADD_CLIENT);

        return "clientsPage";
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public String getClientById(Model model,  @PathVariable String id) {

        return "clientDetailsPage";
    }

    @RequestMapping(value = ADD_CLIENT, method = RequestMethod.GET)
    public String renderAddPage(Model model) {

        if (!model.containsAttribute("clientDTOExists")) {
            model.addAttribute("clientDetails", new ClientDTO());
        }
        return "clientAddPage";
    }

    @RequestMapping(value = ADD_CLIENT, method = RequestMethod.POST)
    public String addClient(@ModelAttribute("clientDetails") ClientDTO clientDTO, BindingResult result, Model model) {

        clientValidator.validate(clientDTO, result);
        if (result.hasErrors()) {
            model.addAttribute("clientDTOExists", true);
            return "clientAddPage";
        }
        //rest call (save clientDTO)
        return "redirect:/clients";
    }

    @RequestMapping(value = FILTER, method = RequestMethod.POST)
    public void applyFilter(HttpServletRequest request, HttpServletResponse response) {
        String searchCriteria = request.getParameter("searchCriteria");

        FilterDTO clientFilterDTO = new FilterDTO();
        clientFilterDTO.setSearchCriteria(searchCriteria);
        request.getSession().setAttribute(CLIENT_FILTER, clientFilterDTO);

        try {
            response.sendRedirect(request.getContextPath() + CLIENTS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = REFRESH_FILTER, method = RequestMethod.POST)
    public void refreshFilter(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute(CLIENT_FILTER);
        try {
            response.sendRedirect(request.getContextPath() + CLIENTS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
