package com.epam.jwd.context;

import java.util.ResourceBundle;

public class MailProperties {
    public static final MailProperties MAIL_PROPERTIES = new MailProperties();

    private String username;

    private String password;

    private String host;

    private String port;

    private String auth;

    public void init() {
        ResourceBundle rb = ResourceBundle.getBundle("mail");
        this.username = rb.getString("mail.username");
        this.password = rb.getString("mail.password");
        this.host = rb.getString("mail.host");
        this.port = rb.getString("mail.port");
        this.auth = rb.getString("mail.smtp.auth");
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public String getAuth() {
        return auth;
    }

}
