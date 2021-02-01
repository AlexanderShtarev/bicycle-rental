package com.epam.jwd.controller.command.impl;

import com.epam.jwd.context.ApplicationContext;
import com.epam.jwd.controller.PageConstant;
import com.epam.jwd.controller.RequestConstant;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.criteria.UserCriteria;
import com.epam.jwd.domain.User;
import com.epam.jwd.service.AuthService;
import com.epam.jwd.service.MailService;
import com.epam.jwd.service.ServiceFactory;
import com.epam.jwd.service.UserService;
import com.lambdaworks.crypto.SCryptUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCommand extends Command {
    UserService userService;
    AuthService authService;
    MailService mailService;

    public LoginCommand() {
        ServiceFactory serviceFactory = ApplicationContext.APPLICATION_CONTEXT.getServiceFactory();
        userService = serviceFactory.getUserService();
        authService = serviceFactory.getAuthService();
        mailService = serviceFactory.getMailService();
    }

    @Override
    public void process() throws ServletException, IOException {
        String email = request.getParameter(RequestConstant.EMAIL);
        String password = request.getParameter(RequestConstant.PASSWORD);
        HttpSession session = request.getSession();

        UserCriteria criteria = UserCriteria.builder().email(email).build();

        User user = userService.getSingleUserByCriteria(criteria);

        if (user != null && SCryptUtil.check(password, user.getPassword())) {
            session.setAttribute(RequestConstant.USER, user);
            request.setAttribute(RequestConstant.PAGE, PageConstant.HOME_PAGE);
            forward(PageConstant.HOME_PAGE);
        } else {
            request.setAttribute(RequestConstant.ERROR, "Wrong Email or Password");
            forward(PageConstant.ERROR_PAGE);
        }

    }

}
