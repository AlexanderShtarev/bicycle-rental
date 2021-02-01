package com.epam.jwd.controller.command.impl.user;

import com.epam.jwd.context.Context;
import com.epam.jwd.context.ApplicationContext;
import com.epam.jwd.controller.PageConstant;
import com.epam.jwd.controller.RequestConstant;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.criteria.UserCriteria;
import com.epam.jwd.domain.User;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.service.ServiceFactory;
import com.epam.jwd.service.UserService;
import com.epam.jwd.validator.AnnotationValidator;
import com.epam.jwd.validator.EntityValidator;
import com.epam.jwd.validator.rule.LengthRule;
import com.epam.jwd.validator.rule.NotEmptyRule;
import com.epam.jwd.validator.rule.PatternRule;
import com.lambdaworks.crypto.SCryptUtil;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;

public class UpdateUserProfileCommand extends Command {
    Context applicationContext = ApplicationContext.APPLICATION_CONTEXT;
    ServiceFactory serviceFactory;
    UserService userService;
    EntityValidator entityValidator;
    NotEmptyRule notEmptyRule;
    PatternRule patternRule;
    LengthRule lengthRule;

    public UpdateUserProfileCommand() {
        serviceFactory = applicationContext.getServiceFactory();
        userService = serviceFactory.getUserService();

        notEmptyRule = new NotEmptyRule();
        patternRule = new PatternRule();
        lengthRule = new LengthRule();

        entityValidator = new AnnotationValidator(new ArrayList<>() {{
            add(notEmptyRule);
            add(patternRule);
            add(lengthRule);
        }});
    }

    @Override
    public void process() throws ServletException, IOException {
        String name = request.getParameter(RequestConstant.NAME);
        String email = request.getParameter(RequestConstant.EMAIL);
        String password = request.getParameter(RequestConstant.PASSWORD);
        String cPassword = request.getParameter(RequestConstant.C_PASSWORD);

        if (!password.equals(cPassword)) {
            request.setAttribute(RequestConstant.ERROR, "Password not match");
            forward(PageConstant.ERROR_PAGE);
        }


        UserCriteria criteria = UserCriteria.builder().email(email).build();
        User user = userService.getSingleUserByCriteria(criteria);

        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        try {
            entityValidator.validate(user);
        } catch (ValidationException e) {
            request.setAttribute(RequestConstant.ERROR, e.getMessage());
            forward(PageConstant.ERROR_PAGE);
        }

        user.setPassword(SCryptUtil.scrypt(password, 16, 16, 16));
        userService.updateUser(user);

    }

}
