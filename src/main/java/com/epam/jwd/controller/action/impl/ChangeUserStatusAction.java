package com.epam.jwd.controller.action.impl;

import com.epam.jwd.controller.RequestConstant;
import com.epam.jwd.controller.action.Action;
import com.epam.jwd.domain.User;
import com.epam.jwd.domain.UserStatus;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.service.UserService;
import com.epam.jwd.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeUserStatusAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChangeUserStatusAction.class);
    private final UserService userService;

    public ChangeUserStatusAction() {
        this.userService = UserServiceImpl.getInstance();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException, IOException, ServletException {

        LOGGER.debug("Change user status command received");

        String userId = request.getParameter(RequestConstant.USER_ID);

        User user = userService.getUserById(Integer.parseInt(userId));
        if (user.getStatus().equals(UserStatus.BLOCKED)) {
            user.setStatus(UserStatus.ACTIVE);
        } else {
            user.setStatus(UserStatus.BLOCKED);
        }
        userService.updateUser(user);

    }

}
