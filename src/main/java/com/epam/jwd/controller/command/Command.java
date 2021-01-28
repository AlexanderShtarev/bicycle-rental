package com.epam.jwd.controller.command;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class Command {
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected ServletContext context;

    public void init( ServletContext servletContext, HttpServletRequest servletRequest,HttpServletResponse servletResponse) {
        this.request = servletRequest;
        this.response = servletResponse;
        this.context = servletContext;
    }

    public abstract void process() throws ServletException, IOException;

    protected void forward(String page) {
        String target = String.format("/jsp/%s.jsp", page);
        try {
            context.getRequestDispatcher(target).forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

}
