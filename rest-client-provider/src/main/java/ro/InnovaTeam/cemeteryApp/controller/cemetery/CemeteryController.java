package ro.InnovaTeam.cemeteryApp.controller.cemetery;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import ro.InnovaTeam.cemeteryApp.CemeteryDTO;
import ro.InnovaTeam.cemeteryApp.ErrorDTO;
import ro.InnovaTeam.cemeteryApp.FilterDTO;
import ro.InnovaTeam.cemeteryApp.ParcelDTO;
import ro.InnovaTeam.cemeteryApp.controller.auth.UserAuthenticationManager;
import ro.InnovaTeam.cemeteryApp.controller.log.LogController;
import ro.InnovaTeam.cemeteryApp.controller.parcel.ParcelController;
import ro.InnovaTeam.cemeteryApp.restClient.CemeteryRestClient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Catalin Sorecau on 11/24/2014.
 */
@Controller
@RequestMapping("/cemetery")
public class CemeteryController {

    private static final Logger logger = LoggerFactory.getLogger(CemeteryController.class);
    private static final String CEMETERY = "/cemetery";
    private static final String CEMETERY_FILTER = "cemeteryFilter";
    public static final String PARCEL_DTO = "parcelDTO";
    public static final int PAGE_SIZE = 20;
    private ObjectMapper om = new ObjectMapper();

    @Autowired
    @Qualifier("cemeteryValidator")
    private Validator cemeteryValidator;

