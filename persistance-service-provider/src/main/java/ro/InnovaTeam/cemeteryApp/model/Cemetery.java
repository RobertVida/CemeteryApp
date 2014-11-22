package ro.InnovaTeam.cemeteryApp.model;

import javax.validation.constraints.NotNull;

/**
 * Created by lucian.vaida on 2/11/2014.
 */
public class Cemetery extends BaseEntity{

    @NotNull
    private String name;
    @NotNull
    private String address;

    public Cemetery() {
    }

    public Cemetery(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Cemetery{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
