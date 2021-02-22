package com.epam.jwd.controller;

import com.epam.jwd.controller.action.ActionFactory;
import com.epam.jwd.controller.action.Action;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.validator.Validator;
import com.mysql.cj.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet for ajax requests
 */
@WebServlet(name = "AjaxServlet", urlPatterns = "/test")
public class AjaxServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(AjaxServlet.class);
    ActionFactory actionFactory;

    @Override
    public void init() throws ServletException {
        LOGGER.debug("Ajax Servlet initializing");
        super.init();
        this.actionFactory = ActionFactory.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        LOGGER.debug("Service in Ajax Servlet");
        String actionName = request.getParameter(RequestConstant.ACTION);

        if (StringUtils.isNullOrEmpty(actionName)) {
            LOGGER.warn("Empty action name");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Action action = actionFactory.getAction(actionName);

        try {
            LOGGER.info("executing action");
            action.execute(request, response);
        } catch (ServiceException e) {
            LOGGER.warn("Service provided exception to ajax servlet:", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        super.service(request, response);

    }

}