package com.epam.jwd.controller.command.impl;

import com.epam.jwd.controller.PageConstant;
import com.epam.jwd.controller.RequestConstant;
import com.epam.jwd.controller.builder.UserBuilder;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.resolver.ResolveAction;
import com.epam.jwd.controller.command.resolver.ViewResolver;
import com.epam.jwd.domain.Token;
import com.epam.jwd.domain.User;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.service.ServiceFactory;
import com.epam.jwd.service.UserService;
import com.epam.jwd.util.MessageConstant;
import com.epam.jwd.util.MessageManager;
import com.epam.jwd.validator.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class RegisterCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterCommand.class);
    private static final String SUCCESS_MESSAGE_BODY =
            MessageManager.INSTANCE.getMessage(MessageConstant.CONFIRMATION_BODY);
    private static final String SUCCESS_MESSAGE_SUBJECT =
            MessageManager.INSTANCE.getMessage(MessageConstant.CONFIRMATION_SUBJECT);

    private final UserService userService;

    public RegisterCommand() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        this.userService = serviceFactory.getUserService();
    }

    @Override
    public ViewResolver resolve(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {

        LOGGER.debug("Register command received");

        ViewResolver resolver = new ViewResolver(PageConstant.REGISTRATION_PAGE, ResolveAction.FORWARD);
        User user = UserBuilder.buildUserFromRequest(request);

        List<String> errors = UserValidator.checkUser(user);

        if (errors.isEmpty()) {

            if (userService.register(user)) {
                LOGGER.debug("User registered");

                Token token = userService.createToken(user);
                String link = "http://localhost:8080/final_war_exploded/controller?command=confirm_account&token=";
                String confirmationLink = link + token.getToken();
                userService.sendMailMessage
                        (SUCCESS_MESSAGE_SUBJECT, SUCCESS_MESSAGE_BODY + confirmationLink, user.getEmail());

                return new ViewResolver(PageConstant.HOME_PAGE, ResolveAction.REDIRECT);
            } else {
                request.setAttribute(RequestConstant.ERROR,
                        MessageManager.INSTANCE.getMessage(MessageConstant.USER_ALREADY_EXIST));
            }

        } else {
            request.setAttribute(RequestConstant.ERRORS, errors);
        }

        request.setAttribute(RequestConstant.FORM_BEAN, user);
        return resolver;
    }

}
