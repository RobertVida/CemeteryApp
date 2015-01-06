package ro.InnovaTeam.cemeteryApp.restClient;

import org.springframework.web.bind.annotation.PathVariable;
import ro.InnovaTeam.cemeteryApp.*;

import java.util.List;

/**
 * Created by Tudor on 1/5/2015.
 */
public class StructureHistoryRestClient extends GenericRestClient {

    public static final String STRUCTURE_HISTORY_URL = "/structureHistory";
    public static final String STRUCTURE_HISTORIES_URL = "/structureHistories";
    public static final String SPECIFIC_STRUCTURE_HISTORY_URL = STRUCTURE_HISTORY_URL + "/{structureHistoryId}";
    public static final String SPECIFIC_USER_STRUCTURE_HISTORY_URL = STRUCTURE_HISTORY_URL + "/{userId}/{structureHistoryId}";
    public static List<StructureHistoryEntryDTO> findByFilter(FilterDTO structureHistoryFilterDTO) {
        return getByFilter(structureHistoryFilterDTO, BASE_URL + STRUCTURE_HISTORIES_URL, StructureHistoryEntryList.class);
    }

    public static StructureHistoryEntryDTO findById(@PathVariable Integer structureId) {
        return findById(structureId, BASE_URL + SPECIFIC_STRUCTURE_HISTORY_URL, StructureHistoryEntryDTO.class);
    }

    public static StructureHistoryEntryDTO update(@PathVariable Integer structureId, StructureHistoryEntryDTO structureHistoryEntryDTO) {
        return update(structureId, BASE_URL + SPECIFIC_STRUCTURE_HISTORY_URL, structureHistoryEntryDTO, StructureHistoryEntryDTO.class);
    }

    public static void add(StructureHistoryEntryDTO structureHistoryEntryDTO) {
        add(structureHistoryEntryDTO, BASE_URL + STRUCTURE_HISTORY_URL);
    }

    public static void delete(@PathVariable Integer structureId) {
        delete(structureId, BASE_URL + SPECIFIC_USER_STRUCTURE_HISTORY_URL);
    }

    public static Integer getStructureCount(FilterDTO filterDTO) {
        return getCount(filterDTO, BASE_URL + STRUCTURE_HISTORIES_URL + "/count");
    }
}
