package ro.InnovaTeam.cemeteryApp.controller.request;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ro.InnovaTeam.cemeteryApp.FilterDTO;
import ro.InnovaTeam.cemeteryApp.RestingPlaceRequestDTO;
import ro.InnovaTeam.cemeteryApp.controller.client.ClientsController;
import ro.InnovaTeam.cemeteryApp.restClient.RestingPlaceRequestRestClient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Catalin Sorecau on 12/27/2014.
 */
@Controller
@RequestMapping("/request")
public class RestingPlaceRequestController {

    private static final Logger logger = LoggerFactory.getLogger(RestingPlaceRequestController.class);
    public static final String REQUEST = "/request";
    public static final String REQUEST_FILTER = "requestFilter";
    public static final int PAGE_SIZE = 20;

    @Autowired
    @Qualifier("requestValidator")
    private Validator requestValidator;

    @Autowired
    @Qualifier("messagesConfig")
    private Configuration config;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(config.getString("date.pattern"));
        // true passed to CustomDateEditor constructor means convert empty String to null
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping
    public String renderHome(Model model, HttpServletRequest request) {
        FilterDTO requestFilterDTO = (FilterDTO) request.getSession().getAttribute(REQUEST_FILTER);
        requestFilterDTO = requestFilterDTO != null ? requestFilterDTO : new FilterDTO();
        String param = request.getParameter("pageNo");
        Integer pageNo = param != null ? Integer.valueOf(param) : 1;

        List<RestingPlaceRequestDTO> requests;
        requestFilterDTO.setPageNo(pageNo);
        requestFilterDTO.setPageSize(PAGE_SIZE);
        requests = RestingPlaceRequestRestClient.getRequestsByFilter(requestFilterDTO);

        float pages = RestingPlaceRequestRestClient.getRequestCount(new FilterDTO(requestFilterDTO.getSearchCriteria(),
                requestFilterDTO.getParentId())) / (float) PAGE_SIZE;
        model.addAttribute("pages", Math.ceil(pages));
        model.addAttribute("requestList", requests);
        model.addAttribute("requestPath", REQUEST);
        return "request/requestsPage";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String renderAddPage(Model model, HttpServletRequest request) {

        if (!model.containsAttribute("requestDTOExists")) {
            RestingPlaceRequestDTO requestDTO = (RestingPlaceRequestDTO) request.getSession().getAttribute(ClientsController.REQUEST_DTO);
            model.addAttribute("request", requestDTO != null ? requestDTO : new RestingPlaceRequestDTO());
            request.getSession().removeAttribute(ClientsController.REQUEST_DTO);
        }
        return "request/requestDetailsPage";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("request") RestingPlaceRequestDTO requestDTO, BindingResult result, Model model) {
        requestValidator.validate(requestDTO, result);
        if (result.hasErrors()) {
            model.addAttribute("requestDTOExists", true);
            return "request/requestDetailsPage";
        }
        RestingPlaceRequestRestClient.add(requestDTO);
        return "redirect:" + REQUEST;
    }

    @RequestMapping(value = "/get/{id}")
    public String getRequestById(@PathVariable Integer id, Model model) {
        RestingPlaceRequestDTO requestDTO = RestingPlaceRequestRestClient.findById(id);

        model.addAttribute("request", requestDTO);
        model.addAttribute("view", true);
        return "request/requestDetailsPage";
    }

    @RequestMapping(value = "/delete/{id}")
    public String deleteRequest(@PathVariable Integer id) {
        try {
            RestingPlaceRequestRestClient.delete(id);
        }
        catch (Exception e) {
            logger.error("Could not delete Request with id: " + id, e);
        }
        return "redirect:" + REQUEST;
    }

    @RequestMapping(value = "/update")
    public String updateRequest(@ModelAttribute("request") RestingPlaceRequestDTO requestDTO, BindingResult result, Model model) {
        requestValidator.validate(requestDTO, result);
        if (result.hasErrors()) {
            model.addAttribute("view", true);
            return "request/requestDetailsPage";
        }
        RestingPlaceRequestRestClient.update(requestDTO.getId(), requestDTO);
        return "redirect:" + REQUEST;
    }

    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public void applyFilter(HttpServletRequest request, HttpServletResponse response) {
        String searchCriteria = request.getParameter("searchCriteria");
        String clientId = request.getParameter("clientId");
        //TODO: validate clientId
        FilterDTO requestFilterDTO = new FilterDTO();
        requestFilterDTO.setSearchCriteria(searchCriteria);
        if (StringUtils.isNotEmpty(clientId)) {
            requestFilterDTO.setParentId(Integer.valueOf(clientId));
        }

        request.getSession().setAttribute(REQUEST_FILTER, requestFilterDTO);

        try {
            response.sendRedirect(request.getContextPath() + REQUEST);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/refreshFilter", method = RequestMethod.POST)
    public void refreshFilter(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute(REQUEST_FILTER);
        try {
            response.sendRedirect(request.getContextPath() + REQUEST);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
