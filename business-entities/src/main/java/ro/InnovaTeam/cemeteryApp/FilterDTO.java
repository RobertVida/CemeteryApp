package ro.InnovaTeam.cemeteryApp;

/**
 * Created by Catalin Sorecau on 11/23/2014.
 */
public class FilterDTO {

    private String searchCriteria;
    private Integer pageNo;
    private Integer pageSize;
    private Integer parentId;

    public FilterDTO(){
    }

    public FilterDTO(String searchCriteria, Integer parentId) {
        this.searchCriteria = searchCriteria;
        pageNo = 1;
        pageSize = 1;
        this.parentId = parentId;
    }

    public String getSearchCriteria() {
        return searchCriteria;
    }

    public void setSearchCriteria(String searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
