package com.epam.jwd.controller.command.impl;

import com.epam.jwd.controller.PageConstant;
import com.epam.jwd.controller.command.Command;

import javax.servlet.ServletException;
import java.io.IOException;

public class ToLoginPageCommand extends Command {

    @Override
    public void process() throws ServletException, IOException {
        forward(PageConstant.LOGIN_PAGE);
    }

}
