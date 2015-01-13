package ro.InnovaTeam.cemeteryApp.controller.contract;

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
import org.springframework.web.servlet.ModelAndView;
import ro.InnovaTeam.cemeteryApp.ContractDTO;
import ro.InnovaTeam.cemeteryApp.FilterDTO;
import ro.InnovaTeam.cemeteryApp.controller.auth.UserAuthenticationManager;
import ro.InnovaTeam.cemeteryApp.controller.grave.GraveController;
import ro.InnovaTeam.cemeteryApp.restClient.ContractRestClient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Cata on 1/5/2015.
 */
@Controller
@RequestMapping("/contract")
public class ContractController {

    private static final Logger logger = LoggerFactory.getLogger(ContractController.class);
    public static final String CONTRACT = "/contract";
    public static final String CONTRACT_FILTER = "contractFilter";
    public static final int PAGE_SIZE = 20;

    @Autowired
    @Qualifier("contractValidator")
    private Validator contractValidator;

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
        FilterDTO contractFilterDTO = (FilterDTO) request.getSession().getAttribute(CONTRACT_FILTER);
        contractFilterDTO = contractFilterDTO != null ? contractFilterDTO : new FilterDTO();
        String param = request.getParameter("pageNo");
        Integer pageNo = param != null ? Integer.valueOf(param) : 1;

        List<ContractDTO> contractDTOs;
        contractFilterDTO.setPageNo(pageNo);
        contractFilterDTO.setPageSize(PAGE_SIZE);
        contractDTOs = ContractRestClient.findByFilter(contractFilterDTO);

        float pages = ContractRestClient.getContractCount(new FilterDTO(contractFilterDTO.getSearchCriteria(),
                contractFilterDTO.getParentId())) / (float) PAGE_SIZE;
        model.addAttribute("pages", Math.ceil(pages));
        model.addAttribute("contractList", contractDTOs);
        model.addAttribute("hasAdminRole", UserAuthenticationManager.hasAdminRole());
        return "contract/contractsPage";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String renderAddPage(Model model, HttpServletRequest request) {

        if (!model.containsAttribute("contractDTOExists")) {
            ContractDTO contractDTO = (ContractDTO) request.getSession().getAttribute(GraveController.STRUCTURE_CONTRACT_DTO);
            model.addAttribute("contract", contractDTO != null ? contractDTO : new ContractDTO());
            request.getSession().removeAttribute(GraveController.STRUCTURE_CONTRACT_DTO);
        }
        model.addAttribute("hasAdminRole", UserAuthenticationManager.hasAdminRole());
        return "contract/contractDetailsPage";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("contract") ContractDTO contractDTO, BindingResult result, Model model) {
        contractValidator.validate(contractDTO, result);
        if (result.hasErrors()) {
            model.addAttribute("contractDTOExists", true);
            model.addAttribute("hasAdminRole", UserAuthenticationManager.hasAdminRole());
            return "contract/contractDetailsPage";
        }
        ContractRestClient.add(contractDTO);
        return "redirect:" + CONTRACT;
    }

    @RequestMapping(value = "/get/{id}")
    public String getById(@PathVariable Integer id, Model model) {
        ContractDTO contractDTO = ContractRestClient.findById(id);

        model.addAttribute("contract", contractDTO);
        model.addAttribute("view", true);
        model.addAttribute("hasAdminRole", UserAuthenticationManager.hasAdminRole());
        return "contract/contractDetailsPage";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable Integer id) {
        try {
            ContractRestClient.delete(id);
        }
        catch (Exception e) {
            logger.error("Could not delete contract with id: " + id, e);
        }
        return "redirect:" + CONTRACT;
    }

    @RequestMapping(value = "/update")
    public String update(@ModelAttribute("contract") ContractDTO contractDTO, BindingResult result, Model model) {
        contractValidator.validate(contractDTO, result);
        if (result.hasErrors()) {
            model.addAttribute("view", true);
            model.addAttribute("hasAdminRole", UserAuthenticationManager.hasAdminRole());
            return "contract/contractDetailsPage";
        }
        ContractRestClient.update(contractDTO.getId(), contractDTO);
        return "redirect:" + CONTRACT;
    }

    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public void applyFilter(HttpServletRequest request, HttpServletResponse response) {
        String searchCriteria = request.getParameter("searchCriteria");
        String cemeteryId = request.getParameter("structureId");
        //TODO: validate cemeteryId
        FilterDTO contractFilterDTO = new FilterDTO();
        contractFilterDTO.setSearchCriteria(searchCriteria);
        if(StringUtils.isNotEmpty(cemeteryId)) {
            contractFilterDTO.setParentId(Integer.valueOf(cemeteryId));
        }
        request.getSession().setAttribute(CONTRACT_FILTER, contractFilterDTO);

        try {
            response.sendRedirect(request.getContextPath() + CONTRACT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/refreshFilter", method = RequestMethod.POST)
    public void refreshFilter(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute(CONTRACT_FILTER);
        try {
            response.sendRedirect(request.getContextPath() + CONTRACT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/printable/{contractId}", method = RequestMethod.GET)
    public ModelAndView showContractPDF(@PathVariable Integer contractId, HttpServletRequest request) {
        request.getSession().setAttribute("contractId", contractId);
        return new ModelAndView("printableContract");
    }
}
