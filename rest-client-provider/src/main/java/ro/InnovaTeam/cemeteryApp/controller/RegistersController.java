package ro.InnovaTeam.cemeteryApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ro.InnovaTeam.cemeteryApp.FilterDTO;
import ro.InnovaTeam.cemeteryApp.registers.*;
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
    private static final String MONUMENT_REGISTRY_FILTER = "monumentRegistryFilter";
    private static final String MONUMENT_REGISTRY = "/monumentRegistry";
    private static final String DECEASED_REGISTRY_FILTER = "deceasedRegistryFilter";
    private static final String DECEASED_REGISTRY = "/deceasedRegistry";
    private static final String DECEASED_NO_CAREGIVER_REGISTRY_FILTER = "deceasedNoCaregiverRegistryFilter";
    private static final String DECEASED_NO_CAREGIVER_REGISTRY = "/deceasedNoCaregiverRegistry";
    private static final String REQUEST_REGISTRY_FILTER = "requestRegistryFilter";
    private static final String REQUEST_REGISTRY = "/requestRegistry";
    private static final String CONTRACT_REGISTRY_FILTER = "contractRegistryFilter";
    private static final String CONTRACT_REGISTRY = "/contractRegistry";
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

    @RequestMapping(value = MONUMENT_REGISTRY, method = RequestMethod.GET)
    public String showMonumentRegistry(Model model, HttpServletRequest request){
        FilterDTO filterDTO = (FilterDTO) request.getSession().getAttribute(MONUMENT_REGISTRY_FILTER);
        filterDTO = filterDTO != null ? filterDTO : new FilterDTO();
        String param = request.getParameter("pageNo");
        Integer pageNo = param != null ? Integer.valueOf(param) : 1;

        filterDTO.setPageNo(pageNo);
        filterDTO.setPageSize(PAGE_SIZE);
        filterDTO.setParentId(null);
        List<MonumentRegistryEntryDTO> entryDTOs = RegistryRestClient.getMonumentRegistry(filterDTO);

        float pages = RegistryRestClient.getMonumentRegistryCount(new FilterDTO(filterDTO.getSearchCriteria(),
                filterDTO.getParentId())) / (float) PAGE_SIZE;
        pages /= PAGE_SIZE;
        model.addAttribute("pages", Math.ceil(pages));

        model.addAttribute("monumentRegistryList", entryDTOs);
        return "registers/monumentRegistry";
    }

    @RequestMapping(value = DECEASED_REGISTRY, method = RequestMethod.GET)
    public String showDeceasedRegistry(Model model, HttpServletRequest request){
        FilterDTO filterDTO = (FilterDTO) request.getSession().getAttribute(DECEASED_REGISTRY_FILTER);
        filterDTO = filterDTO != null ? filterDTO : new FilterDTO();
        String param = request.getParameter("pageNo");
        String nameOrder = (String) request.getSession().getAttribute("nameOrder");
        nameOrder = nameOrder != null ? nameOrder : "ASC";
        String diedOnOrder = (String) request.getSession().getAttribute("diedOnOrder");
        diedOnOrder = diedOnOrder != null ? diedOnOrder : "ASC";
        Integer pageNo = param != null ? Integer.valueOf(param) : 1;

        filterDTO.setPageNo(pageNo);
        filterDTO.setPageSize(PAGE_SIZE);
        filterDTO.setParentId(null);
        List<DeceasedRegistryEntryDTO> entryDTOs = RegistryRestClient.getDeceasedRegistry(filterDTO, nameOrder, diedOnOrder);

        float pages = RegistryRestClient.getDeceasedRegistryCount(new FilterDTO(filterDTO.getSearchCriteria(),
                filterDTO.getParentId()), nameOrder, diedOnOrder) / (float) PAGE_SIZE;
        pages /= PAGE_SIZE;
        model.addAttribute("pages", Math.ceil(pages));

        model.addAttribute("deceasedRegistryList", entryDTOs);
        return "registers/deceasedRegistry";
    }

    @RequestMapping(value = DECEASED_NO_CAREGIVER_REGISTRY, method = RequestMethod.GET)
    public String showDeceasedNoCaregiverRegistry(Model model, HttpServletRequest request){
        FilterDTO filterDTO = (FilterDTO) request.getSession().getAttribute(DECEASED_NO_CAREGIVER_REGISTRY_FILTER);
        filterDTO = filterDTO != null ? filterDTO : new FilterDTO();
        String param = request.getParameter("pageNo");
        String nameOrder = (String) request.getSession().getAttribute("nameOrder");
        nameOrder = nameOrder != null ? nameOrder : "ASC";
        String diedOnOrder = (String) request.getSession().getAttribute("diedOnOrder");
        diedOnOrder = diedOnOrder != null ? diedOnOrder : "ASC";
        Integer pageNo = param != null ? Integer.valueOf(param) : 1;

        filterDTO.setPageNo(pageNo);
        filterDTO.setPageSize(PAGE_SIZE);
        filterDTO.setParentId(null);
        List<DeceasedNoCaregiverRegistryEntryDTO> entryDTOs = RegistryRestClient.getDeceasedNoCaregiverRegistry(filterDTO, nameOrder, diedOnOrder);

        float pages = RegistryRestClient.getDeceasedNoCaregiverRegistryCount(new FilterDTO(filterDTO.getSearchCriteria(),
                filterDTO.getParentId()), nameOrder, diedOnOrder) / (float) PAGE_SIZE;
        pages /= PAGE_SIZE;
        model.addAttribute("pages", Math.ceil(pages));

        model.addAttribute("deceasedNoCaregiverRegistryList", entryDTOs);
        return "registers/deceasedNoCaregiver";
    }

    @RequestMapping(value = REQUEST_REGISTRY, method = RequestMethod.GET)
    public String showRequestRegistry(Model model, HttpServletRequest request){
        FilterDTO filterDTO = (FilterDTO) request.getSession().getAttribute(REQUEST_REGISTRY_FILTER);
        filterDTO = filterDTO != null ? filterDTO : new FilterDTO();
        String param = request.getParameter("pageNo");
        Integer pageNo = param != null ? Integer.valueOf(param) : 1;

        filterDTO.setPageNo(pageNo);
        filterDTO.setPageSize(PAGE_SIZE);
        filterDTO.setParentId(null);
        List<RequestRegistryEntryDTO> entryDTOs = RegistryRestClient.getRequestRegistry(filterDTO);

        float pages = RegistryRestClient.getRequestRegistryCount(new FilterDTO(filterDTO.getSearchCriteria(),
                filterDTO.getParentId())) / (float) PAGE_SIZE;
        pages /= PAGE_SIZE;
        model.addAttribute("pages", Math.ceil(pages));

        model.addAttribute("requestRegistryList", entryDTOs);
        return "registers/requestRegistry";
    }

    @RequestMapping(value = CONTRACT_REGISTRY, method = RequestMethod.GET)
    public String showContractRegistry(Model model, HttpServletRequest request){
        FilterDTO filterDTO = (FilterDTO) request.getSession().getAttribute(CONTRACT_REGISTRY_FILTER);
        filterDTO = filterDTO != null ? filterDTO : new FilterDTO();
        String param = request.getParameter("pageNo");
        Integer pageNo = param != null ? Integer.valueOf(param) : 1;

        filterDTO.setPageNo(pageNo);
        filterDTO.setPageSize(PAGE_SIZE);
        filterDTO.setParentId(null);
        List<ContractRegistryEntryDTO> entryDTOs = RegistryRestClient.getContractRegistry(filterDTO);

        float pages = RegistryRestClient.getContractRegistryCount(new FilterDTO(filterDTO.getSearchCriteria(),
                filterDTO.getParentId())) / (float) PAGE_SIZE;
        pages /= PAGE_SIZE;
        model.addAttribute("pages", Math.ceil(pages));

        model.addAttribute("contractRegistryList", entryDTOs);
        return "registers/contractRegistry";
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
    public void refreshGraveFilter(HttpServletRequest request, HttpServletResponse response) {
        refreshFilter(request, response, GRAVE_REGISTRY_FILTER, REGISTERS + GRAVE_REGISTRY);
    }

    @RequestMapping(value = MONUMENT_REGISTRY + FILTER, method = RequestMethod.GET)
    public void applyMonumentFilter(HttpServletRequest request, HttpServletResponse response) {
        applyFilter(request, response, MONUMENT_REGISTRY_FILTER, REGISTERS + MONUMENT_REGISTRY);
    }

    @RequestMapping(value = MONUMENT_REGISTRY + REFRESH_FILTER, method = RequestMethod.POST)
    public void refreshMonumentFilter(HttpServletRequest request, HttpServletResponse response) {
        refreshFilter(request, response, MONUMENT_REGISTRY_FILTER, REGISTERS + MONUMENT_REGISTRY);
    }

    @RequestMapping(value = DECEASED_REGISTRY + FILTER, method = RequestMethod.GET)
    public void applyDeceasedFilter(HttpServletRequest request, HttpServletResponse response) {
        applyFilter(request, response, DECEASED_REGISTRY_FILTER, REGISTERS + DECEASED_REGISTRY);
        request.getSession().setAttribute("nameOrder", request.getParameter("nameOrder"));
        request.getSession().setAttribute("diedOnOrder", request.getParameter("diedOnOrder"));
    }

    @RequestMapping(value = DECEASED_REGISTRY + REFRESH_FILTER, method = RequestMethod.POST)
    public void refreshDeceasedFilter(HttpServletRequest request, HttpServletResponse response) {
        refreshFilter(request, response, DECEASED_REGISTRY_FILTER, REGISTERS + DECEASED_REGISTRY);
        request.getSession().removeAttribute("nameOrder");
        request.getSession().removeAttribute("diedOnOrder");
    }

    @RequestMapping(value = DECEASED_NO_CAREGIVER_REGISTRY + FILTER, method = RequestMethod.GET)
    public void applyDeceasedNoCaregiverFilter(HttpServletRequest request, HttpServletResponse response) {
        applyFilter(request, response, DECEASED_NO_CAREGIVER_REGISTRY_FILTER, REGISTERS + DECEASED_NO_CAREGIVER_REGISTRY);
        request.getSession().setAttribute("nameOrder", request.getParameter("nameOrder"));
        request.getSession().setAttribute("diedOnOrder", request.getParameter("diedOnOrder"));
    }

    @RequestMapping(value = DECEASED_NO_CAREGIVER_REGISTRY + REFRESH_FILTER, method = RequestMethod.POST)
    public void refreshDeceasedNoCaregiverFilter(HttpServletRequest request, HttpServletResponse response) {
        refreshFilter(request, response, DECEASED_NO_CAREGIVER_REGISTRY_FILTER, REGISTERS + DECEASED_NO_CAREGIVER_REGISTRY);
        request.getSession().removeAttribute("nameOrder");
        request.getSession().removeAttribute("diedOnOrder");
    }

    @RequestMapping(value = REQUEST_REGISTRY + FILTER, method = RequestMethod.GET)
    public void applyRequestFilter(HttpServletRequest request, HttpServletResponse response) {
        applyFilter(request, response, REQUEST_REGISTRY_FILTER, REGISTERS + REQUEST_REGISTRY);
    }

    @RequestMapping(value = REQUEST_REGISTRY + REFRESH_FILTER, method = RequestMethod.POST)
    public void refreshRequestFilter(HttpServletRequest request, HttpServletResponse response) {
        refreshFilter(request, response, REQUEST_REGISTRY_FILTER, REGISTERS + REQUEST_REGISTRY);
    }

    @RequestMapping(value = CONTRACT_REGISTRY + FILTER, method = RequestMethod.GET)
    public void applyContractFilter(HttpServletRequest request, HttpServletResponse response) {
        applyFilter(request, response, CONTRACT_REGISTRY_FILTER, REGISTERS + CONTRACT_REGISTRY);
    }

    @RequestMapping(value = CONTRACT_REGISTRY + REFRESH_FILTER, method = RequestMethod.POST)
    public void refreshContractFilter(HttpServletRequest request, HttpServletResponse response) {
        refreshFilter(request, response, CONTRACT_REGISTRY_FILTER, REGISTERS + CONTRACT_REGISTRY);
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
