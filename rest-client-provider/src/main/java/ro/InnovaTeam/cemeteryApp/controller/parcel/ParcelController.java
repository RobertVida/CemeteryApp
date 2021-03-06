package ro.InnovaTeam.cemeteryApp.controller.parcel;

import org.apache.commons.lang3.StringUtils;
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
import org.springframework.web.client.HttpServerErrorException;
import ro.InnovaTeam.cemeteryApp.*;
import ro.InnovaTeam.cemeteryApp.controller.auth.UserAuthenticationManager;
import ro.InnovaTeam.cemeteryApp.controller.cemetery.CemeteryController;
import ro.InnovaTeam.cemeteryApp.controller.grave.GraveController;
import ro.InnovaTeam.cemeteryApp.controller.log.LogController;
import ro.InnovaTeam.cemeteryApp.controller.monument.MonumentController;
import ro.InnovaTeam.cemeteryApp.restClient.ParcelRestClient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Catalin Sorecau on 11/24/2014.
 */
@Controller
@RequestMapping("/parcel")
public class ParcelController {

    private static final Logger logger = LoggerFactory.getLogger(ParcelController.class);
    public static final String PARCEL = "/parcel";
    public static final String PARCEL_FILTER = "parcelFilter";
    public static final String GRAVE_DTO = "graveDTO";
    public static final String MONUMENT_DTO = "monumentDTO";
    public static final int PAGE_SIZE = 20;
    private ObjectMapper om = new ObjectMapper();

    @Autowired
    @Qualifier("parcelValidator")
    private Validator parcelValidator;

