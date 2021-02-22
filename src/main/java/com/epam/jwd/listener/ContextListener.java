package com.epam.jwd.listener;

import com.epam.jwd.context.ApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOGGER.debug("Context listener initializing");
        ApplicationContext.getInstance();
        LOGGER.debug("Context listener initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOGGER.debug("Context listener destroying");
        ApplicationContext.getInstance().shutdown();
        LOGGER.debug("Context listener destroyed");
    }
}
