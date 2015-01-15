package ro.InnovaTeam.cemeteryApp.controller.deceased;

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
import ro.InnovaTeam.cemeteryApp.DeceasedDTO;
import ro.InnovaTeam.cemeteryApp.ErrorDTO;
import ro.InnovaTeam.cemeteryApp.FilterDTO;
import ro.InnovaTeam.cemeteryApp.controller.auth.UserAuthenticationManager;
import ro.InnovaTeam.cemeteryApp.controller.log.LogController;
import ro.InnovaTeam.cemeteryApp.controller.monument.MonumentController;
import ro.InnovaTeam.cemeteryApp.restClient.DeceasedRestClient;
import org.apache.commons.configuration.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Catalin Sorecau on 11/30/2014.
 */
@Controller
@RequestMapping("/deceased")
public class DeceasedController {

    private static final Logger logger = LoggerFactory.getLogger(DeceasedController.class);
    public static final String DECEASED = "/deceased";
    public static final String DECEASED_FILTER = "deceasedFilter";
    public static final String DECEASED_DTO = "deceasedDTO";
    public static final int PAGE_SIZE = 20;
    private ObjectMapper om = new ObjectMapper();

    @Autowired
    @Qualifier("deceasedValidator")
    private Validator deceasedValidator;

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
        FilterDTO deceasedFilterDTO = (FilterDTO) request.getSession().getAttribute(DECEASED_FILTER);
        deceasedFilterDTO = deceasedFilterDTO != null ? deceasedFilterDTO : new FilterDTO();
        String param = request.getParameter("pageNo");
        Integer pageNo = param != null ? Integer.valueOf(param) : 1;

