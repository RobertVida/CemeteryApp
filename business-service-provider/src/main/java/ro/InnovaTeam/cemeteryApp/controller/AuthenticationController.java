package ro.InnovaTeam.cemeteryApp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.InnovaTeam.cemeteryApp.UserDTO;

/**
 * Created by robert on 12/14/2014.
 */
@Controller
public class AuthenticationController {

    public static final String LOGIN_URL = "/login/{username}/{password}";

    @RequestMapping(value = LOGIN_URL, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public UserDTO login(@PathVariable String username, @PathVariable String password){
        UserDTO user = null;
        if(username.equals("admin") && password.equals("admin")){
            user = new UserDTO();
            user.setUsername("admin");
            user.setPassword("admin");
            user.setId(1);
        } else if(username.equals("u") && password.equals("p")){
            user = new UserDTO();
            user.setUsername("u");
            user.setPassword("p");
            user.setId(2);
        }
        return user;
    }

}
