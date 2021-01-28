package com.epam.jwd.controller.command;

import com.epam.jwd.controller.command.impl.*;

import java.util.Map;

public class CommandType {
    private static final Map<String, Command> COMMANDS = initMap();

    private static Map<String, Command> initMap() {
        return Map.ofEntries(
                Map.entry("CHECK_OTP", new CheckOtpCommand()),
                Map.entry("TO_HOME_PAGE", new ToHomePageCommand()),
                Map.entry("TO_LOGIN_PAGE", new ToLoginPageCommand()),
                Map.entry("TO_REGISTER_PAGE", new ToRegisterPageCommand()),
                Map.entry("LOGIN", new LoginCommand()),
                Map.entry("REGISTER", new RegisterCommand()));
    }

    public static Map<String, Command> getCommands() {
        return COMMANDS;
    }
}
