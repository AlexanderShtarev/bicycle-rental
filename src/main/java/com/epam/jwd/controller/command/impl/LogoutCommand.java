package com.epam.jwd.controller.command.impl;

import com.epam.jwd.controller.PageConstant;
import com.epam.jwd.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutCommand extends Command {

    @Override
    public void process() throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();

        forward(PageConstant.HOME_PAGE);
    }

}
