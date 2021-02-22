package com.epam.jwd.context.config;

import com.epam.jwd.exception.InitializingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

public class MailConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(MailConfig.class);
    public static MailConfig instance;

    private String username;

    private String password;

    private String host;

    private String port;

    private String protocol;

    private String auth;

    private String debug;

    private String socketFactoryClass;

    private String socketFactoryFallback;

    private Properties props;

    private MailConfig() {
        init();
    }

    public static MailConfig getInstance() {
        if (instance == null) {
            instance = new MailConfig();
        }
        return instance;
    }

    private void init() throws InitializingException {
        LOGGER.trace("Initializing email configuration");
        try {
            ResourceBundle rb = ResourceBundle.getBundle("mail");
            this.username = rb.getString("mail.user.name");
            this.password = rb.getString("mail.user.password");
            this.host = rb.getString("mail.smtp.host");
            this.port = rb.getString("mail.smtp.port");
            this.protocol = rb.getString("mail.transport.protocol");
            this.auth =rb.getString("mail.smtp.auth");
            this.debug = rb.getString("mail.debug");
            this.socketFactoryClass = rb.getString("mail.smtp.socketFactory.class");
            this.socketFactoryFallback =rb.getString("mail.smtp.socketFactory.fallback");

            this.createProps();
        } catch (ExceptionInInitializerError | MissingResourceException e) {
            LOGGER.error("Error while initializing email configuration", e);
            throw new InitializingException("Error while initializing mail configuration", e);
        }
    }

    private void createProps() {
        this.props = new Properties();
        props.setProperty("mail.transport.protocol", this.protocol);
        props.setProperty("mail.host", this.host);
        props.put("mail.smtp.auth", this.auth);
        props.put("mail.smtp.port", this.port);
        props.put("mail.debug", this.debug);
        props.put("mail.smtp.socketFactory.port", this.port);
        props.put("mail.smtp.socketFactory.class", this.socketFactoryClass);
        props.put("mail.smtp.socketFactory.fallback", this.socketFactoryFallback);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Properties getProps() {
        return props;
    }
}
