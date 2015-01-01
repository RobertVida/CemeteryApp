package ro.InnovaTeam.cemeteryApp.restClient;

import org.springframework.web.client.RestTemplate;
import ro.InnovaTeam.cemeteryApp.FilterDTO;
import ro.InnovaTeam.cemeteryApp.LogEntryDTO;
import ro.InnovaTeam.cemeteryApp.LogEntryList;

import java.util.List;

/**
 * Created by Catalin Sorecau on 12/14/2014.
 */
public class LogRestClient extends BaseRestClient {

    public static final String LOG_URL = "/log/{logId}";
    public static final String LOGS_URL = "/logs";
    public static final String LOGS_FOR_ENTITY_URL = LOGS_URL + "/{entityName}";
    public static final String LOGS_FOR_ENTITY_AND_ID_URL = LOGS_FOR_ENTITY_URL + "/{entityId}";

    public static LogEntryDTO getLog(Integer logId) {
        RestTemplate restTemplate = getJSONRestTemplate();

        return restTemplate.getForObject(BASE_URL + LOG_URL, LogEntryDTO.class, logId);
    }

    public static List<LogEntryDTO> getLogs(FilterDTO filterDTO) {
        RestTemplate restTemplate = getJSONRestTemplate();

        LogEntryList logEntryList = restTemplate.postForObject(BASE_URL + LOGS_URL, filterDTO, LogEntryList.class);

        return logEntryList.getContent();
    }

    public static List<LogEntryDTO> getLogs(FilterDTO filterDTO, String entityName) {
        RestTemplate restTemplate = getJSONRestTemplate();

        LogEntryList logEntryList = restTemplate.postForObject(BASE_URL + LOGS_FOR_ENTITY_URL, filterDTO, LogEntryList.class, entityName);

        return logEntryList.getContent();
    }

    public static List<LogEntryDTO> getLogs(FilterDTO filterDTO, String entityName, Integer entityId) {
        RestTemplate restTemplate = getJSONRestTemplate();

        LogEntryList logEntryList = restTemplate.postForObject(BASE_URL + LOGS_FOR_ENTITY_AND_ID_URL, filterDTO, LogEntryList.class, entityName, entityId);

        return logEntryList.getContent();
    }

    public static Integer getLogCount(FilterDTO filterDTO) {
        return getJSONRestTemplate().postForObject(BASE_URL + LOGS_URL + "/count", filterDTO, Integer.class);
    }

    public static Integer getLogCount(FilterDTO filterDTO, String entityName) {
        return getJSONRestTemplate().postForObject(BASE_URL + LOGS_FOR_ENTITY_URL + "/count", filterDTO, Integer.class, entityName);
    }

    public static Integer getLogCount(FilterDTO filterDTO, String entityName, Integer entryId) {
        return getJSONRestTemplate().postForObject(BASE_URL + LOGS_FOR_ENTITY_AND_ID_URL + "/count", filterDTO, Integer.class, entityName, entryId);
    }
}
