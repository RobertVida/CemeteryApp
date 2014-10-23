package ro.InnovaTeam.cemeteryApp.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by robert on 10/23/2014.
 */

@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column( name = "created_at")
    private Date createdAt;

    @Column( name = "updated_at")
    private Date updatedAt;

    public BaseEntity() {
        this(new Date(), new Date());
    }

    public BaseEntity(Date createdAt, Date updatedAt) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
