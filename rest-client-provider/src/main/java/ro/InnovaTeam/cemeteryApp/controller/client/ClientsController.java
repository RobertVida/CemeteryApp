package ro.InnovaTeam.cemeteryApp.controller.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import ro.InnovaTeam.cemeteryApp.ClientDTO;
import ro.InnovaTeam.cemeteryApp.FilterDTO;
import ro.InnovaTeam.cemeteryApp.RestingPlaceRequestDTO;
import ro.InnovaTeam.cemeteryApp.controller.request.RestingPlaceRequestController;
import ro.InnovaTeam.cemeteryApp.restClient.ClientRestClient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Catalin Sorecau on 11/15/2014.
 */
@Controller
@RequestMapping("/clients")
public class ClientsController {

    private static final Logger logger = LoggerFactory.getLogger(ClientsController.class);
    public static final String ADD_CLIENT = "/add";
    public static final String CLIENTS = "/clients";
    public static final String FILTER = "/filter";
    public static final String REFRESH_FILTER = "/refreshFilter";
    public static final String DELETE = "/delete/{id}";
    public static final String UPDATE = "/update";
    public static final String REQUEST_DTO = "requestDTO";

    private static final String CLIENT_FILTER = "clientFilter";
    public static final int PAGE_SIZE = 20;

    @Autowired
    @Qualifier("clientValidator")
    private Validator clientValidator;

    @RequestMapping
    public String render(Model model, HttpServletRequest request) {
        FilterDTO clientFilterDTO = (FilterDTO) request.getSession().getAttribute(CLIENT_FILTER);
        clientFilterDTO = clientFilterDTO != null ? clientFilterDTO : new FilterDTO();
        String param = request.getParameter("pageNo");
        Integer pageNo = param != null ? Integer.valueOf(param) : 1;

        List<ClientDTO> clients;
        clientFilterDTO.setPageNo(pageNo);
        clientFilterDTO.setPageSize(PAGE_SIZE);
        clientFilterDTO.setParentId(null);
        clients = ClientRestClient.getClientsByFilter(clientFilterDTO);

        int pages = clients.size()/PAGE_SIZE;
        model.addAttribute("pages", pages != 0 ? pages + 1 : pages);
        model.addAttribute("clientList", clients);

        model.addAttribute("clientsPageURL", CLIENTS);
        model.addAttribute("addPageURL", ADD_CLIENT);
        model.addAttribute("filterURL", FILTER);
        model.addAttribute("refreshFilterURL", REFRESH_FILTER);

        return "client/clientsPage";
    }

    @RequestMapping(value = DELETE)
    public String deleteClient(@PathVariable Integer id) {
        try {
            ClientRestClient.delete(id);
        }
        catch (Exception e) {
            logger.error("Could not delete Client with id: " + id, e);
        }
        return "redirect:/clients";
    }

    @RequestMapping(value = UPDATE)
    public String updateClient(@ModelAttribute("client") ClientDTO clientDTO, BindingResult result, Model model) {
        clientValidator.validate(clientDTO, result);
        if (result.hasErrors()) {
            model.addAttribute("view", true);
            return "client/clientDetailsPage";
        }
        ClientRestClient.update(clientDTO.getId(), clientDTO);
        return "redirect:" + CLIENTS;
    }

    @RequestMapping(value = "/get/{id}")
    public String getClientById(Model model,  @PathVariable Integer id) {
        ClientDTO clientDTO = ClientRestClient.findById(id);

        model.addAttribute("client", clientDTO);
        model.addAttribute("view", true);
        return "client/clientDetailsPage";
    }

    @RequestMapping(value = ADD_CLIENT, method = RequestMethod.GET)
    public String renderAddPage(Model model) {

        if (!model.containsAttribute("clientDTOExists")) {
            model.addAttribute("client", new ClientDTO());
        }
        return "client/clientDetailsPage";
    }

    @RequestMapping(value = ADD_CLIENT, method = RequestMethod.POST)
    public String addClient(@ModelAttribute("client") ClientDTO clientDTO, BindingResult result, Model model) {

        clientValidator.validate(clientDTO, result);
        if (result.hasErrors()) {
            model.addAttribute("clientDTOExists", true);
            return "client/clientDetailsPage";
        }
        ClientRestClient.add(clientDTO);
        return "redirect:/clients";
    }

    @RequestMapping(value = FILTER, method = RequestMethod.GET)
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

    @RequestMapping(value = "/filterRequests/{clientId}", method = RequestMethod.GET)
    public void filterRequestsByClientId(@PathVariable Integer clientId, HttpServletRequest request, HttpServletResponse response) {
        FilterDTO requestFilterDTO = new FilterDTO();
        requestFilterDTO.setSearchCriteria("");
        requestFilterDTO.setParentId(clientId);

        request.getSession().setAttribute(RestingPlaceRequestController.REQUEST_FILTER, requestFilterDTO);
        try {
            response.sendRedirect(request.getContextPath() + RestingPlaceRequestController.REQUEST);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/addRequest/{clientId}", method = RequestMethod.GET)
    public void addRequestForClientId(@PathVariable Integer clientId, HttpServletRequest request, HttpServletResponse response) {
        RestingPlaceRequestDTO requestDTO = new RestingPlaceRequestDTO();
        requestDTO.setClientId(clientId);

        request.getSession().setAttribute(REQUEST_DTO, requestDTO);
        try {
            response.sendRedirect(request.getContextPath() + RestingPlaceRequestController.REQUEST + "/add");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
