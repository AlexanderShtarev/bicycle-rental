package com.epam.jwd.context;

import com.epam.jwd.context.config.DatabaseConfig;
import com.epam.jwd.context.config.MailConfig;
import com.epam.jwd.exception.InitializingException;
import com.epam.jwd.pool.ConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationContext {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationContext.class);
    private static ApplicationContext instance;

    private ApplicationContext() {
        init();
    }

    public static ApplicationContext getInstance() {
        if (instance == null) {
            instance = new ApplicationContext();
        }
        return instance;
    }

    void init() {
        LOGGER.trace("Initializing application configuration");
        try {
            DatabaseConfig.getInstance();
            MailConfig.getInstance();
            ConnectionPool.getInstance();
            LOGGER.info("Application configuration initialized");
        } catch (InitializingException e) {
            LOGGER.error("Application initializing failed");
            System.exit(1);
        }
    }

    public void shutdown() {
        LOGGER.info("Shutdown application");
        ConnectionPool.getInstance().shutdown();
        LOGGER.info("Application shutdown successfully");
    }

}
