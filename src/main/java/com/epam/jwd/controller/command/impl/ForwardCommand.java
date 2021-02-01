package com.epam.jwd.controller.command.impl;

import com.epam.jwd.controller.command.Command;

import javax.servlet.ServletException;
import java.io.IOException;

public class ForwardCommand extends Command {

    private final String page;

    public ForwardCommand(String page) {
        this.page = page;
    }

    @Override
    public void process() throws ServletException, IOException {
        forward(page);
    }

}
