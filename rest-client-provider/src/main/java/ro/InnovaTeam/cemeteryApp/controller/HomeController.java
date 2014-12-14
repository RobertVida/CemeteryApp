package ro.InnovaTeam.cemeteryApp.controller;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ro.InnovaTeam.cemeteryApp.controller.auth.UserAuthenticationManager;

/**
 * Created by Catalin Sorecau on 11/15/2014.
 */
@Controller
public class HomeController {

    private static final String DASHBOARD_PAGE = "dashBoardPage";

    @RequestMapping(value = {"", "/dashboard"}, method = RequestMethod.GET)
    public String showDashboard() {

        return DASHBOARD_PAGE;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String renderLoginPage() {

        return "loginPage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password, Model model) {
        org.springframework.security.authentication.AuthenticationManager authenticationManager = new UserAuthenticationManager();
        try {
            Authentication request = new UsernamePasswordAuthenticationToken(username, password);
            Authentication result = authenticationManager.authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(result);
        } catch (BadCredentialsException e) {
            model.addAttribute("error", "Credentiale gresite");
            return "loginPage";
        }
        return DASHBOARD_PAGE;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        SecurityContextHolder.getContext().setAuthentication(null);

        return "loginPage";
    }
}
