package ro.InnovaTeam.cemeteryApp.controller.deceased;

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
    private static final String DECEASED = "/deceased";
    private static final String DECEASED_FILTER = "deceasedFilter";
    public static final int PAGE_SIZE = 20;

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
    public String renderHome(Model model, HttpServletRequest request) {
        FilterDTO deceasedFilterDTO = (FilterDTO) request.getSession().getAttribute(DECEASED_FILTER);
        deceasedFilterDTO = deceasedFilterDTO != null ? deceasedFilterDTO : new FilterDTO();
        String param = request.getParameter("pageNo");
        Integer pageNo = param != null ? Integer.valueOf(param) : 1;

        List<DeceasedDTO> deceasedDTOs;
        deceasedFilterDTO.setPageNo(pageNo);
        deceasedFilterDTO.setPageSize(PAGE_SIZE);
        deceasedDTOs = DeceasedRestClient.findByFilter(deceasedFilterDTO);

        int pages = deceasedDTOs.size()/PAGE_SIZE;
        model.addAttribute("pages", pages != 0 ? pages + 1 : pages);
        model.addAttribute("deceasedList", deceasedDTOs);
        return "deceased/deceasedPage";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String renderAddPage(Model model) {

        if (!model.containsAttribute("deceasedDTOExists")) {
            model.addAttribute("deceased", new DeceasedDTO());
        }
        return "deceased/deceasedDetailsPage";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("deceased") DeceasedDTO deceasedDTO, BindingResult result, Model model) {
        deceasedValidator.validate(deceasedDTO, result);
        if (result.hasErrors()) {
            model.addAttribute("deceasedDTOExists", true);
            return "deceased/deceasedDetailsPage";
        }
        DeceasedRestClient.add(deceasedDTO);
        return "redirect:" + DECEASED;
    }

    @RequestMapping(value = "/get/{id}")
    public String getById(@PathVariable Integer id, Model model) {
        DeceasedDTO deceasedDTO = DeceasedRestClient.findById(id);

        model.addAttribute("deceased", deceasedDTO);
        model.addAttribute("view", true);
        return "deceased/deceasedDetailsPage";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable Integer id) {
        try {
            DeceasedRestClient.delete(id);
        }
        catch (Exception e) {
            logger.error("Could not delete deceased with id: " + id, e);
        }
        return "redirect:" + DECEASED;
    }

    @RequestMapping(value = "/update")
    public String update(@ModelAttribute("deceased") DeceasedDTO deceasedDTO, BindingResult result, Model model) {
        deceasedValidator.validate(deceasedDTO, result);
        if (result.hasErrors()) {
            model.addAttribute("view", true);
            return "deceased/deceasedDetailsPage";
        }
        DeceasedRestClient.update(deceasedDTO.getId(), deceasedDTO);
        return "redirect:" + DECEASED;
    }

    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public void applyFilter(HttpServletRequest request, HttpServletResponse response) {
        String searchCriteria = request.getParameter("searchCriteria");
        String cemeteryId = request.getParameter("cemeteryId");
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
