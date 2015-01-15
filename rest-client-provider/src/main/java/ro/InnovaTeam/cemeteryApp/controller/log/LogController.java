package ro.InnovaTeam.cemeteryApp.controller.log;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import ro.InnovaTeam.cemeteryApp.ErrorDTO;
import ro.InnovaTeam.cemeteryApp.FilterDTO;
import ro.InnovaTeam.cemeteryApp.LogEntryDTO;
import ro.InnovaTeam.cemeteryApp.restClient.LogRestClient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Catalin Sorecau on 12/14/2014.
 */
@Controller
@RequestMapping("/logs")
public class LogController {

    private static final Logger logger = LoggerFactory.getLogger(LogController.class);
    public static final int PAGE_SIZE = 20;
    public static final String LOGS_TABLE_NAME = "logsTableName";
    public static final String LOGS_TABLE_ID = "logsTableId";
    private static final String LOGS_FILTER = "logsFilter";
    public static final String LOGS = "/logs";
    private ObjectMapper om = new ObjectMapper();

    @RequestMapping
    public String renderHome(Model model, HttpServletRequest request, HttpServletResponse response) {
        FilterDTO filterDTO = (FilterDTO) request.getSession().getAttribute(LOGS_FILTER);
        String tableName = (String) request.getSession().getAttribute(LOGS_TABLE_NAME);
        String tableId = (String) request.getSession().getAttribute(LOGS_TABLE_ID);
        filterDTO = filterDTO != null ? filterDTO : new FilterDTO();
        String param = request.getParameter("pageNo");
        Integer pageNo = param != null ? Integer.valueOf(param) : 1;

        List<LogEntryDTO> entryDTOs;
        filterDTO.setPageNo(pageNo);
        filterDTO.setPageSize(PAGE_SIZE);
        filterDTO.setParentId(null);

        float pages;
        FilterDTO countFilterDTO = new FilterDTO(filterDTO.getSearchCriteria(), null);
        if (StringUtils.isNotEmpty(tableName) && StringUtils.isNotEmpty(tableId)) {
            entryDTOs = LogRestClient.getLogs(filterDTO, tableName, Integer.valueOf(tableId));
            pages = LogRestClient.getLogCount(countFilterDTO, tableName, Integer.valueOf(tableId));
        } else if (StringUtils.isNotEmpty(tableName)) {
            entryDTOs = LogRestClient.getLogs(filterDTO, tableName);
            pages = LogRestClient.getLogCount(countFilterDTO, tableName);
        } else {
            entryDTOs = LogRestClient.getLogs(filterDTO);
            pages = LogRestClient.getLogCount(countFilterDTO);
        }
        pages /= PAGE_SIZE;
        model.addAttribute("pages", new Double(Math.ceil(pages)).intValue());
        try {
            float pages;
            FilterDTO countFilterDTO = new FilterDTO(filterDTO.getSearchCriteria(), null);
            if (StringUtils.isNotEmpty(tableName) && StringUtils.isNotEmpty(tableId)) {
                entryDTOs = LogRestClient.getLogs(filterDTO, tableName, Integer.valueOf(tableId));
                pages = LogRestClient.getLogCount(countFilterDTO, tableName, Integer.valueOf(tableId));
            } else if (StringUtils.isNotEmpty(tableName)) {
                entryDTOs = LogRestClient.getLogs(filterDTO, tableName);
                pages = LogRestClient.getLogCount(countFilterDTO, tableName);
            } else {
                entryDTOs = LogRestClient.getLogs(filterDTO);
                pages = LogRestClient.getLogCount(countFilterDTO);
            }
            pages /= PAGE_SIZE;
            model.addAttribute("pages", Math.ceil(pages));

            model.addAttribute("logList", entryDTOs);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
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
        return "log/logPage";
    }

    @RequestMapping(value = "/get/{logId}")
    public String getLog(@PathVariable Integer logId, Model model, HttpServletRequest request, HttpServletResponse response) {
        try {
            model.addAttribute("log", LogRestClient.getLog(logId));
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            try {
                ErrorDTO error = om.readValue(e.getResponseBodyAsString(), ErrorDTO.class);
                if (ErrorDTO.Status.UNAUTHORIZED_ACCESS.toString().equals(error.getStatus())) {
                    request.getSession().invalidate();
                    response.sendRedirect(request.getContextPath() + "/login");
                }
                model.addAttribute("errors", error.getErrors());
                return "log/logPage";
            } catch (IOException ioe) {
                logger.error("Could not read value from ObjectMapper", ioe);
            }
        }
        return "log/logDetailsPage";
    }

    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public void applyFilter(HttpServletRequest request, HttpServletResponse response) {
        String tableName = request.getParameter("tableName");
        String tableId = request.getParameter("tableId");

        FilterDTO filterDTO = new FilterDTO();

        request.getSession().setAttribute(LOGS_TABLE_NAME, tableName);
        request.getSession().setAttribute(LOGS_TABLE_ID, tableId);
        request.getSession().setAttribute(LOGS_FILTER, filterDTO);

        try {
            response.sendRedirect(request.getContextPath() + "/logs");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/refreshFilter", method = RequestMethod.POST)
    public void refreshFilter(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute(LOGS_FILTER);
        request.getSession().removeAttribute(LOGS_TABLE_ID);
        request.getSession().removeAttribute(LOGS_TABLE_NAME);
        try {
            response.sendRedirect(request.getContextPath() + "/logs");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
