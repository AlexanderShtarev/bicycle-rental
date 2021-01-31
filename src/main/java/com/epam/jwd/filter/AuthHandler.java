package com.epam.jwd.filter;

import javax.servlet.http.HttpSession;

public class AuthHandler {
    private static AuthHandler instance;

    private AuthHandler() {
    }

    public static AuthHandler getInstance() {
        if (instance == null) {
            instance = new AuthHandler();
        }

        return instance;
    }

    public boolean havePrivilege(HttpSession httpSession, String commandName) {
       return true; //todo
    }

}
