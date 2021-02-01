package com.epam.jwd.controller.command.impl.admin;

import com.epam.jwd.controller.RequestConstant;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.criteria.UserCriteria;
import com.epam.jwd.service.ServiceFactory;
import com.epam.jwd.service.UserService;

import javax.servlet.ServletException;
import java.io.IOException;

public class BlockUserCommand extends Command {
    ServiceFactory serviceFactory;
    UserService userService;

    public BlockUserCommand() {
        serviceFactory = ServiceFactory.getInstance();
        userService = serviceFactory.getUserService();
    }

    @Override
    public void process() throws ServletException, IOException {
        String userId = request.getParameter(RequestConstant.USER);

        UserCriteria criteria = UserCriteria.builder().id(Long.valueOf(userId)).build();
        userService.getSingleUserByCriteria(criteria);

    }
}
