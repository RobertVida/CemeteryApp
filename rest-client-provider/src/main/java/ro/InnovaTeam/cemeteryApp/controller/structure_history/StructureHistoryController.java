package ro.InnovaTeam.cemeteryApp.controller.structure_history;

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
import ro.InnovaTeam.cemeteryApp.DeceasedDTO;
import ro.InnovaTeam.cemeteryApp.FilterDTO;
import ro.InnovaTeam.cemeteryApp.StructureHistoryEntryDTO;
import ro.InnovaTeam.cemeteryApp.controller.auth.UserAuthenticationManager;
import ro.InnovaTeam.cemeteryApp.restClient.DeceasedRestClient;
import ro.InnovaTeam.cemeteryApp.restClient.StructureHistoryRestClient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Tudor on 1/5/2015.
 */
@Controller
@RequestMapping("/structureHistory")
public class StructureHistoryController {

    private static final Logger logger = LoggerFactory.getLogger(StructureHistoryController.class);
    private static final String STRUCTURE_HISTORY = "/structureHistory";
    private static final String STRUCTURE_HISTORY_FILTER = "structureHistoryFilter";
    public static final int PAGE_SIZE = 20;

    @Autowired
    @Qualifier("structureValidator")
    private Validator structureValidator;

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
        FilterDTO structureHistoryFilterDTO = (FilterDTO) request.getSession().getAttribute(STRUCTURE_HISTORY_FILTER);
        structureHistoryFilterDTO = structureHistoryFilterDTO != null ? structureHistoryFilterDTO : new FilterDTO();
        String param = request.getParameter("pageNo");
        Integer pageNo = param != null ? Integer.valueOf(param) : 1;

        List<StructureHistoryEntryDTO> structureHistoryEntryDTOs;
        structureHistoryFilterDTO.setPageNo(pageNo);
        structureHistoryFilterDTO.setPageSize(PAGE_SIZE);
        structureHistoryEntryDTOs = StructureHistoryRestClient.findByFilter(structureHistoryFilterDTO);

        float pages = StructureHistoryRestClient.getStructureCount(new FilterDTO(structureHistoryFilterDTO.getSearchCriteria(),
                structureHistoryFilterDTO.getParentId())) / (float) PAGE_SIZE;
        model.addAttribute("pages", Math.ceil(pages));
        model.addAttribute("structureList", structureHistoryEntryDTOs);
        model.addAttribute("hasAdminRole", UserAuthenticationManager.hasAdminRole());
        return "structure_history/structureHistoryPage";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String renderAddPage(Model model) {

        if (!model.containsAttribute("structureHistoryEntryDTOExists")) {
            model.addAttribute("structure_history", new StructureHistoryEntryDTO());
        }
        model.addAttribute("hasAdminRole", UserAuthenticationManager.hasAdminRole());
        return "structure_history/structureHistoryEntryDetailsPage";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("structure_history") StructureHistoryEntryDTO structureHistoryEntryDTO, BindingResult result, Model model) {
        structureValidator.validate(structureHistoryEntryDTO, result);
        if (result.hasErrors()) {
            model.addAttribute("structureHistoryEntryDTOExists", true);
            model.addAttribute("hasAdminRole", UserAuthenticationManager.hasAdminRole());
            return "structure_history/structureHistoryEntryDetailsPage";
        }
        StructureHistoryRestClient.add(structureHistoryEntryDTO);
        return "redirect:" + STRUCTURE_HISTORY;
    }

    @RequestMapping(value = "/get/{id}")
    public String getById(@PathVariable Integer id, Model model) {
        StructureHistoryEntryDTO structureHistoryEntryDTO = StructureHistoryRestClient.findById(id);

        model.addAttribute("structure_history", structureHistoryEntryDTO);
        model.addAttribute("view", true);
        model.addAttribute("hasAdminRole", UserAuthenticationManager.hasAdminRole());
        return "structure_history/structureHistoryEntryDetailsPage";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable Integer id) {
        try {
            StructureHistoryRestClient.delete(id);
        } catch (Exception e) {
            logger.error("Could not delete structure with id: " + id, e);
        }
        return "redirect:" + STRUCTURE_HISTORY;
    }

    @RequestMapping(value = "/update")
    public String update(@ModelAttribute("structure_history") StructureHistoryEntryDTO structureHistoryEntryDTO, BindingResult result, Model model) {
        structureValidator.validate(structureHistoryEntryDTO, result);
        if (result.hasErrors()) {
            model.addAttribute("view", true);
            model.addAttribute("hasAdminRole", UserAuthenticationManager.hasAdminRole());
            return "structure_history/structureHistoryEntryDetailsPage";
        }
        StructureHistoryRestClient.update(structureHistoryEntryDTO.getId(), structureHistoryEntryDTO);
        return "redirect:" + STRUCTURE_HISTORY;
    }

    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public void applyFilter(HttpServletRequest request, HttpServletResponse response) {
        String searchCriteria = request.getParameter("searchCriteria");
        String cemeteryId = request.getParameter("structureId");
        //TODO: validate cemeteryId
        FilterDTO deceasedFilterDTO = new FilterDTO();
        deceasedFilterDTO.setSearchCriteria(searchCriteria);
        if (StringUtils.isNotEmpty(cemeteryId)) {
            deceasedFilterDTO.setParentId(Integer.valueOf(cemeteryId));
        }
        request.getSession().setAttribute(STRUCTURE_HISTORY_FILTER, deceasedFilterDTO);

        try {
            response.sendRedirect(request.getContextPath() + STRUCTURE_HISTORY);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/refreshFilter", method = RequestMethod.POST)
    public void refreshFilter(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute(STRUCTURE_HISTORY_FILTER);
        try {
            response.sendRedirect(request.getContextPath() + STRUCTURE_HISTORY);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}