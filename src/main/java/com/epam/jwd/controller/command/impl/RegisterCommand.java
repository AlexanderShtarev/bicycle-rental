package com.epam.jwd.controller.command.impl;

import com.epam.jwd.controller.PageConstant;
import com.epam.jwd.controller.RequestConstant;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.criteria.UserCriteria;
import com.epam.jwd.domain.User;
import com.epam.jwd.domain.UserRole;
import com.epam.jwd.domain.UserStatus;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.service.MailService;
import com.epam.jwd.service.ServiceFactory;
import com.epam.jwd.service.UserService;
import com.epam.jwd.validator.AnnotationValidator;
import com.epam.jwd.validator.EntityValidator;
import com.epam.jwd.validator.rule.NotEmptyRule;
import com.epam.jwd.validator.rule.PatternRule;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class RegisterCommand extends Command {
    UserService userService;
    MailService mailService;
    EntityValidator entityValidator;
    NotEmptyRule notEmptyRule;
    PatternRule patternRule;

    public RegisterCommand() {

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        userService = serviceFactory.getUserService();
        mailService = serviceFactory.getMailService();

        notEmptyRule = new NotEmptyRule();
        patternRule = new PatternRule();
        entityValidator = new AnnotationValidator(new ArrayList<>() {{
            add(notEmptyRule);
            add(patternRule);
        }});

    }

    @Override
    public void process() throws ServletException, IOException {
        String name = request.getParameter(RequestConstant.NAME);
        String email = request.getParameter(RequestConstant.EMAIL);
        String password = request.getParameter(RequestConstant.PASSWORD);
        String cPassword = request.getParameter(RequestConstant.C_PASSWORD);

        if (!password.equals(cPassword)) {
            request.setAttribute(RequestConstant.ERROR, "Passwords not match");
            forward(PageConstant.ERROR_PAGE);
        }

        UserCriteria criteria = UserCriteria.builder().email(email).build();
        User user = userService.getSingleUserByCriteria(criteria);
        if (user != null) {
            request.setAttribute(RequestConstant.ERROR, "User already exists");
            forward(PageConstant.REGISTER_PAGE);
        }

        user = this.createUser(name, email, password);
        try {
            entityValidator.validate(user);
        } catch (ValidationException e) {
            request.setAttribute(RequestConstant.ERROR, e.getMessage());
            forward(PageConstant.ERROR_PAGE);
        }

        userService.registerUser(user);

        mailService.sendVerificationToken(user);

        request.getSession().setAttribute(RequestConstant.USER, user);
        forward(PageConstant.OTP_PAGE);

    }

    private User createUser(String name, String email, String password) {
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .status(UserStatus.NON_ACTIVE)
                .balance(0d)
                .roles(Collections.singletonList(UserRole.CLIENT))
                .build();
    }

}
