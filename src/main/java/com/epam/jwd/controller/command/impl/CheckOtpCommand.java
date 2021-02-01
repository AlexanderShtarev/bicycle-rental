package com.epam.jwd.controller.command.impl;

import com.epam.jwd.controller.PageConstant;
import com.epam.jwd.controller.RequestConstant;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.domain.User;
import com.epam.jwd.domain.VerificationToken;
import com.epam.jwd.service.AuthService;
import com.epam.jwd.service.MailService;
import com.epam.jwd.service.ServiceFactory;
import com.epam.jwd.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CheckOtpCommand extends Command {
    UserService userService;
    AuthService authService;
    MailService mailService;

    public CheckOtpCommand() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        userService = serviceFactory.getUserService();
        authService = serviceFactory.getAuthService();
        mailService = serviceFactory.getMailService();
    }

    @Override
    public void process() throws ServletException, IOException {
        String token = request.getParameter(RequestConstant.TOKEN);
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute(RequestConstant.USER);

        if (user == null) {
            forward(PageConstant.LOGIN_PAGE);
        }

        VerificationToken verificationToken = mailService.getVerificationToken(token);

        if (verificationToken != null && verificationToken.getUser().equals(user)) {
            forward(PageConstant.HOME_PAGE);
        } else {
            request.setAttribute(RequestConstant.ERROR, "Wrong token");
            forward(PageConstant.ERROR_PAGE);
        }

    }

}
