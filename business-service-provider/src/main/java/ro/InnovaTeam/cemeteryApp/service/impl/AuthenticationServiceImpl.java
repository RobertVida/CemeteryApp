package ro.InnovaTeam.cemeteryApp.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.InnovaTeam.cemeteryApp.exceptions.Forbidden;
import ro.InnovaTeam.cemeteryApp.exceptions.UnauthorizedAccess;
import ro.InnovaTeam.cemeteryApp.model.User;
import ro.InnovaTeam.cemeteryApp.service.AuthenticationService;


/**
 * Created by robert on 1/10/2015.
 */
@Transactional
@Service
public class AuthenticationServiceImpl implements AuthenticationService{

    @Override
    public User login(User user) {
        if(user.getUsername().equals("admin") && user.getPassword().equals("admin")){
            return getAdmin();
        } else if(user.getUsername().equals("u") && user.getPassword().equals("p")){
            return getGuest();
        }
        throw new UnauthorizedAccess();
    }

    @Override
    public User getAdminAccess(String token) {
        if("11111-1asda-123a2=asd2a-123ad-q21aa-asd22-zxcew-asdw2".equals(token)){
            return getAdmin();
        } else if ("22222-1asda-123a2=asd2a-123ad-q21aa-asd22-zxcew-asdw2".equals(token)){
            throw new Forbidden();
        }
        throw new UnauthorizedAccess();
    }

    @Override
    public User hasGuestAccess(String token) {
        if("11111-1asda-123a2=asd2a-123ad-q21aa-asd22-zxcew-asdw2".equals(token)) {
            return getAdmin();
        }else if ("22222-1asda-123a2=asd2a-123ad-q21aa-asd22-zxcew-asdw2".equals(token)){
            return getGuest();
        }
        throw new UnauthorizedAccess();
    }

    private User getGuest() {
        return new User(){{
            setUsername("u");
            setPassword("p");
            setId(2);
            setRole("guest");
            setToken("22222-1asda-123a2=asd2a-123ad-q21aa-asd22-zxcew-asdw2");
        }};
    }

    private User getAdmin() {
        return new User(){{
            setUsername("admin");
            setPassword("admin");
            setId(1);
            setRole("admin");
            setToken("11111-1asda-123a2=asd2a-123ad-q21aa-asd22-zxcew-asdw2");
        }};
    }
}
