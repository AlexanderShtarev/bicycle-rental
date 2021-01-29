package com.epam.jwd.controller.command.impl.admin;

import com.epam.jwd.controller.PageConstant;
import com.epam.jwd.controller.command.Command;

import javax.servlet.ServletException;
import java.io.IOException;

public class ToAddProductPageCommand extends Command {

    @Override
    public void process() throws ServletException, IOException {
        forward(PageConstant.ADD_PRODUCT_PAGE);
    }

}
