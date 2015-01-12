package ro.InnovaTeam.cemeteryApp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.InnovaTeam.cemeteryApp.eao.AuthenticationEAO;
import ro.InnovaTeam.cemeteryApp.exceptions.BadCredentials;
import ro.InnovaTeam.cemeteryApp.exceptions.Forbidden;
import ro.InnovaTeam.cemeteryApp.exceptions.UnauthorizedAccess;
import ro.InnovaTeam.cemeteryApp.model.User;
import ro.InnovaTeam.cemeteryApp.service.AuthenticationService;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;


/**
 * Created by robert on 1/10/2015.
 */
@Transactional
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AuthenticationEAO authEAO;

    @Override
    public User login(User user) {
        return refreshUserWithToken(getUser(user));
    }

    @Override
    public User getAdminAccess(String token) {
        User user = getUser(token);

        if (user.getRole().equals("admin")) {
            return user;
        } else if (user.getRole().equals("guest")) {
            throw new Forbidden();
        }
        throw new UnauthorizedAccess();

    }

    @Override
    public User hasGuestAccess(String token) {
        User user = getUser(token);

        if (user.getRole().equals("admin") || user.getRole().equals("guest")) {
            return user;
        }
        throw new UnauthorizedAccess();
    }

    private User getUser(User user) {
        User dbUser;
        try {
            dbUser = authEAO.getUser(user.getUsername(), user.getPassword());
        } catch (Exception e) {
            throw new BadCredentials();
        }
        return dbUser;
    }

    private User getUser(String token) {
        User dbUser;
        try {
            dbUser = authEAO.getUser(token);
        } catch (Exception e) {
            throw new UnauthorizedAccess();
        }

        if (expiredToken(dbUser)) {
            throw new UnauthorizedAccess();
        }

        return dbUser;
    }

    private User refreshUserWithToken(User user) {
        if (user.getToken() == null || expiredToken(user)) {
            user.setExpiresOn(getExpirationDate());
            user.setToken(UUID.randomUUID().toString());
            authEAO.createTokenForUser(user);
            return authEAO.getUser(user.getUsername(), user.getPassword());
        }
        return user;
    }

    private boolean expiredToken(User user) {
        return new Date().compareTo(user.getExpiresOn()) > 0;
    }

    public Date getExpirationDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR_OF_DAY, 8);
        return calendar.getTime();
    }
}
