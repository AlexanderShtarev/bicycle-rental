package com.epam.jwd.controller.action.impl;

import com.epam.jwd.controller.action.Action;
import com.epam.jwd.domain.User;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.service.UserService;
import com.epam.jwd.service.impl.UserServiceImpl;
import com.epam.jwd.util.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ViewUsersAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(ViewUsersAction.class);
    private final UserService userService;

    public ViewUsersAction() {
        this.userService = UserServiceImpl.getInstance();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException, IOException, ServletException {

        LOGGER.debug("VIew users action received");

        List<User> users = userService.getAllUsers();

        if (!users.isEmpty()) {
            JSONParser parser = new JSONParser();
            String json = String.valueOf(parser.parseUsersToJSON(users));
            PrintWriter writer = response.getWriter();
            writer.write(json);
            writer.close();
        }

    }

}
