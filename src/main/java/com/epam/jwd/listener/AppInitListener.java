package com.epam.jwd.listener;

import com.epam.jwd.context.ApplicationContext;
import com.epam.jwd.pool.ConnectionPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppInitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ApplicationContext.APPLICATION_CONTEXT.init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        ConnectionPool.getInstance().shutdown();
    }
}
