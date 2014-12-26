package ro.InnovaTeam.cemeteryApp.model;

/**
 * Created by robert on 11/26/2014.
 */
public class Filter {

    private String searchCriteria;
    private Integer pageNo;
    private Integer pageSize;
    private Integer parentId;

    public Filter() {
    }

    public Filter(Integer parentId) {
        this(null, null, null, parentId);
    }

    public Filter(Integer pageNo, Integer pageSize, String searchCriteria, Integer parentId) {
        this.searchCriteria = searchCriteria;
        this.pageNo = pageNo != null ? pageNo : 1;
        this.pageSize = pageSize != null ? pageSize : 20;
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