    @RequestMapping
    public String renderHome(Model model, HttpServletRequest request, HttpServletResponse response) {
        FilterDTO parcelFilterDTO = (FilterDTO) request.getSession().getAttribute(PARCEL_FILTER);
        parcelFilterDTO = parcelFilterDTO != null ? parcelFilterDTO : new FilterDTO();
        String param = request.getParameter("pageNo");
        Integer pageNo = param != null ? Integer.valueOf(param) : 1;

        List<ParcelDTO> parcels;
        parcelFilterDTO.setPageNo(pageNo);
        parcelFilterDTO.setPageSize(PAGE_SIZE);
        try {
            parcels = ParcelRestClient.getParcelsByFilter(parcelFilterDTO);

            float pages = ParcelRestClient.getParcelCount(new FilterDTO(parcelFilterDTO.getSearchCriteria(),
                    parcelFilterDTO.getParentId())) / (float) PAGE_SIZE;
            model.addAttribute("pages", new Double(Math.ceil(pages)).intValue());
            model.addAttribute("parcelList", parcels);
            model.addAttribute("parcelPath", PARCEL);
            model.addAttribute("hasAdminRole", UserAuthenticationManager.hasAdminRole());
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
        return "parcel/parcelPage";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String renderAddPage(Model model, HttpServletRequest request, HttpServletResponse response) {

        if (!model.containsAttribute("parcelDTOExists")) {
            ParcelDTO parcelDTO = (ParcelDTO) request.getSession().getAttribute(CemeteryController.PARCEL_DTO);
            model.addAttribute("parcel", parcelDTO != null ? parcelDTO : new ParcelDTO());
            request.getSession().removeAttribute(CemeteryController.PARCEL_DTO);
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
                return "parcel/parcelPage";
            } catch (IOException ioe) {
                logger.error("Could not read value from ObjectMapper", ioe);
            }
        }
        return "parcel/parcelDetailsPage";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("parcel") ParcelDTO parcelDTO, BindingResult result, Model model,
                      HttpServletRequest request, HttpServletResponse response) {
        parcelValidator.validate(parcelDTO, result);
        try {
            model.addAttribute("hasAdminRole", UserAuthenticationManager.hasAdminRole());
            if (result.hasErrors()) {
                model.addAttribute("parcelDTOExists", true);
                return "parcel/parcelDetailsPage";
            }
            ParcelRestClient.add(parcelDTO);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            try {
                ErrorDTO error = om.readValue(e.getResponseBodyAsString(), ErrorDTO.class);
                if (ErrorDTO.Status.UNAUTHORIZED_ACCESS.toString().equals(error.getStatus())) {
                    request.getSession().invalidate();
                    response.sendRedirect(request.getContextPath() + "/login");
                }
                model.addAttribute("errors", error.getErrors());
                return "parcel/parcelDetailsPage";
            } catch (IOException ioe) {
                logger.error("Could not read value from ObjectMapper", ioe);
            }
        }
        return "redirect:" + PARCEL;
    }

    @RequestMapping(value = "/get/{id}")
    public String getParcelById(@PathVariable Integer id, Model model, HttpServletRequest request, HttpServletResponse response) {
        try {
            ParcelDTO ParcelDTO = ParcelRestClient.findById(id);
            model.addAttribute("parcel", ParcelDTO);
            model.addAttribute("hasAdminRole", UserAuthenticationManager.hasAdminRole());
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            try {
                ErrorDTO error = om.readValue(e.getResponseBodyAsString(), ErrorDTO.class);
                if (ErrorDTO.Status.UNAUTHORIZED_ACCESS.toString().equals(error.getStatus())) {
                    request.getSession().invalidate();
                    response.sendRedirect(request.getContextPath() + "/login");
                }
                model.addAttribute("errors", error.getErrors());
                return "parcel/parcelPage";
            } catch (IOException ioe) {
                logger.error("Could not read value from ObjectMapper", ioe);
            }
        }
        model.addAttribute("view", true);
        return "parcel/parcelDetailsPage";
    }

    @RequestMapping(value = "/delete/{id}")
    public String deleteParcel(@PathVariable Integer id, HttpServletResponse response, HttpServletRequest request, Model model) {
        try {
            ParcelRestClient.delete(id);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            try {
                ErrorDTO error = om.readValue(e.getResponseBodyAsString(), ErrorDTO.class);
                if (ErrorDTO.Status.UNAUTHORIZED_ACCESS.toString().equals(error.getStatus())) {
                    request.getSession().invalidate();
                    response.sendRedirect(request.getContextPath() + "/login");
                }
                model.addAttribute("errors", error.getErrors());
                return "parcel/parcelPage";
            } catch (IOException ioe) {
                logger.error("Could not read value from ObjectMapper", ioe);
            }
        }
        return "redirect:" + PARCEL;
    }

    @RequestMapping(value = "/update")
    public String updateParcel(@ModelAttribute("parcel") ParcelDTO parcelDTO, BindingResult result, Model model,
                               HttpServletRequest request, HttpServletResponse response) {
        parcelValidator.validate(parcelDTO, result);
        try {
            model.addAttribute("hasAdminRole", UserAuthenticationManager.hasAdminRole());
            if (result.hasErrors()) {
                model.addAttribute("view", true);
                return "parcel/parcelDetailsPage";
            }
            ParcelRestClient.update(parcelDTO.getId(), parcelDTO);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            try {
                ErrorDTO error = om.readValue(e.getResponseBodyAsString(), ErrorDTO.class);
                if (ErrorDTO.Status.UNAUTHORIZED_ACCESS.toString().equals(error.getStatus())) {
                    request.getSession().invalidate();
                    response.sendRedirect(request.getContextPath() + "/login");
                }
                model.addAttribute("errors", error.getErrors());
                return "parcel/parcelDetailsPage";
            } catch (IOException ioe) {
                logger.error("Could not read value from ObjectMapper", ioe);
            }
        }
        return "redirect:" + PARCEL;
    }

    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public void applyFilter(HttpServletRequest request, HttpServletResponse response) {
        String searchCriteria = request.getParameter("searchCriteria");
        String cemeteryId = request.getParameter("cemeteryId");
        //TODO: validate cemeteryId
        FilterDTO parcelFilterDTO = new FilterDTO();
        parcelFilterDTO.setSearchCriteria(searchCriteria);
        if (StringUtils.isNotEmpty(cemeteryId)) {
            parcelFilterDTO.setParentId(Integer.valueOf(cemeteryId));
        }

        request.getSession().setAttribute(PARCEL_FILTER, parcelFilterDTO);

        try {
            response.sendRedirect(request.getContextPath() + PARCEL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/refreshFilter", method = RequestMethod.POST)
    public void refreshFilter(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute(PARCEL_FILTER);
        try {
            response.sendRedirect(request.getContextPath() + PARCEL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/filterAction/{parcelId}/{type}", method = RequestMethod.GET)
    public void filterStructureByParcelId(@PathVariable Integer parcelId, @PathVariable String type, HttpServletRequest request, HttpServletResponse response) {
        FilterDTO structureFilterDTO = new FilterDTO();
        structureFilterDTO.setSearchCriteria("");
        structureFilterDTO.setParentId(parcelId);
        String filterStructure = "grave".equals(type) ? GraveController.GRAVE_FILTER : MonumentController.MONUMENT_FILTER;
        String redirectPath = "grave".equals(type) ? GraveController.GRAVE : MonumentController.MONUMENT;

        request.getSession().setAttribute(filterStructure, structureFilterDTO);
        try {
            response.sendRedirect(request.getContextPath() + redirectPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/filterLogs/{parcelId}", method = RequestMethod.GET)
    public void filterLogs(HttpServletRequest request, @PathVariable Integer parcelId, HttpServletResponse response) {
        request.getSession().setAttribute(LogController.LOGS_TABLE_NAME, "parcel");
        request.getSession().setAttribute(LogController.LOGS_TABLE_ID, String.valueOf(parcelId));
        try {
            response.sendRedirect(request.getContextPath() + LogController.LOGS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/addStructure/{parcelId}/{type}", method = RequestMethod.GET)
    public void addStructureForParcelId(@PathVariable Integer parcelId, @PathVariable String type, HttpServletRequest request, HttpServletResponse response) {
        String redirectPath;
        if ("grave".equals(type)) {
            GraveDTO graveDTO = new GraveDTO();
            graveDTO.setParcelId(parcelId);
            request.getSession().setAttribute(GRAVE_DTO, graveDTO);
            redirectPath = GraveController.GRAVE;
        } else {
            MonumentDTO monumentDTO = new MonumentDTO();
            monumentDTO.setParcelId(parcelId);
            request.getSession().setAttribute(MONUMENT_DTO, monumentDTO);
            redirectPath = MonumentController.MONUMENT;
        }
        try {
            response.sendRedirect(request.getContextPath() + redirectPath + "/add");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
