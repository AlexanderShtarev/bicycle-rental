package com.epam.jwd.listener;

import com.epam.jwd.context.cache.AppCache;
import com.epam.jwd.pool.ConnectionPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppInitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ConnectionPool.getInstance();
        AppCache.APPLICATION_CACHE.init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        ConnectionPool.getInstance().shutdown();
    }
}
