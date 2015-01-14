package ro.InnovaTeam.cemeteryApp.controller.monument;

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
import ro.InnovaTeam.cemeteryApp.*;
import ro.InnovaTeam.cemeteryApp.controller.auth.UserAuthenticationManager;
import ro.InnovaTeam.cemeteryApp.controller.contract.ContractController;
import ro.InnovaTeam.cemeteryApp.controller.deceased.DeceasedController;
import ro.InnovaTeam.cemeteryApp.controller.grave.GraveController;
import ro.InnovaTeam.cemeteryApp.controller.log.LogController;
import ro.InnovaTeam.cemeteryApp.controller.parcel.ParcelController;
import ro.InnovaTeam.cemeteryApp.controller.structure_history.StructureHistoryController;
import ro.InnovaTeam.cemeteryApp.restClient.MonumentRestClient;

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
@RequestMapping("/monument")
public class MonumentController {

    private static final Logger logger = LoggerFactory.getLogger(MonumentController.class);
    public static final String MONUMENT = "/monument";
    public static final String MONUMENT_FILTER = "monumentFilter";
    public static final int PAGE_SIZE = 20;

    @Autowired
    @Qualifier("monumentValidator")
    private Validator monumentValidator;

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
        FilterDTO monumentFilterDTO = (FilterDTO) request.getSession().getAttribute(MONUMENT_FILTER);
        monumentFilterDTO = monumentFilterDTO != null ? monumentFilterDTO : new FilterDTO();
        String param = request.getParameter("pageNo");
        Integer pageNo = param != null ? Integer.valueOf(param) : 1;

        List<MonumentDTO> monuments;
        monumentFilterDTO.setPageNo(pageNo);
        monumentFilterDTO.setPageSize(PAGE_SIZE);
        monuments = MonumentRestClient.getByFilter(monumentFilterDTO);

        float pages =  MonumentRestClient.getMonumentCount(new FilterDTO(monumentFilterDTO.getSearchCriteria(),
                monumentFilterDTO.getParentId())) / (float) PAGE_SIZE;
        model.addAttribute("pages", Math.ceil(pages));
        model.addAttribute("monumentList", monuments);
        model.addAttribute("monumentPath", MONUMENT);
        model.addAttribute("hasAdminRole", UserAuthenticationManager.hasAdminRole());
        return "monument/monumentsPage";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String renderAddPage(Model model, HttpServletRequest request) {

        if (!model.containsAttribute("monumentDTOExists")) {
            MonumentDTO monumentDTO = (MonumentDTO) request.getSession().getAttribute(ParcelController.MONUMENT_DTO);
            model.addAttribute("monument", monumentDTO != null ? monumentDTO : new MonumentDTO());
            request.getSession().removeAttribute(ParcelController.MONUMENT_DTO);
        }
        model.addAttribute("hasAdminRole", UserAuthenticationManager.hasAdminRole());
        return "monument/monumentDetailsPage";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("monument") MonumentDTO monumentDTO, BindingResult result, Model model) {
        monumentValidator.validate(monumentDTO, result);
        if (result.hasErrors()) {
            model.addAttribute("monumentDTOExists", true);
            model.addAttribute("hasAdminRole", UserAuthenticationManager.hasAdminRole());
            return "monument/monumentDetailsPage";
        }
        MonumentRestClient.add(monumentDTO);
        return "redirect:" + MONUMENT;
    }

    @RequestMapping(value = "/get/{id}")
    public String getMonumentById(@PathVariable Integer id, Model model) {
        MonumentDTO monumentDTO = MonumentRestClient.findById(id);

        model.addAttribute("monument", monumentDTO);
        model.addAttribute("view", true);
        model.addAttribute("hasAdminRole", UserAuthenticationManager.hasAdminRole());
        return "monument/monumentDetailsPage";
    }

    @RequestMapping(value = "/delete/{id}")
    public String deleteMonument(@PathVariable Integer id) {
        try {
            MonumentRestClient.delete(id);
        }
        catch (Exception e) {
            logger.error("Could not delete Monument with id: " + id, e);
        }
        return "redirect:" + MONUMENT;
    }

