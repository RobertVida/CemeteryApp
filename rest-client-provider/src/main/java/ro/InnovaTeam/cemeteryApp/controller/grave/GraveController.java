package ro.InnovaTeam.cemeteryApp.controller.grave;

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
import ro.InnovaTeam.cemeteryApp.ContractDTO;
import ro.InnovaTeam.cemeteryApp.FilterDTO;
import ro.InnovaTeam.cemeteryApp.GraveDTO;
import ro.InnovaTeam.cemeteryApp.StructureHistoryEntryDTO;
import ro.InnovaTeam.cemeteryApp.controller.auth.UserAuthenticationManager;
import ro.InnovaTeam.cemeteryApp.controller.contract.ContractController;
import ro.InnovaTeam.cemeteryApp.controller.parcel.ParcelController;
import ro.InnovaTeam.cemeteryApp.controller.structure_history.StructureHistoryController;
import ro.InnovaTeam.cemeteryApp.restClient.GraveRestClient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Tudor on 11/22/2014.
 */
@Controller
@RequestMapping("/grave")
public class GraveController {

    private static final Logger logger = LoggerFactory.getLogger(GraveController.class);
    public static final String GRAVE = "/grave";
    public static final String GRAVE_FILTER = "graveFilter";
    public static final String STRUCTURE_HISTORY_DTO = "structureGraveHistoryDTO";
    public static final String STRUCTURE_CONTRACT_DTO = "structureGraveDTO";
    public static final int PAGE_SIZE = 20;

    @Autowired
    @Qualifier("graveValidator")
    private Validator graveValidator;

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
        FilterDTO graveFilterDTO = (FilterDTO) request.getSession().getAttribute(GRAVE_FILTER);
        graveFilterDTO = graveFilterDTO != null ? graveFilterDTO : new FilterDTO();
        String param = request.getParameter("pageNo");
        Integer pageNo = param != null ? Integer.valueOf(param) : 1;

        List<GraveDTO> graves;
        graveFilterDTO.setPageNo(pageNo);
        graveFilterDTO.setPageSize(PAGE_SIZE);
        graves = GraveRestClient.getByFilter(graveFilterDTO);

        float pages = GraveRestClient.getGraveCount(new FilterDTO(graveFilterDTO.getSearchCriteria(),
                graveFilterDTO.getParentId())) / (float) PAGE_SIZE;
        model.addAttribute("pages", Math.ceil(pages));
        model.addAttribute("graveList", graves);
        model.addAttribute("gravePath", GRAVE);
        model.addAttribute("hasAdminRole", UserAuthenticationManager.hasAdminRole());
        return "grave/gravesPage";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String renderAddPage(Model model, HttpServletRequest request) {

        if (!model.containsAttribute("graveDTOExists")) {
            GraveDTO graveDTO = (GraveDTO) request.getSession().getAttribute(ParcelController.GRAVE_DTO);
            model.addAttribute("grave", graveDTO != null ? graveDTO : new GraveDTO());
            request.getSession().removeAttribute(ParcelController.GRAVE_DTO);
        }
        model.addAttribute("hasAdminRole", UserAuthenticationManager.hasAdminRole());
        return "grave/graveDetailsPage";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("grave") GraveDTO graveDTO, BindingResult result, Model model) {
        graveValidator.validate(graveDTO, result);
        if (result.hasErrors()) {
            model.addAttribute("graveDTOExists", true);
            model.addAttribute("hasAdminRole", UserAuthenticationManager.hasAdminRole());
            return "grave/graveDetailsPage";
        }
        GraveRestClient.add(graveDTO);
        return "redirect:" + GRAVE;
    }

    @RequestMapping(value = "/get/{id}")
    public String getGraveById(@PathVariable Integer id, Model model) {
        GraveDTO graveDTO = GraveRestClient.findById(id);

        model.addAttribute("grave", graveDTO);
        model.addAttribute("view", true);
        model.addAttribute("hasAdminRole", UserAuthenticationManager.hasAdminRole());
        return "grave/graveDetailsPage";
    }

    @RequestMapping(value = "/delete/{id}")
    public String deleteGrave(@PathVariable Integer id) {
        try {
            GraveRestClient.delete(id);
        }
        catch (Exception e) {
            logger.error("Could not delete Grave with id: " + id, e);
        }
        return "redirect:" + GRAVE;
    }

    @RequestMapping(value = "/update")
    public String updateGrave(@ModelAttribute("grave") GraveDTO graveDTO, BindingResult result, Model model) {
        graveValidator.validate(graveDTO, result);
        if (result.hasErrors()) {
            model.addAttribute("view", true);
            model.addAttribute("hasAdminRole", UserAuthenticationManager.hasAdminRole());
            return "grave/graveDetailsPage";
        }
        GraveRestClient.update(graveDTO.getId(), graveDTO);
        return "redirect:" + GRAVE;
    }

    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public void applyFilter(HttpServletRequest request, HttpServletResponse response) {
        String parcelId = request.getParameter("parcelId");
        //TODO validate data
        FilterDTO graveFilterDTO = new FilterDTO();
        if(StringUtils.isNotEmpty(parcelId)) {
            graveFilterDTO.setParentId(Integer.valueOf(parcelId));
        }

        request.getSession().setAttribute(GRAVE_FILTER, graveFilterDTO);

        try {
            response.sendRedirect(request.getContextPath() + GRAVE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/refreshFilter", method = RequestMethod.POST)
    public void refreshFilter(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute(GRAVE_FILTER);
        try {
            response.sendRedirect(request.getContextPath() + GRAVE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/filterAction/{graveId}/{type}", method = RequestMethod.GET)
    public void filterByGraveId(@PathVariable Integer graveId, @PathVariable String type, HttpServletRequest request, HttpServletResponse response) {
        FilterDTO filterDTO = new FilterDTO();
        filterDTO.setSearchCriteria("");
        filterDTO.setParentId(graveId);
        String filter = "contract".equals(type) ? ContractController.CONTRACT_FILTER : StructureHistoryController.STRUCTURE_HISTORY_FILTER;
        String redirectPath = "contract".equals(type) ? ContractController.CONTRACT : StructureHistoryController.STRUCTURE_HISTORY;

        request.getSession().setAttribute(filter, filterDTO);
        try {
            response.sendRedirect(request.getContextPath() + redirectPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/addAction/{graveId}/{type}", method = RequestMethod.GET)
    public void addForGraveId(@PathVariable Integer graveId, @PathVariable String type, HttpServletRequest request, HttpServletResponse response) {
        String redirectPath;
        if ("contract".equals(type)) {
            ContractDTO contractDTO = new ContractDTO();
            contractDTO.setStructureId(graveId);
            request.getSession().setAttribute(STRUCTURE_CONTRACT_DTO, contractDTO);
            redirectPath = ContractController.CONTRACT;
        } else {
            StructureHistoryEntryDTO structureHistoryEntryDTO = new StructureHistoryEntryDTO();
            structureHistoryEntryDTO.setStructureId(graveId);
            request.getSession().setAttribute(STRUCTURE_HISTORY_DTO, structureHistoryEntryDTO);
            redirectPath = StructureHistoryController.STRUCTURE_HISTORY;
        }
        try {
            response.sendRedirect(request.getContextPath() + redirectPath + "/add");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
