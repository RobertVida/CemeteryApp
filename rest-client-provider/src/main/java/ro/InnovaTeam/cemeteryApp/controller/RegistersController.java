package ro.InnovaTeam.cemeteryApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ro.InnovaTeam.cemeteryApp.FilterDTO;
import ro.InnovaTeam.cemeteryApp.registers.BurialRegistryEntryDTO;
import ro.InnovaTeam.cemeteryApp.registers.GraveRegistryEntryDTO;
import ro.InnovaTeam.cemeteryApp.restClient.RegistryRestClient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Catalin Sorecau on 11/15/2014.
 */
@Controller
@RequestMapping("/registers")
public class RegistersController {

    private static final String BURIAL_REGISTRY_FILTER = "burialRegistryFilter";
    private static final String BURIAL_REGISTRY = "/burialRegistry";
    private static final String GRAVE_REGISTRY_FILTER = "graveRegistryFilter";
    private static final String GRAVE_REGISTRY = "/graveRegistry";
    private static final String FILTER = "/filter";
    private static final String REGISTERS = "/registers";
    private static final String REFRESH_FILTER = "/refreshFilter";
    public static final int PAGE_SIZE = 20;

    @RequestMapping(value = BURIAL_REGISTRY, method = RequestMethod.GET)
    public String showBurialRegistry(Model model, HttpServletRequest request){
        FilterDTO filterDTO = (FilterDTO) request.getSession().getAttribute(BURIAL_REGISTRY_FILTER);
        filterDTO = filterDTO != null ? filterDTO : new FilterDTO();
        String param = request.getParameter("pageNo");
        Integer pageNo = param != null ? Integer.valueOf(param) : 1;

        filterDTO.setPageNo(pageNo);
        filterDTO.setPageSize(PAGE_SIZE);
        filterDTO.setParentId(null);
        List<BurialRegistryEntryDTO> entryDTOs = RegistryRestClient.getBurialRegistry(filterDTO);

        float pages = RegistryRestClient.getBurialRegistryCount(new FilterDTO(filterDTO.getSearchCriteria(),
                filterDTO.getParentId())) / (float) PAGE_SIZE;
        pages /= PAGE_SIZE;
        model.addAttribute("pages", Math.ceil(pages));

        model.addAttribute("burialRegistryList", entryDTOs);
        return "registers/burialRegistry";
    }

    @RequestMapping(value = GRAVE_REGISTRY, method = RequestMethod.GET)
    public String showGraveRegistry(Model model, HttpServletRequest request){
        FilterDTO filterDTO = (FilterDTO) request.getSession().getAttribute(GRAVE_REGISTRY_FILTER);
        filterDTO = filterDTO != null ? filterDTO : new FilterDTO();
        String param = request.getParameter("pageNo");
        Integer pageNo = param != null ? Integer.valueOf(param) : 1;

        filterDTO.setPageNo(pageNo);
        filterDTO.setPageSize(PAGE_SIZE);
        filterDTO.setParentId(null);
        List<GraveRegistryEntryDTO> entryDTOs = RegistryRestClient.getGraveRegistry(filterDTO);

        float pages = RegistryRestClient.getGraveRegistryCount(new FilterDTO(filterDTO.getSearchCriteria(),
                filterDTO.getParentId())) / (float) PAGE_SIZE;
        pages /= PAGE_SIZE;
        model.addAttribute("pages", Math.ceil(pages));

        model.addAttribute("graveRegistryList", entryDTOs);
        return "registers/graveRegistry";
    }

    @RequestMapping(value = BURIAL_REGISTRY + FILTER, method = RequestMethod.GET)
    public void applyBurialFilter(HttpServletRequest request, HttpServletResponse response) {
        applyFilter(request, response, BURIAL_REGISTRY_FILTER, REGISTERS + BURIAL_REGISTRY);
    }

    @RequestMapping(value = BURIAL_REGISTRY + REFRESH_FILTER, method = RequestMethod.POST)
    public void refreshBurialFilter(HttpServletRequest request, HttpServletResponse response) {
        refreshFilter(request, response, BURIAL_REGISTRY_FILTER, REGISTERS + BURIAL_REGISTRY);
    }

    @RequestMapping(value = GRAVE_REGISTRY + FILTER, method = RequestMethod.GET)
    public void applyGraveFilter(HttpServletRequest request, HttpServletResponse response) {
        applyFilter(request, response, GRAVE_REGISTRY_FILTER, REGISTERS + GRAVE_REGISTRY);
    }

    @RequestMapping(value = GRAVE_REGISTRY + REFRESH_FILTER, method = RequestMethod.POST)
    public void refressGravehFilter(HttpServletRequest request, HttpServletResponse response) {
        refreshFilter(request, response, GRAVE_REGISTRY_FILTER, REGISTERS + GRAVE_REGISTRY);
    }

    private void refreshFilter(HttpServletRequest request, HttpServletResponse response, String filterName, String redirect) {
        request.getSession().removeAttribute(filterName);
        try {
            response.sendRedirect(request.getContextPath() + redirect);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void applyFilter(HttpServletRequest request, HttpServletResponse response, String filterName, String redirect) {
        String searchCriteria = request.getParameter("searchCriteria");

        FilterDTO filterDTO = new FilterDTO();
        filterDTO.setSearchCriteria(searchCriteria);

        request.getSession().setAttribute(filterName, filterDTO);

        try {
            response.sendRedirect(request.getContextPath() + redirect);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
