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

    private String excludePattern;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        excludePattern = filterConfig.getInitParameter("excludePattern");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String currentUrl = ((HttpServletRequest) servletRequest).getRequestURL().toString();
        String loginUrl = ((HttpServletRequest) servletRequest).getContextPath() + excludePattern;
        if (SecurityContextHolder.getContext().getAuthentication() != null
                || StringUtils.contains(currentUrl, loginUrl)
                || StringUtils.contains(currentUrl, ".css")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            ((HttpServletResponse) servletResponse).sendRedirect(loginUrl);
        }
    }

    @Override
    public void destroy() {

    }
}
