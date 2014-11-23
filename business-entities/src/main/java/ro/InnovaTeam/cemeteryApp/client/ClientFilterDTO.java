package ro.InnovaTeam.cemeteryApp.client;

/**
 * Created by Catalin Sorecau on 11/23/2014.
 */
public class ClientFilterDTO {

    private String searchCriteria;
    private Integer pageNo;
    private Integer pageSize;

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
}
