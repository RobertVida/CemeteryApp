package ro.InnovaTeam.cemeteryApp;

import org.hibernate.validator.constraints.NotBlank;

import static ro.InnovaTeam.cemeteryApp.ValidationErrors.*;

/**
 * Created by robert on 12/14/2014.
 */
public class UserDTO extends BaseDTO{

    @NotBlank(message = USER_USERNAME_BLANK)
    private String username;
    @NotBlank(message = USER_PASSWORD_BLANK)
    private String password;
    private String role;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
