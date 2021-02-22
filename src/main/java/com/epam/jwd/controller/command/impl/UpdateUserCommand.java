package com.epam.jwd.controller.command.impl;

import com.epam.jwd.controller.PageConstant;
import com.epam.jwd.controller.RequestConstant;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.resolver.ResolveAction;
import com.epam.jwd.controller.command.resolver.ViewResolver;
import com.epam.jwd.domain.User;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.service.ServiceFactory;
import com.epam.jwd.service.UserService;
import com.epam.jwd.util.MessageConstant;
import com.epam.jwd.util.MessageManager;
import com.epam.jwd.validator.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateUserCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateUserCommand.class);
    private final UserService userService;

    public UpdateUserCommand() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        this.userService = serviceFactory.getUserService();
    }

    @Override
    public ViewResolver resolve(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException, IOException, ServletException {

        LOGGER.debug("Update user command received");

        ViewResolver resolver = new ViewResolver(PageConstant.PROFILE_PAGE);

        String email = request.getParameter(RequestConstant.EMAIL);
        String password = request.getParameter(RequestConstant.PASSWORD);
        String name = request.getParameter(RequestConstant.NAME);
        String surname = request.getParameter(RequestConstant.SURNAME);

        User user = (User) request.getSession().getAttribute(RequestConstant.USER);

        if (UserValidator.checkEmail(email).isEmpty()) {
            user.setEmail(email);
        }
        if (userService.getUserByEmail(email) == null || user.getEmail().equals(email)) {
            if (UserValidator.checkPassword(password).isEmpty()) {
                user.setPassword(password);
            }
            if (UserValidator.checkString(name).isEmpty()) {
                user.setName(name);
            }
            if (UserValidator.checkString(surname).isEmpty()) {
                user.setSurname(surname);
            }
            userService.updateUser(user);
            request.getSession().setAttribute(RequestConstant.USER, user);
            resolver.setResolveAction(ResolveAction.REDIRECT);
        } else {
            request.setAttribute(RequestConstant.ERROR,
                    MessageManager.INSTANCE.getMessage(MessageConstant.USER_ALREADY_EXIST));
        }

        return resolver;
    }
}
