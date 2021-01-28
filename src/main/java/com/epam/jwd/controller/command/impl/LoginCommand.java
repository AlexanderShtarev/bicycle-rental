package com.epam.jwd.controller.command.impl;

import com.epam.jwd.controller.PageConstant;
import com.epam.jwd.controller.RequestConstant;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.domain.User;
import com.epam.jwd.service.AuthService;
import com.epam.jwd.service.MailService;
import com.epam.jwd.service.ServiceFactory;
import com.epam.jwd.service.UserService;
import com.epam.jwd.service.impl.AuthServiceImpl;
import com.epam.jwd.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCommand extends Command {
    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    UserService userService = serviceFactory.getUserService();
    AuthService authService = serviceFactory.getAuthService();
    MailService mailService = serviceFactory.getMailService();

    @Override
    public void process() throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        String email = request.getParameter(RequestConstant.EMAIL);
        String password = request.getParameter(RequestConstant.PASSWORD);

        User user = userService.findUserByEmail(email);

        if (user != null && authService.login(password, user.getPassword())) {
            httpSession.setAttribute(RequestConstant.USER, email);
            request.setAttribute(RequestConstant.PAGE, PageConstant.HOME_PAGE);
            forward(PageConstant.HOME_PAGE);
        } else {
            request.setAttribute(RequestConstant.ERROR, "Wrong Email or Password");
            forward(PageConstant.ERROR_PAGE);
        }
    }

}
