package ro.InnovaTeam.cemeteryApp;

import java.io.Serializable;

public class BaseDTO implements Serializable{

    protected Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
