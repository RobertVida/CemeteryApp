package ro.InnovaTeam.cemeteryApp.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import ro.InnovaTeam.cemeteryApp.ErrorDTO;
import ro.InnovaTeam.cemeteryApp.UserDTO;
import ro.InnovaTeam.cemeteryApp.controller.auth.AuthenticationFilter;
import ro.InnovaTeam.cemeteryApp.controller.auth.UserAuthenticationManager;
import ro.InnovaTeam.cemeteryApp.restClient.AuthenticationRestClient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Catalin Sorecau on 11/15/2014.
 */
@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    private static final String DASHBOARD_PAGE = "dashBoardPage";
    private ObjectMapper om = new ObjectMapper();

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
                        @RequestParam(value = "password") String password, Model model,
                        HttpServletRequest httpServletRequest, HttpServletResponse response) {
        org.springframework.security.authentication.AuthenticationManager authenticationManager = new UserAuthenticationManager();
        try {
            Authentication request = new UsernamePasswordAuthenticationToken(username, password);
            Authentication result = authenticationManager.authenticate(request);
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(result);
            httpServletRequest.getSession().setAttribute(AuthenticationFilter.USER_SESSION_AUTHENTICATION_NAME, username);

            HttpSession session = httpServletRequest.getSession(true);
            session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
            session.setMaxInactiveInterval(320);
        } catch (BadCredentialsException e) {
            model.addAttribute("errors", "Credentiale gresite");
            return "loginPage";
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            try {
                ErrorDTO error = om.readValue(e.getResponseBodyAsString(), ErrorDTO.class);
                if (ErrorDTO.Status.UNAUTHORIZED_ACCESS.toString().equals(error.getStatus())) {
                    httpServletRequest.getSession().invalidate();
                    response.sendRedirect(httpServletRequest.getContextPath() + "/login");
                }
                model.addAttribute("errors", error.getErrors());
                return "loginPage";
            } catch (IOException ioe) {
                logger.error("Could not read value from ObjectMapper", ioe);
            }
        }
        return DASHBOARD_PAGE;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        SecurityContextHolder.getContext().setAuthentication(null);
        request.getSession().removeAttribute(AuthenticationFilter.USER_SESSION_AUTHENTICATION_NAME);
        request.getSession().removeAttribute(AuthenticationFilter.USER_SESSION_AUTHENTICATION_TOKEN);

        return "loginPage";
    }
}
