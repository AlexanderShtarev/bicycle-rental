package com.epam.jwd.controller.command.impl;

import com.epam.jwd.controller.PageConstant;
import com.epam.jwd.controller.RequestConstant;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.domain.User;
import com.epam.jwd.domain.UserRole;
import com.epam.jwd.domain.UserStatus;
import com.epam.jwd.domain.VerificationToken;
import com.epam.jwd.service.AuthService;
import com.epam.jwd.service.MailService;
import com.epam.jwd.service.ServiceFactory;
import com.epam.jwd.service.UserService;
import com.epam.jwd.service.impl.AuthServiceImpl;
import com.epam.jwd.service.impl.MailServiceImpl;
import com.epam.jwd.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class RegisterCommand extends Command {
    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    UserService userService = serviceFactory.getUserService();
    AuthService authService = serviceFactory.getAuthService();
    MailService mailService = serviceFactory.getMailService();

    @Override
    public void process() throws ServletException, IOException {
        String name = request.getParameter(RequestConstant.NAME);
        String email = request.getParameter(RequestConstant.EMAIL);
        String password = request.getParameter(RequestConstant.PASSWORD);
        String cPassword = request.getParameter(RequestConstant.C_PASSWORD);

        HttpSession session = request.getSession();

        if (password.equals(cPassword)) {
            User user = createUser(name, email, password);
            if (authService.register(user)) {
                VerificationToken token = mailService.createVerificationToken(user);
                mailService.send();

                session.setAttribute(RequestConstant.USER, user);
                forward(PageConstant.OTP_PAGE);
            } else {
                request.setAttribute(RequestConstant.ERROR, "User already exist");
                forward(PageConstant.ERROR_PAGE);
            }
        } else {
            request.setAttribute(RequestConstant.ERROR, "Password not match");
            forward(PageConstant.ERROR_PAGE);
        }

    }

    private User createUser(String name, String email, String password) {
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .status(UserStatus.NON_ACTIVE)
                .balance(0d)
                .roles(new ArrayList<>() {{
                    add(UserRole.CLIENT);
                }})
                .build();
    }

}
