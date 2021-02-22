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
import com.epam.jwd.validator.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginCommand.class);
    private final UserService userService;

    public LoginCommand() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        this.userService = serviceFactory.getUserService();
    }

    @Override
    public ViewResolver resolve(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException, IOException, ServletException {

        LOGGER.debug("Login command received");

        HttpSession session = request.getSession();

        String email = request.getParameter(RequestConstant.EMAIL);
        String password = request.getParameter(RequestConstant.PASSWORD);

        List<String> errors = new ArrayList<>();

        Validator.checkString(password).ifPresent(errors::add);
        Validator.checkString(email).ifPresent(errors::add);

        if (errors.isEmpty()) {
            User user = userService.login(email, password);

            if (user != null) {

                session.setAttribute(RequestConstant.USER, user);
                session.setAttribute(RequestConstant.CART_BEAN, userService.getUserCart(user.getId()));
                return new ViewResolver(PageConstant.HOME_PAGE, ResolveAction.REDIRECT);

            } else {
                request.setAttribute(RequestConstant.ERROR,
                        MessageManager.INSTANCE.getMessage(MessageConstant.WRONG_EMAIL_OR_PASSWORD));
            }

        } else {
            request.setAttribute(RequestConstant.ERRORS, errors);
            request.setAttribute(RequestConstant.EMAIL, email);
        }

        return new ViewResolver(PageConstant.LOGIN_PAGE);
    }

}
