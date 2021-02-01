package com.epam.jwd.controller.command.impl.admin;

import com.epam.jwd.context.ApplicationContext;
import com.epam.jwd.context.Context;
import com.epam.jwd.controller.RequestConstant;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.criteria.UserCriteria;
import com.epam.jwd.domain.User;
import com.epam.jwd.domain.UserStatus;
import com.epam.jwd.service.ServiceFactory;
import com.epam.jwd.service.UserService;

import javax.servlet.ServletException;
import java.io.IOException;

public class BlockUserCommand extends Command {
    Context context = ApplicationContext.APPLICATION_CONTEXT;
    UserService userService;

    public BlockUserCommand() {
        ServiceFactory serviceFactory = context.getServiceFactory();
        userService = serviceFactory.getUserService();
    }

    @Override
    public void process() throws ServletException, IOException {
        String userId = request.getParameter(RequestConstant.USER);

        UserCriteria criteria = UserCriteria.builder().id(Long.valueOf(userId)).build();
        User user = userService.getSingleUserByCriteria(criteria);

        user.setStatus(UserStatus.BLOCKED);

        userService.updateUser(user);

        forward(RequestConstant.PAGE);

    }
}
