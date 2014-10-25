package ro.InnovaTeam.cemeteryApp.model.test;

import ro.InnovaTeam.cemeteryApp.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Catalin Sorecau on 10/23/2014.
 */
@Entity
@Table(name = "test")
public class Test extends BaseEntity {

    @Column(name = "content")
    private String content;

    public Test() {
        super();
    }

    public Test(String content) {
        super();
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
