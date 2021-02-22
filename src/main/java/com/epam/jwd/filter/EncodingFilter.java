package com.epam.jwd.filter;

import com.epam.jwd.controller.RequestConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;

@WebFilter(filterName = "encoding", urlPatterns = {"/*"},
        initParams = {
                @WebInitParam(name = "encoding", value = "UTF-8", description = "Encoding Param"),
                @WebInitParam(name = "locale", value = "en_US", description = "Locale Param")})
public class EncodingFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(EncodingFilter.class);

    private String encoding;

    public void init(FilterConfig config) throws ServletException {
        LOGGER.debug("Encoding filter initializing");

        encoding = config.getInitParameter("encoding");
        if (encoding == null) encoding = "UTF-8";
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain next) throws IOException, ServletException {
        if (null == request.getCharacterEncoding()) request.setCharacterEncoding(encoding);

        LOGGER.trace("Setting content type and character encoding");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpServletRequest req = (HttpServletRequest) request;
        if (req.getSession().getAttribute(RequestConstant.LOCALE) == null) {
            req.getSession().setAttribute(RequestConstant.LOCALE, Locale.getDefault());
        }

        next.doFilter(request, response);
    }

}
