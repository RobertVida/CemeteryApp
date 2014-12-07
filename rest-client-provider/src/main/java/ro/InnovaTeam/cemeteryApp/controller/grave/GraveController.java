package ro.InnovaTeam.cemeteryApp.controller.grave;

import org.apache.commons.lang3.StringUtils;
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
import ro.InnovaTeam.cemeteryApp.FilterDTO;
import ro.InnovaTeam.cemeteryApp.GraveDTO;
import ro.InnovaTeam.cemeteryApp.restClient.GraveRestClient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Tudor on 11/22/2014.
 */
@Controller
@RequestMapping("/grave")
public class GraveController {

    private static final Logger logger = LoggerFactory.getLogger(GraveController.class);
    private static final String GRAVE = "/grave";
    private static final String GRAVE_FILTER = "graveFilter";
    public static final int PAGE_SIZE = 20;

    @Autowired
    @Qualifier("graveValidator")
    private Validator graveValidator;

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

        int pages = graves.size()/PAGE_SIZE;
        model.addAttribute("pages", pages != 0 ? pages + 1 : pages);
        model.addAttribute("graveList", graves);
        model.addAttribute("gravePath", GRAVE);
        return "grave/gravesPage";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String renderAddPage(Model model) {

        if (!model.containsAttribute("graveDTOExists")) {
            model.addAttribute("grave", new GraveDTO());
        }
        return "grave/graveDetailsPage";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("grave") GraveDTO graveDTO, BindingResult result, Model model) {
        graveValidator.validate(graveDTO, result);
        if (result.hasErrors()) {
            model.addAttribute("graveDTOExists", true);
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
            return "grave/graveDetailsPage";
        }
        GraveRestClient.update(graveDTO.getId(), graveDTO);
        return "redirect:" + GRAVE;
    }

    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public void applyFilter(HttpServletRequest request, HttpServletResponse response) {
        String searchCriteria = request.getParameter("searchCriteria");
        String parcelId = request.getParameter("parcelId");
        //TODO validate data
        FilterDTO graveFilterDTO = new FilterDTO();
        graveFilterDTO.setSearchCriteria(searchCriteria);
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

}
