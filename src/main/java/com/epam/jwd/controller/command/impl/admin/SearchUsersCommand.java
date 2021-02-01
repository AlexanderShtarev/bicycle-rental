package com.epam.jwd.controller.command.impl.admin;

import com.epam.jwd.controller.PageConstant;
import com.epam.jwd.controller.RequestConstant;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.criteria.UserCriteria;
import com.epam.jwd.domain.User;
import com.epam.jwd.domain.UserRole;
import com.epam.jwd.domain.UserStatus;
import com.epam.jwd.service.ServiceFactory;
import com.epam.jwd.service.UserService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class SearchUsersCommand extends Command {

    @Override
    public void process() throws ServletException, IOException {
        String name = request.getParameter(RequestConstant.NAME);
        String email = request.getParameter(RequestConstant.EMAIL);
        String status = request.getParameter("");
        String role = request.getParameter("");


        UserCriteria criteria = UserCriteria.builder()
                .name(name)
                .email(email)
                .status(UserStatus.resolveStatusById(Integer.valueOf(status)))
                .roles(Collections.singletonList(UserRole.resolveRoleById(Integer.valueOf(role))))
                .build();

        UserService userService = ServiceFactory.getInstance().getUserService();
        List<User> userList = (List<User>) userService.getAllUsersByCriteria(criteria);

        request.setAttribute(RequestConstant.USERS, userList);
        forward(PageConstant.SEARCH_USER_PAGE);
    }

}
