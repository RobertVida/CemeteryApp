package ro.InnovaTeam.cemeteryApp;

import javax.validation.constraints.NotNull;

/**
 * Created by robert on 12/14/2014.
 */
public class UserDTO extends BaseDTO{

    @NotNull
    private String username;
    @NotNull
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
