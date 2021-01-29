package com.epam.jwd.controller.command.impl.user;

import com.epam.jwd.controller.PageConstant;
import com.epam.jwd.controller.RequestConstant;
import com.epam.jwd.controller.command.Command;

import javax.servlet.ServletException;
import java.io.IOException;

public class ToProfilePageCommand extends Command {

    @Override
    public void process() throws ServletException, IOException {
        request.setAttribute(RequestConstant.PAGE, PageConstant.PROFILE_PAGE);
        forward(PageConstant.PROFILE_PAGE);
    }

}
