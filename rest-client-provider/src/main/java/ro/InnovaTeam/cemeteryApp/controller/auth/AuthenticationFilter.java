package ro.InnovaTeam.cemeteryApp.controller.auth;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Cata on 12/14/2014.
 */
public class AuthenticationFilter implements Filter {

    private static final String LOGIN_PATTERN = "/login";
    private static final String RESOURCES_PATTERN = "/resources";
    public static final String USER_SESSION_AUTHENTICATION = "userSessionAuthentication";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String currentUrl = ((HttpServletRequest) servletRequest).getRequestURL().toString();
        String loginUrl = ((HttpServletRequest) servletRequest).getContextPath() + LOGIN_PATTERN;
        if (canDoFilter(currentUrl, (HttpServletRequest) servletRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            ((HttpServletResponse) servletResponse).sendRedirect(loginUrl);
        }
    }

    @Override
    public void destroy() {

    }

    private boolean canDoFilter(String currentUrl, HttpServletRequest request) {
        return StringUtils.contains(currentUrl, LOGIN_PATTERN)
                || StringUtils.contains(currentUrl, RESOURCES_PATTERN)
                || request.getSession().getAttribute(USER_SESSION_AUTHENTICATION) != null;
    }
}
