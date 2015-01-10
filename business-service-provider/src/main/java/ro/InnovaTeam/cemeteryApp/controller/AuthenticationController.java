package ro.InnovaTeam.cemeteryApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import ro.InnovaTeam.cemeteryApp.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.InnovaTeam.cemeteryApp.UserDTO;
import ro.InnovaTeam.cemeteryApp.service.AuthenticationService;

import static ro.InnovaTeam.cemeteryApp.util.UserUtil.toDTO;

/**
 * Created by robert on 12/14/2014.
 */
@Controller
public class AuthenticationController extends ExceptionHandledController {

    public static final String LOGIN_URL = "/login/{username}/{password}";

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(value = LOGIN_URL, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public UserDTO login(@PathVariable final String username, @PathVariable final String password) {
        return toDTO(authenticationService.login(new User() {{
            setUsername(username);
            setPassword(password);
        }}));
    }

}
