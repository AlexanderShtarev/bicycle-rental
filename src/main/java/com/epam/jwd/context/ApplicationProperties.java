package com.epam.jwd.context;

import com.epam.jwd.pool.DatabaseConfig;

public class ApplicationProperties {
    public static final ApplicationProperties APPLICATION_PROPERTIES = new ApplicationProperties();

    DatabaseConfig databaseConfig;

    MailProperties mailProperties;

    public void init() {

        mailProperties.init();

        databaseConfig.init();

    }

}
