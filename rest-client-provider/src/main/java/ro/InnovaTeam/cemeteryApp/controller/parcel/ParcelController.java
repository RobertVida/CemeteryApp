package ro.InnovaTeam.cemeteryApp.controller.parcel;

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
import ro.InnovaTeam.cemeteryApp.ParcelDTO;
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
    private static final String PARCEL = "/parcel";
    private static final String PARCEL_FILTER = "parcelFilter";
    public static final int PAGE_SIZE = 20;

    @Autowired
    @Qualifier("parcelValidator")
    private Validator parcelValidator;

    @RequestMapping
    public String renderHome(Model model, HttpServletRequest request) {
        FilterDTO parcelFilterDTO = (FilterDTO) request.getSession().getAttribute(PARCEL_FILTER);
        parcelFilterDTO = parcelFilterDTO != null ? parcelFilterDTO : new FilterDTO();
        String param = request.getParameter("pageNo");
        Integer pageNo = param != null ? Integer.valueOf(param) : 1;

        List<ParcelDTO> parcels;
        parcelFilterDTO.setPageNo(pageNo);
        parcelFilterDTO.setPageSize(PAGE_SIZE);
        parcels = ParcelRestClient.getParcelsByFilter(parcelFilterDTO);

        int pages = parcels.size()/PAGE_SIZE;
        model.addAttribute("pages", pages != 0 ? pages + 1 : pages);
        model.addAttribute("parcelList", parcels);
        model.addAttribute("parcelPath", PARCEL);
        return "parcel/parcelPage";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String renderAddPage(Model model) {

        if (!model.containsAttribute("parcelDTOExists")) {
            model.addAttribute("parcel", new ParcelDTO());
        }
        return "parcel/parcelDetailsPage";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("parcel") ParcelDTO parcelDTO, BindingResult result, Model model) {
        parcelValidator.validate(parcelDTO, result);
        if (result.hasErrors()) {
            model.addAttribute("parcelDTOExists", true);
            return "parcel/parcelDetailsPage";
        }
        ParcelRestClient.add(parcelDTO);
        return "redirect:" + PARCEL;
    }

    @RequestMapping(value = "/get/{id}")
    public String getParcelById(@PathVariable Integer id, Model model) {
        ParcelDTO ParcelDTO = ParcelRestClient.findById(id);

        model.addAttribute("parcel", ParcelDTO);
        model.addAttribute("view", true);
        return "parcel/parcelDetailsPage";
    }

    @RequestMapping(value = "/delete/{id}")
    public String deleteParcel(@PathVariable Integer id) {
        try {
            ParcelRestClient.delete(id);
        }
        catch (Exception e) {
            logger.error("Could not delete Parcel with id: " + id, e);
        }
        return "redirect:" + PARCEL;
    }

    @RequestMapping(value = "/update")
    public String updateParcel(@ModelAttribute("parcel") ParcelDTO parcelDTO, BindingResult result, Model model) {
        parcelValidator.validate(parcelDTO, result);
        if (result.hasErrors()) {
            model.addAttribute("view", true);
            return "parcel/parcelDetailsPage";
        }
        ParcelRestClient.update(parcelDTO.getId(), parcelDTO);
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
}
