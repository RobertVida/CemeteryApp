package ro.InnovaTeam.cemeteryApp.util;

import ro.InnovaTeam.cemeteryApp.FilterDTO;
import ro.InnovaTeam.cemeteryApp.model.Filter;

/**
 * Created by Catalin Sorecau on 11/24/2014.
 */
public abstract class FilterUtil {

    public static Filter toDB(FilterDTO filterDTO) {
        Filter filter = new Filter();
        filter.setParentId(filterDTO.getParentId());
        filter.setPageNo(filterDTO.getPageNo());
        filter.setPageSize(filterDTO.getPageSize());
        filter.setSearchCriteria(filterDTO.getSearchCriteria());

        return filter;
    }

    public static FilterDTO toDTO(Filter filter) {
        FilterDTO filterDTO = new FilterDTO();
        filterDTO.setParentId(filter.getParentId());
        filterDTO.setPageNo(filter.getPageNo());
        filterDTO.setPageSize(filter.getPageSize());
        filterDTO.setSearchCriteria(filter.getSearchCriteria());

        return filterDTO;
    }
}
