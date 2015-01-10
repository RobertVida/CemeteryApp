package ro.InnovaTeam.cemeteryApp.service;

import ro.InnovaTeam.cemeteryApp.model.User;

/**
 * Created by robert on 1/10/2015.
 */
public interface AuthenticationService {

    public User login(User user);

    public User getAdminAccess(String token);

    public User hasGuestAccess(String token);
}
