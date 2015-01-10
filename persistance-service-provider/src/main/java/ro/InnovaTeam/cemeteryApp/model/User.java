package ro.InnovaTeam.cemeteryApp.model;

/**
 * Created by robert on 1/10/2015.
 */
public class User extends BaseEntity{

    private String username;
    private String password;
    private String token;
    private String role;

    public User() {
        this("users");
    }

    public User(String tableName) {
        super(tableName);
    }

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
