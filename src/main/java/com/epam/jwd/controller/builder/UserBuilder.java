package com.epam.jwd.controller.builder;

import com.epam.jwd.controller.RequestConstant;
import com.epam.jwd.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class UserBuilder {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserBuilder.class);

    public static User buildUserFromRequest(HttpServletRequest request) {

        LOGGER.info("Getting user from request");

        return User.builder()
                .email(request.getParameter(RequestConstant.EMAIL))
                .password(request.getParameter(RequestConstant.PASSWORD))
                .name(request.getParameter(RequestConstant.NAME))
                .surname(request.getParameter(RequestConstant.SURNAME))
                .build();
    }

}
