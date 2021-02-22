package com.epam.jwd.controller.command.impl;

import com.epam.jwd.controller.PageConstant;
import com.epam.jwd.controller.RequestConstant;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.resolver.ResolveAction;
import com.epam.jwd.controller.command.resolver.ViewResolver;
import com.epam.jwd.domain.Token;
import com.epam.jwd.domain.User;
import com.epam.jwd.domain.UserStatus;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.service.ServiceFactory;
import com.epam.jwd.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ConfirmAccountCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfirmAccountCommand.class);
    private final UserService userService;

    public ConfirmAccountCommand() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        this.userService = serviceFactory.getUserService();
    }

    @Override
    public ViewResolver resolve(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException, ServletException, IOException {

        LOGGER.debug("Confirm account command received");

        String inputToken = request.getParameter(RequestConstant.TOKEN);
        Token token = userService.getUserToken(inputToken);

        if (token != null) {
            User user = userService.getUserById(token.getUser().getId());
            user.setStatus(UserStatus.ACTIVE);
            userService.updateUser(user);

            request.getSession().setAttribute(RequestConstant.CART_BEAN, userService.getUserCart(user.getId()));
            request.getSession().setAttribute(RequestConstant.USER, user);
        }

        return new ViewResolver(PageConstant.HOME_PAGE, ResolveAction.REDIRECT);
    }

}
