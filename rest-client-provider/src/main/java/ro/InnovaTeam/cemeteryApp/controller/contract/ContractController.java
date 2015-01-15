package ro.InnovaTeam.cemeteryApp.controller.contract;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;
import ro.InnovaTeam.cemeteryApp.ContractDTO;
import ro.InnovaTeam.cemeteryApp.ErrorDTO;
import ro.InnovaTeam.cemeteryApp.FilterDTO;
import ro.InnovaTeam.cemeteryApp.controller.auth.UserAuthenticationManager;
import ro.InnovaTeam.cemeteryApp.controller.grave.GraveController;
import ro.InnovaTeam.cemeteryApp.controller.log.LogController;
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
    private ObjectMapper om = new ObjectMapper();

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
    public String renderHome(Model model, HttpServletRequest request, HttpServletResponse response) {
        FilterDTO contractFilterDTO = (FilterDTO) request.getSession().getAttribute(CONTRACT_FILTER);
        contractFilterDTO = contractFilterDTO != null ? contractFilterDTO : new FilterDTO();
        String param = request.getParameter("pageNo");
        Integer pageNo = param != null ? Integer.valueOf(param) : 1;

        List<ContractDTO> contractDTOs;
        contractFilterDTO.setPageNo(pageNo);
        contractFilterDTO.setPageSize(PAGE_SIZE);
        try {
            contractDTOs = ContractRestClient.findByFilter(contractFilterDTO);

            float pages = ContractRestClient.getContractCount(new FilterDTO(contractFilterDTO.getSearchCriteria(),
                    contractFilterDTO.getParentId())) / (float) PAGE_SIZE;
            model.addAttribute("hasAdminRole", UserAuthenticationManager.hasAdminRole());
            model.addAttribute("pages", Math.ceil(pages));
            model.addAttribute("contractList", contractDTOs);
        } catch (HttpClientErrorException e) {
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
        return "contract/contractsPage";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String renderAddPage(Model model, HttpServletRequest request, HttpServletResponse response) {

        if (!model.containsAttribute("contractDTOExists")) {
            ContractDTO contractDTO = (ContractDTO) request.getSession().getAttribute(GraveController.STRUCTURE_CONTRACT_DTO);
            model.addAttribute("contract", contractDTO != null ? contractDTO : new ContractDTO());
            request.getSession().removeAttribute(GraveController.STRUCTURE_CONTRACT_DTO);
        }
        try {
            model.addAttribute("hasAdminRole", UserAuthenticationManager.hasAdminRole());
        } catch (HttpClientErrorException e) {
            try {
                ErrorDTO error = om.readValue(e.getResponseBodyAsString(), ErrorDTO.class);
                if (ErrorDTO.Status.UNAUTHORIZED_ACCESS.toString().equals(error.getStatus())) {
                    request.getSession().invalidate();
                    response.sendRedirect(request.getContextPath() + "/login");
                }
                model.addAttribute("errors", error.getErrors());
                return "contract/contractsPage";
            } catch (IOException ioe) {
                logger.error("Could not read value from ObjectMapper", ioe);
            }
        }
        return "contract/contractDetailsPage";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("contract") ContractDTO contractDTO, BindingResult result, Model model,
                      HttpServletResponse response, HttpServletRequest request) {
        contractValidator.validate(contractDTO, result);
        try {
            model.addAttribute("hasAdminRole", UserAuthenticationManager.hasAdminRole());
            if (result.hasErrors()) {
                model.addAttribute("contractDTOExists", true);
                return "contract/contractDetailsPage";
            }
            ContractRestClient.add(contractDTO);
        } catch (HttpClientErrorException e) {
            try {
                ErrorDTO error = om.readValue(e.getResponseBodyAsString(), ErrorDTO.class);
                if (ErrorDTO.Status.UNAUTHORIZED_ACCESS.toString().equals(error.getStatus())) {
                    request.getSession().invalidate();
                    response.sendRedirect(request.getContextPath() + "/login");
                }
                model.addAttribute("errors", error.getErrors());
                return "contract/contractDetailsPage";
            } catch (IOException ioe) {
                logger.error("Could not read value from ObjectMapper", ioe);
            }
        }
        return "redirect:" + CONTRACT;
    }

    @RequestMapping(value = "/get/{id}")
    public String getById(@PathVariable Integer id, Model model, HttpServletRequest request, HttpServletResponse response) {
        try {
            ContractDTO contractDTO = ContractRestClient.findById(id);
            model.addAttribute("contract", contractDTO);
            model.addAttribute("hasAdminRole", UserAuthenticationManager.hasAdminRole());
        } catch (HttpClientErrorException e) {
            try {
                ErrorDTO error = om.readValue(e.getResponseBodyAsString(), ErrorDTO.class);
                if (ErrorDTO.Status.UNAUTHORIZED_ACCESS.toString().equals(error.getStatus())) {
                    request.getSession().invalidate();
                    response.sendRedirect(request.getContextPath() + "/login");
                }
                model.addAttribute("errors", error.getErrors());
                return "contract/contractsPage";
            } catch (IOException ioe) {
                logger.error("Could not read value from ObjectMapper", ioe);
            }
        }

        model.addAttribute("view", true);
        return "contract/contractDetailsPage";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            ContractRestClient.delete(id);
        } catch (HttpClientErrorException e) {
            try {
                ErrorDTO error = om.readValue(e.getResponseBodyAsString(), ErrorDTO.class);
                if (ErrorDTO.Status.UNAUTHORIZED_ACCESS.toString().equals(error.getStatus())) {
                    request.getSession().invalidate();
                    response.sendRedirect(request.getContextPath() + "/login");
                }
                model.addAttribute("errors", error.getErrors());
                return "contract/contractsPage";
            } catch (IOException ioe) {
                logger.error("Could not read value from ObjectMapper", ioe);
            }
        }
        return "redirect:" + CONTRACT;
    }

    @RequestMapping(value = "/update")
    public String update(@ModelAttribute("contract") ContractDTO contractDTO, BindingResult result, Model model,
                         HttpServletRequest request, HttpServletResponse response) {
        contractValidator.validate(contractDTO, result);
        try {
            model.addAttribute("hasAdminRole", UserAuthenticationManager.hasAdminRole());
            if (result.hasErrors()) {
                model.addAttribute("view", true);
                return "contract/contractDetailsPage";
            }
            ContractRestClient.update(contractDTO.getId(), contractDTO);
        } catch (HttpClientErrorException e) {
            try {
                ErrorDTO error = om.readValue(e.getResponseBodyAsString(), ErrorDTO.class);
                if (ErrorDTO.Status.UNAUTHORIZED_ACCESS.toString().equals(error.getStatus())) {
                    request.getSession().invalidate();
                    response.sendRedirect(request.getContextPath() + "/login");
                }
                model.addAttribute("errors", error.getErrors());
                return "contract/contractDetailsPage";
            } catch (IOException ioe) {
                logger.error("Could not read value from ObjectMapper", ioe);
            }
        }
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

    @RequestMapping(value = "/filterLogs/{contractId}", method = RequestMethod.GET)
    public void filterLogs(HttpServletRequest request, @PathVariable Integer contractId, HttpServletResponse response) {
        request.getSession().setAttribute(LogController.LOGS_TABLE_NAME, "contracts");
        request.getSession().setAttribute(LogController.LOGS_TABLE_ID, String.valueOf(contractId));
        try {
            response.sendRedirect(request.getContextPath() + LogController.LOGS);
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
