package com.epam.jwd.controller.command.impl;

import com.epam.jwd.controller.PageConstant;
import com.epam.jwd.controller.RequestConstant;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.criteria.VerificationTokenCriteria;
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
    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    UserService userService = serviceFactory.getUserService();
    AuthService authService = serviceFactory.getAuthService();
    MailService mailService = serviceFactory.getMailService();

    @Override
    public void process() throws ServletException, IOException {
        HttpSession session = request.getSession();

        String token = request.getParameter(RequestConstant.TOKEN);
        User user = (User) session.getAttribute(RequestConstant.USER);

        if (user == null) {
            forward(PageConstant.LOGIN_PAGE);
        }

        VerificationTokenCriteria criteria = VerificationTokenCriteria.builder().token(token).build();
        VerificationToken verificationToken = mailService.findTokenByCriteria(criteria);

        if (verificationToken != null) {
            if (mailService.validateToken(verificationToken, user)) {
                forward(PageConstant.HOME_PAGE);
            }
        } else {
            request.setAttribute(RequestConstant.ERROR, "Wrong token");
            forward(PageConstant.ERROR_PAGE);
        }

    }

}
