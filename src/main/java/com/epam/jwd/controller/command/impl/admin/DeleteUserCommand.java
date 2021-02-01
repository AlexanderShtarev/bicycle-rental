package com.epam.jwd.controller.command.impl.admin;

import com.epam.jwd.controller.PageConstant;
import com.epam.jwd.controller.RequestConstant;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.service.ServiceFactory;
import com.epam.jwd.service.UserService;

import javax.servlet.ServletException;
import java.io.IOException;

public class DeleteUserCommand extends Command {

    @Override
    public void process() throws ServletException, IOException {
        String userId = request.getParameter(RequestConstant.USER_ID);

        UserService userService = ServiceFactory.getInstance().getUserService();

        userService.deleteUser(Long.valueOf(userId));

        forward(PageConstant.UPDATE_USER_PAGE);
    }

}