        List<DeceasedDTO> deceasedDTOs;
        deceasedFilterDTO.setPageNo(pageNo);
        deceasedFilterDTO.setPageSize(PAGE_SIZE);
        try {
            deceasedDTOs = DeceasedRestClient.findByFilter(deceasedFilterDTO);

            float pages = DeceasedRestClient.getDeceasedCount(new FilterDTO(deceasedFilterDTO.getSearchCriteria(),
                    deceasedFilterDTO.getParentId())) / (float) PAGE_SIZE;
            model.addAttribute("pages", Math.ceil(pages));
            model.addAttribute("deceasedList", deceasedDTOs);
            model.addAttribute("hasAdminRole", UserAuthenticationManager.hasAdminRole());
        } catch (HttpClientErrorException e) {
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
        return "deceased/deceasedPage";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String renderAddPage(Model model, HttpServletRequest request, HttpServletResponse response) {

        if (!model.containsAttribute("deceasedDTOExists")) {
            DeceasedDTO deceasedDTO = (DeceasedDTO) request.getSession().getAttribute(DECEASED_DTO);
            model.addAttribute("deceased", deceasedDTO != null ? deceasedDTO : new DeceasedDTO());
            request.getSession().removeAttribute(DECEASED_DTO);
        }
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
                return "deceased/deceasedPage";
            } catch (IOException ioe) {
                logger.error("Could not read value from ObjectMapper", ioe);
            }
        }
        return "deceased/deceasedDetailsPage";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("deceased") DeceasedDTO deceasedDTO, BindingResult result, Model model,
                      HttpServletRequest request, HttpServletResponse response) {
        deceasedValidator.validate(deceasedDTO, result);
        try {
            model.addAttribute("hasAdminRole", UserAuthenticationManager.hasAdminRole());
            if (result.hasErrors()) {
                model.addAttribute("deceasedDTOExists", true);
                return "deceased/deceasedDetailsPage";
            }
            DeceasedRestClient.add(deceasedDTO);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            try {
                ErrorDTO error = om.readValue(e.getResponseBodyAsString(), ErrorDTO.class);
                if (ErrorDTO.Status.UNAUTHORIZED_ACCESS.toString().equals(error.getStatus())) {
                    request.getSession().invalidate();
                    response.sendRedirect(request.getContextPath() + "/login");
                }
                model.addAttribute("errors", error.getErrors());
                return "deceased/deceasedDetailsPage";
            } catch (IOException ioe) {
                logger.error("Could not read value from ObjectMapper", ioe);
            }
        }
        return "redirect:" + DECEASED;
    }

    @RequestMapping(value = "/get/{id}")
    public String getById(@PathVariable Integer id, Model model, HttpServletRequest request, HttpServletResponse response) {
        try {
            DeceasedDTO deceasedDTO = DeceasedRestClient.findById(id);
            if (deceasedDTO.getCertificateId() != null) {
                deceasedDTO.setHasCaregiver(false);
            }
            model.addAttribute("deceased", deceasedDTO);
            model.addAttribute("hasAdminRole", UserAuthenticationManager.hasAdminRole());
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            try {
                ErrorDTO error = om.readValue(e.getResponseBodyAsString(), ErrorDTO.class);
                if (ErrorDTO.Status.UNAUTHORIZED_ACCESS.toString().equals(error.getStatus())) {
                    request.getSession().invalidate();
                    response.sendRedirect(request.getContextPath() + "/login");
                }
                model.addAttribute("errors", error.getErrors());
                return "deceased/deceasedPage";
            } catch (IOException ioe) {
                logger.error("Could not read value from ObjectMapper", ioe);
            }
        }

        model.addAttribute("view", true);
        return "deceased/deceasedDetailsPage";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable Integer id, Model model, HttpServletRequest request, HttpServletResponse response) {
        try {
            DeceasedRestClient.delete(id);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            try {
                ErrorDTO error = om.readValue(e.getResponseBodyAsString(), ErrorDTO.class);
                if (ErrorDTO.Status.UNAUTHORIZED_ACCESS.toString().equals(error.getStatus())) {
                    request.getSession().invalidate();
                    response.sendRedirect(request.getContextPath() + "/login");
                }
                model.addAttribute("errors", error.getErrors());
                return "deceased/deceasedPage";
            } catch (IOException ioe) {
                logger.error("Could not read value from ObjectMapper", ioe);
            }
        }
        return "redirect:" + DECEASED;
    }

    @RequestMapping(value = "/update")
    public String update(@ModelAttribute("deceased") DeceasedDTO deceasedDTO, BindingResult result, Model model,
                         HttpServletRequest request, HttpServletResponse response) {
        deceasedValidator.validate(deceasedDTO, result);
        try {
            model.addAttribute("hasAdminRole", UserAuthenticationManager.hasAdminRole());
            if (result.hasErrors()) {
                model.addAttribute("view", true);
                return "deceased/deceasedDetailsPage";
            }
            DeceasedRestClient.update(deceasedDTO.getId(), deceasedDTO);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            try {
                ErrorDTO error = om.readValue(e.getResponseBodyAsString(), ErrorDTO.class);
                if (ErrorDTO.Status.UNAUTHORIZED_ACCESS.toString().equals(error.getStatus())) {
                    request.getSession().invalidate();
                    response.sendRedirect(request.getContextPath() + "/login");
                }
                model.addAttribute("errors", error.getErrors());
                return "deceased/deceasedDetailsPage";
            } catch (IOException ioe) {
                logger.error("Could not read value from ObjectMapper", ioe);
            }
        }
        return "redirect:" + DECEASED;
    }

    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public void applyFilter(HttpServletRequest request, HttpServletResponse response) {
        String searchCriteria = request.getParameter("searchCriteria");
        String cemeteryId = request.getParameter("structureId");
        //TODO: validate cemeteryId
        FilterDTO deceasedFilterDTO = new FilterDTO();
        deceasedFilterDTO.setSearchCriteria(searchCriteria);
        if(StringUtils.isNotEmpty(cemeteryId)) {
            deceasedFilterDTO.setParentId(Integer.valueOf(cemeteryId));
        }
        request.getSession().setAttribute(DECEASED_FILTER, deceasedFilterDTO);

        try {
            response.sendRedirect(request.getContextPath() + DECEASED);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/filterLogs/{deceasedId}", method = RequestMethod.GET)
    public void filterLogs(HttpServletRequest request, @PathVariable Integer deceasedId, HttpServletResponse response) {
        request.getSession().setAttribute(LogController.LOGS_TABLE_NAME, "deceased");
        request.getSession().setAttribute(LogController.LOGS_TABLE_ID, String.valueOf(deceasedId));
        try {
            response.sendRedirect(request.getContextPath() + LogController.LOGS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/refreshFilter", method = RequestMethod.POST)
    public void refreshFilter(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute(DECEASED_FILTER);
        try {
            response.sendRedirect(request.getContextPath() + DECEASED);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
