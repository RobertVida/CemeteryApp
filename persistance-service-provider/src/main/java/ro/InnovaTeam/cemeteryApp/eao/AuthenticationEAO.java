package ro.InnovaTeam.cemeteryApp.eao;

import ro.InnovaTeam.cemeteryApp.model.User;

/**
 * Created by robert on 01/01/2015.
 */
public interface AuthenticationEAO {

    public User getUser(String username, String password);

    public User getUser(String token);

    public void createTokenForUser(User user);
}
