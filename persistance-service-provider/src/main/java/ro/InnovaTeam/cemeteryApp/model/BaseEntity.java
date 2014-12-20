package ro.InnovaTeam.cemeteryApp.model;

/**
 * Created by robert on 10/23/2014.
 */

public class BaseEntity {

    protected Integer id;
    protected Integer userId;
    protected final String tableName;

    public BaseEntity() {
        this("");
    }

    public BaseEntity(String tableName) {
        this.tableName = tableName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTableName() {
        return tableName;
    }
}
