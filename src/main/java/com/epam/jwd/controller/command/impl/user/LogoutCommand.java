package com.epam.jwd.controller.command.impl.user;

import com.epam.jwd.controller.PageConstant;
import com.epam.jwd.controller.command.Command;

import javax.servlet.ServletException;
import java.io.IOException;

public class LogoutCommand extends Command {

    @Override
    public void process() throws ServletException, IOException {
        request.getSession().invalidate();
        forward(PageConstant.HOME_PAGE);
    }

}