    @RequestMapping
    public String renderHome(Model model, HttpServletRequest request, HttpServletResponse response) {
        FilterDTO cemeteryFilterDTO = (FilterDTO) request.getSession().getAttribute(CEMETERY_FILTER);
        cemeteryFilterDTO = cemeteryFilterDTO != null ? cemeteryFilterDTO : new FilterDTO();
        String param = request.getParameter("pageNo");
        Integer pageNo = param != null ? Integer.valueOf(param) : 1;

        List<CemeteryDTO> cemeteries;
        cemeteryFilterDTO.setPageNo(pageNo);
        cemeteryFilterDTO.setPageSize(PAGE_SIZE);
        cemeteryFilterDTO.setParentId(null);
        try {
            model.addAttribute("hasAdminRole", UserAuthenticationManager.hasAdminRole());
            cemeteries = CemeteryRestClient.getCemeteriesByFilter(cemeteryFilterDTO);
            model.addAttribute("cemeteryList", cemeteries);
            float pages = CemeteryRestClient.getCemeteryCount(new FilterDTO(cemeteryFilterDTO.getSearchCriteria(), null)) / (float)PAGE_SIZE;
            model.addAttribute("pages", Math.ceil(pages));
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

        model.addAttribute("cemeteryPath", CEMETERY);
        return "cemetery/cemeteryPage";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String renderAddPage(Model model, HttpServletRequest request, HttpServletResponse response) {

        if (!model.containsAttribute("cemeteryDTOExists")) {
            model.addAttribute("cemetery", new CemeteryDTO());
        }
        try {
            model.addAttribute("hasAdminRole", UserAuthenticationManager.hasAdminRole());
        } catch (HttpClientErrorException e) {
            try {
                ErrorDTO error = om.readValue(e.getResponseBodyAsString(), ErrorDTO.class);
                if (ErrorDTO.Status.UNAUTHORIZED_ACCESS.toString().equals(error.getStatus())) {
                    request.getSession().invalidate();
                    response.sendRedirect(request.getContextPath() + "/login");
                }
                model.addAttribute("errors", error.getErrors());
                return "cemetery/cemeteryPage";
            } catch (IOException ioe) {
                logger.error("Could not read value from ObjectMapper", ioe);
            }
        }
        return "cemetery/cemeteryDetailsPage";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("cemetery") CemeteryDTO cemeteryDTO, BindingResult result, Model model,
                      HttpServletRequest request, HttpServletResponse response) {
        cemeteryValidator.validate(cemeteryDTO, result);
        try {
            model.addAttribute("hasAdminRole", UserAuthenticationManager.hasAdminRole());
            if (result.hasErrors()) {
                model.addAttribute("cemeteryDTOExists", true);
                return "cemetery/cemeteryDetailsPage";
            }
            CemeteryRestClient.add(cemeteryDTO);
        } catch (HttpClientErrorException e) {
            try {
                ErrorDTO error = om.readValue(e.getResponseBodyAsString(), ErrorDTO.class);
                if (ErrorDTO.Status.UNAUTHORIZED_ACCESS.toString().equals(error.getStatus())) {
                    request.getSession().invalidate();
                    response.sendRedirect(request.getContextPath() + "/login");
                }
                model.addAttribute("errors", error.getErrors());
                return "cemetery/cemeteryDetailsPage";
            } catch (IOException ioe) {
                logger.error("Could not read value from ObjectMapper", ioe);
            }
        }
        return "redirect:/cemetery";
    }

    @RequestMapping(value = "/get/{id}")
    public String getCemeteryById(@PathVariable Integer id, Model model, HttpServletRequest request, HttpServletResponse response) {
        try {
            CemeteryDTO cemeteryDTO = CemeteryRestClient.findById(id);
            model.addAttribute("hasAdminRole", UserAuthenticationManager.hasAdminRole());
            model.addAttribute("cemetery", cemeteryDTO);
        } catch (HttpClientErrorException e) {
            try {
                ErrorDTO error = om.readValue(e.getResponseBodyAsString(), ErrorDTO.class);
                if (ErrorDTO.Status.UNAUTHORIZED_ACCESS.toString().equals(error.getStatus())) {
                    request.getSession().invalidate();
                    response.sendRedirect(request.getContextPath() + "/login");
                }
                model.addAttribute("errors", error.getErrors());
                return "cemetery/cemeteryPage";
            } catch (IOException ioe) {
                logger.error("Could not read value from ObjectMapper", ioe);
            }
        }
        model.addAttribute("view", true);
        return "cemetery/cemeteryDetailsPage";
    }

    @RequestMapping(value = "/delete/{id}")
    public String deleteCemetery(@PathVariable Integer id, Model model, HttpServletResponse response, HttpServletRequest request) {
        try {
            CemeteryRestClient.delete(id);
        }
        catch (HttpClientErrorException e) {
            try {
                ErrorDTO error = om.readValue(e.getResponseBodyAsString(), ErrorDTO.class);
                if (error.getStatus().equals(ErrorDTO.Status.UNAUTHORIZED_ACCESS.toString())) {
                    request.getSession().invalidate();
                    response.sendRedirect(request.getContextPath() + "/login");
                }
                model.addAttribute("errors", error.getErrors());
                return "cemetery/cemeteryPage";
            } catch (IOException ioe) {
                logger.error("Could not read value from ObjectMapper", ioe);
            }
        }
        return "redirect:/cemetery";
    }

    @RequestMapping(value = "/update")
    public String updateCemetery(@ModelAttribute("cemetery") CemeteryDTO cemeteryDTO, BindingResult result, Model model,
                                 HttpServletRequest request, HttpServletResponse response) {
        cemeteryValidator.validate(cemeteryDTO, result);
        try {
            model.addAttribute("hasAdminRole", UserAuthenticationManager.hasAdminRole());
            if (result.hasErrors()) {
                model.addAttribute("view", true);
                return "cemetery/cemeteryDetailsPage";
            }
            CemeteryRestClient.update(cemeteryDTO.getId(), cemeteryDTO);
        } catch (HttpClientErrorException e) {
            try {
                ErrorDTO error = om.readValue(e.getResponseBodyAsString(), ErrorDTO.class);
                if (ErrorDTO.Status.UNAUTHORIZED_ACCESS.toString().equals(error.getStatus())) {
                    request.getSession().invalidate();
                    response.sendRedirect(request.getContextPath() + "/login");
                }
                model.addAttribute("errors", error.getErrors());
                return "cemetery/cemeteryDetailsPage";
            } catch (IOException ioe) {
                logger.error("Could not read value from ObjectMapper", ioe);
            }
        }
        return "redirect:/cemetery";
    }

    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public void applyFilter(HttpServletRequest request, HttpServletResponse response) {
        String searchCriteria = request.getParameter("searchCriteria");

        FilterDTO cemeteryFilterDTO = new FilterDTO();
        cemeteryFilterDTO.setSearchCriteria(searchCriteria);

        request.getSession().setAttribute(CEMETERY_FILTER, cemeteryFilterDTO);

        try {
            response.sendRedirect(request.getContextPath() + CEMETERY);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/refreshFilter", method = RequestMethod.POST)
    public void refreshFilter(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute(CEMETERY_FILTER);
        try {
            response.sendRedirect(request.getContextPath() + CEMETERY);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/filterParcels/{cemeteryId}", method = RequestMethod.GET)
     public void filterParcelsByCemeteryId(@PathVariable Integer cemeteryId, HttpServletRequest request, HttpServletResponse response) {
        FilterDTO parcelFilterDTO = new FilterDTO();
        parcelFilterDTO.setSearchCriteria("");
        parcelFilterDTO.setParentId(cemeteryId);

        request.getSession().setAttribute(ParcelController.PARCEL_FILTER, parcelFilterDTO);
        try {
            response.sendRedirect(request.getContextPath() + ParcelController.PARCEL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/addParcel/{cemeteryId}", method = RequestMethod.GET)
     public void addParcelForCemeteryId(@PathVariable Integer cemeteryId,Model model, HttpServletRequest request, HttpServletResponse response) {
        ParcelDTO parcelDTO = new ParcelDTO();
        parcelDTO.setCemeteryId(cemeteryId);

        request.getSession().setAttribute(PARCEL_DTO, parcelDTO);
        try {
            response.sendRedirect(request.getContextPath() + ParcelController.PARCEL + "/add");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/filterLogs/{cemeteryId}", method = RequestMethod.GET)
    public void filterLogs(HttpServletRequest request, @PathVariable Integer cemeteryId, HttpServletResponse response) {
        request.getSession().setAttribute(LogController.LOGS_TABLE_NAME, "cemeteries");
        request.getSession().setAttribute(LogController.LOGS_TABLE_ID, String.valueOf(cemeteryId));
        try {
            response.sendRedirect(request.getContextPath() + LogController.LOGS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
