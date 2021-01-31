package com.epam.jwd.filter;

import com.epam.jwd.controller.RequestConstant;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/", filterName = "RoleFilter")
public class UserRoleFilter implements Filter {
    private AuthHandler authHandler;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.authHandler = AuthHandler.getInstance();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (authHandler.havePrivilege(request.getSession(), request.getParameter(RequestConstant.COMMAND))) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            response.sendRedirect("/home");
        }
    }

    @Override
    public void destroy() {
    }

}
