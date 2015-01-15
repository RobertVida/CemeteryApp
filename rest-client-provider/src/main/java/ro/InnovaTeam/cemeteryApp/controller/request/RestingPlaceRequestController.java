package ro.InnovaTeam.cemeteryApp.controller.request;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import ro.InnovaTeam.cemeteryApp.ErrorDTO;
import ro.InnovaTeam.cemeteryApp.FilterDTO;
import ro.InnovaTeam.cemeteryApp.RestingPlaceRequestDTO;
import ro.InnovaTeam.cemeteryApp.controller.auth.UserAuthenticationManager;
import ro.InnovaTeam.cemeteryApp.controller.client.ClientsController;
import ro.InnovaTeam.cemeteryApp.controller.log.LogController;
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
    public static final String REQUEST_STATUS = "requestStatus";
    public static final int PAGE_SIZE = 20;
    public static final String[] REQUEST_STATUS_OPTIONS = new String[] {"Activ", "Finalizat"};
    private ObjectMapper om = new ObjectMapper();

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
    public String renderHome(Model model, HttpServletRequest request, HttpServletResponse response) {
        FilterDTO requestFilterDTO = (FilterDTO) request.getSession().getAttribute(REQUEST_FILTER);
        requestFilterDTO = requestFilterDTO != null ? requestFilterDTO : new FilterDTO();
        String param = request.getParameter("pageNo");
        Integer pageNo = param != null ? Integer.valueOf(param) : 1;
        String status = (String) request.getSession().getAttribute(REQUEST_STATUS);

        List<RestingPlaceRequestDTO> requests;
        requestFilterDTO.setPageNo(pageNo);
        requestFilterDTO.setPageSize(PAGE_SIZE);
        float pages;

        try {
            if (StringUtils.isNotEmpty(status)) {
                requests = RestingPlaceRequestRestClient.findRequestByFilterAndStatus(requestFilterDTO, status);
                pages = RestingPlaceRequestRestClient.countRequestByFilterAndStatus(new FilterDTO(requestFilterDTO.getSearchCriteria(),
                        requestFilterDTO.getParentId()), status);
            } else {
                requests = RestingPlaceRequestRestClient.getRequestsByFilter(requestFilterDTO);
                pages = RestingPlaceRequestRestClient.getRequestCount(new FilterDTO(requestFilterDTO.getSearchCriteria(),
                        requestFilterDTO.getParentId()));
            }
            model.addAttribute("hasAdminRole", UserAuthenticationManager.hasAdminRole());
            pages /= (float) PAGE_SIZE;
            model.addAttribute("pages", Math.ceil(pages));
            model.addAttribute("requestList", requests);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            try {
                ErrorDTO error = om.readValue(e.getResponseBodyAsString(), ErrorDTO.class);
                if (ErrorDTO.Status.UNAUTHORIZED_ACCESS.toString().equals(error.getStatus())) {
                    request.getSession().invalidate();
                    response.sendRedirect(request.getContextPath() + "/login");
                }
                model.addAttribute("errors", error.getErrors());
            } catch (IOException ioe) {
                logger.error("Could not read value from ObjectMapper", ioe);
            }
        }
        model.addAttribute("requestPath", REQUEST);
        return "request/requestsPage";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String renderAddPage(Model model, HttpServletRequest request, HttpServletResponse response) {

        if (!model.containsAttribute("requestDTOExists")) {
            RestingPlaceRequestDTO requestDTO = (RestingPlaceRequestDTO) request.getSession().getAttribute(ClientsController.REQUEST_DTO);
            model.addAttribute("request", requestDTO != null ? requestDTO : new RestingPlaceRequestDTO());
            request.getSession().removeAttribute(ClientsController.REQUEST_DTO);
        }
        model.addAttribute("requestStatusOptions", REQUEST_STATUS_OPTIONS);
        try {
            model.addAttribute("hasAdminRole", UserAuthenticationManager.hasAdminRole());
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            try {
                ErrorDTO error = om.readValue(e.getResponseBodyAsString(), ErrorDTO.class);
                if (ErrorDTO.Status.UNAUTHORIZED_ACCESS.toString().equals(error.getStatus())) {
                    request.getSession().invalidate();
                    response.sendRedirect(request.getContextPath() + "/login");
                }
                model.addAttribute("errors", error.getErrors());
                return "request/requestsPage";
            } catch (IOException ioe) {
                logger.error("Could not read value from ObjectMapper", ioe);
            }
        }
        return "request/requestDetailsPage";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("request") RestingPlaceRequestDTO requestDTO, BindingResult result, Model model,
                      HttpServletResponse response, HttpServletRequest request) {
        requestValidator.validate(requestDTO, result);
        try {
            model.addAttribute("hasAdminRole", UserAuthenticationManager.hasAdminRole());
            if (result.hasErrors()) {
                model.addAttribute("requestDTOExists", true);
                return "request/requestDetailsPage";
            }
            RestingPlaceRequestRestClient.add(requestDTO);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            try {
                ErrorDTO error = om.readValue(e.getResponseBodyAsString(), ErrorDTO.class);
                if (ErrorDTO.Status.UNAUTHORIZED_ACCESS.toString().equals(error.getStatus())) {
                    request.getSession().invalidate();
                    response.sendRedirect(request.getContextPath() + "/login");
                }
                model.addAttribute("errors", error.getErrors());
                return "request/requestDetailsPage";
            } catch (IOException ioe) {
                logger.error("Could not read value from ObjectMapper", ioe);
            }
        }
        return "redirect:" + REQUEST;
    }

    @RequestMapping(value = "/get/{id}")
    public String getRequestById(@PathVariable Integer id, Model model, HttpServletRequest request, HttpServletResponse response) {
        try {
            RestingPlaceRequestDTO requestDTO = RestingPlaceRequestRestClient.findById(id);
            model.addAttribute("request", requestDTO);
            model.addAttribute("hasAdminRole", UserAuthenticationManager.hasAdminRole());
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            try {
                ErrorDTO error = om.readValue(e.getResponseBodyAsString(), ErrorDTO.class);
                if (ErrorDTO.Status.UNAUTHORIZED_ACCESS.toString().equals(error.getStatus())) {
                    request.getSession().invalidate();
                    response.sendRedirect(request.getContextPath() + "/login");
                }
                model.addAttribute("errors", error.getErrors());
                return "request/requestsPage";
            } catch (IOException ioe) {
                logger.error("Could not read value from ObjectMapper", ioe);
            }
        }

        model.addAttribute("view", true);
        model.addAttribute("requestStatusOptions", REQUEST_STATUS_OPTIONS);
        return "request/requestDetailsPage";
    }

    @RequestMapping(value = "/delete/{id}")
    public String deleteRequest(@PathVariable Integer id, HttpServletResponse response, HttpServletRequest request, Model model) {
        try {
            RestingPlaceRequestRestClient.delete(id);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            try {
                ErrorDTO error = om.readValue(e.getResponseBodyAsString(), ErrorDTO.class);
                if (ErrorDTO.Status.UNAUTHORIZED_ACCESS.toString().equals(error.getStatus())) {
                    request.getSession().invalidate();
                    response.sendRedirect(request.getContextPath() + "/login");
                }
                model.addAttribute("errors", error.getErrors());
                return "request/requestsPage";
            } catch (IOException ioe) {
                logger.error("Could not read value from ObjectMapper", ioe);
            }
        }
        return "redirect:" + REQUEST;
    }

    @RequestMapping(value = "/update")
    public String updateRequest(@ModelAttribute("request") RestingPlaceRequestDTO requestDTO, BindingResult result, Model model,
                                HttpServletRequest request, HttpServletResponse response) {
        requestValidator.validate(requestDTO, result);
        try {
            model.addAttribute("hasAdminRole", UserAuthenticationManager.hasAdminRole());
            if (result.hasErrors()) {
                model.addAttribute("view", true);
                return "request/requestDetailsPage";
            }
            RestingPlaceRequestRestClient.update(requestDTO.getId(), requestDTO);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            try {
                ErrorDTO error = om.readValue(e.getResponseBodyAsString(), ErrorDTO.class);
                if (ErrorDTO.Status.UNAUTHORIZED_ACCESS.toString().equals(error.getStatus())) {
                    request.getSession().invalidate();
                    response.sendRedirect(request.getContextPath() + "/login");
                }
                model.addAttribute("errors", error.getErrors());
                return "request/requestDetailsPage";
            } catch (IOException ioe) {
                logger.error("Could not read value from ObjectMapper", ioe);
            }
        }
        return "redirect:" + REQUEST;
    }

    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public void applyFilter(HttpServletRequest request, HttpServletResponse response) {
        String searchCriteria = request.getParameter("searchCriteria");
        String clientId = request.getParameter("clientId");
        String status = request.getParameter("status");
        //TODO: validate clientId
        FilterDTO requestFilterDTO = new FilterDTO();
        requestFilterDTO.setSearchCriteria(searchCriteria);
        if (StringUtils.isNotEmpty(clientId)) {
            requestFilterDTO.setParentId(Integer.valueOf(clientId));
        }

        request.getSession().setAttribute(REQUEST_FILTER, requestFilterDTO);
        request.getSession().setAttribute(REQUEST_STATUS, status);

        try {
            response.sendRedirect(request.getContextPath() + REQUEST);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/refreshFilter", method = RequestMethod.POST)
    public void refreshFilter(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute(REQUEST_FILTER);
        request.getSession().removeAttribute(REQUEST_STATUS);
        try {
            response.sendRedirect(request.getContextPath() + REQUEST);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/filterLogs/{requestId}", method = RequestMethod.GET)
    public void filterLogs(HttpServletRequest request, @PathVariable Integer requestId, HttpServletResponse response) {
        request.getSession().setAttribute(LogController.LOGS_TABLE_NAME, "restingplacerequests");
        request.getSession().setAttribute(LogController.LOGS_TABLE_ID, String.valueOf(requestId));
        try {
            response.sendRedirect(request.getContextPath() + LogController.LOGS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
