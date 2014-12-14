package ro.InnovaTeam.cemeteryApp.controller.log;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    public static final int PAGE_SIZE = 20;
    private static final String LOGS_TABLE_NAME = "logsTableName";
    private static final String LOGS_TABLE_ID = "logsTableId";
    private static final String LOGS_FILTER = "logsFilter";

    @RequestMapping
    public String renderHome(Model model, HttpServletRequest request) {
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
        if (StringUtils.isNotEmpty(tableName) && StringUtils.isNotEmpty(tableId)) {
            entryDTOs = LogRestClient.getLogs(filterDTO, tableName, Integer.valueOf(tableId));
        } else if (StringUtils.isNotEmpty(tableName)) {
            entryDTOs = LogRestClient.getLogs(filterDTO, tableName);
        } else {
            entryDTOs = LogRestClient.getLogs(filterDTO);
        }

        int pages = entryDTOs.size()/PAGE_SIZE;
        model.addAttribute("pages", pages != 0 ? pages + 1 : pages);

        model.addAttribute("logList", entryDTOs);
        return "log/logPage";
    }

    @RequestMapping(value = "/get/{logId}")
    public String getLog(@PathVariable Integer logId, Model model) {

        model.addAttribute("log", LogRestClient.getLog(logId));
        return "log/logDetailsPage";
    }

    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public void applyFilter(HttpServletRequest request, HttpServletResponse response) {
        String tableName = request.getParameter("tableName");
        String tableId = request.getParameter("tableId");
        String searchCriteria = request.getParameter("searchCriteria");

        FilterDTO filterDTO = new FilterDTO();
        filterDTO.setSearchCriteria(searchCriteria);

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