    @RequestMapping(value = "/update")
    public String updateMonument(@ModelAttribute("monument") MonumentDTO monumentDTO, BindingResult result, Model model) {
        monumentValidator.validate(monumentDTO, result);
        if (result.hasErrors()) {
            model.addAttribute("view", true);
            model.addAttribute("hasAdminRole", UserAuthenticationManager.hasAdminRole());
            return "monument/monumentDetailsPage";
        }
        MonumentRestClient.update(monumentDTO.getId(), monumentDTO);
        return "redirect:" + MONUMENT;
    }

    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public void applyFilter(HttpServletRequest request, HttpServletResponse response) {
        String searchCriteria = request.getParameter("searchCriteria");
        String parcelId = request.getParameter("parcelId");
        //TODO validate data
        FilterDTO monumentFilterDTO = new FilterDTO();
        monumentFilterDTO.setSearchCriteria(searchCriteria);
        if(StringUtils.isNotEmpty(parcelId)) {
            monumentFilterDTO.setParentId(Integer.valueOf(parcelId));
        }

        request.getSession().setAttribute(MONUMENT_FILTER, monumentFilterDTO);

        try {
            response.sendRedirect(request.getContextPath() + MONUMENT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/refreshFilter", method = RequestMethod.POST)
    public void refreshFilter(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute(MONUMENT_FILTER);
        try {
            response.sendRedirect(request.getContextPath() + MONUMENT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/filterAction/{monumentId}/{type}", method = RequestMethod.GET)
    public void filterByMonumentId(@PathVariable Integer monumentId, @PathVariable String type, HttpServletRequest request, HttpServletResponse response) {
        FilterDTO filterDTO = new FilterDTO();
        filterDTO.setSearchCriteria("");
        filterDTO.setParentId(monumentId);
        String filter, redirectPath = "";

        switch (type) {
            case "contract":
                filter = ContractController.CONTRACT_FILTER;
                request.getSession().setAttribute(filter, filterDTO);
                redirectPath = ContractController.CONTRACT;
                break;
            case "structureHistory":
                filter = StructureHistoryController.STRUCTURE_HISTORY_FILTER;
                request.getSession().setAttribute(filter, filterDTO);
                redirectPath = StructureHistoryController.STRUCTURE_HISTORY;
                break;
            case "deceased":
                filter = DeceasedController.DECEASED_FILTER;
                request.getSession().setAttribute(filter, filterDTO);
                redirectPath = DeceasedController.DECEASED;
                break;
            case "logs":
                request.getSession().setAttribute(LogController.LOGS_TABLE_NAME, "monument");
                request.getSession().setAttribute(LogController.LOGS_TABLE_ID, String.valueOf(monumentId));
                redirectPath = LogController.LOGS;
        }

        try {
            response.sendRedirect(request.getContextPath() + redirectPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/addAction/{monumentId}/{type}", method = RequestMethod.GET)
    public void addForMonumentId(@PathVariable Integer monumentId, @PathVariable String type, HttpServletRequest request, HttpServletResponse response) {
        String redirectPath = "";
        switch (type) {
            case "contract":
                ContractDTO contractDTO = new ContractDTO();
                contractDTO.setStructureId(monumentId);
                request.getSession().setAttribute(GraveController.STRUCTURE_CONTRACT_DTO, contractDTO);
                redirectPath = ContractController.CONTRACT;
                break;
            case "structureHistory":
                StructureHistoryEntryDTO structureHistoryEntryDTO = new StructureHistoryEntryDTO();
                structureHistoryEntryDTO.setStructureId(monumentId);
                request.getSession().setAttribute(GraveController.STRUCTURE_HISTORY_DTO, structureHistoryEntryDTO);
                redirectPath = StructureHistoryController.STRUCTURE_HISTORY;
                break;
            case "deceased":
                DeceasedDTO deceasedDTO = new DeceasedDTO();
                deceasedDTO.setStructureId(monumentId);
                request.getSession().setAttribute(DeceasedController.DECEASED_DTO, deceasedDTO);
                redirectPath = DeceasedController.DECEASED;
                break;
        }
        try {
            response.sendRedirect(request.getContextPath() + redirectPath + "/add");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
