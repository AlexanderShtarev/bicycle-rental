package com.epam.jwd.controller.command;

import com.epam.jwd.controller.command.impl.ToHomePageCommand;

import java.util.Map;

public class CommandType {
    private static final Map<String, Command> COMMANDS = initMap();

    private static Map<String, Command> initMap() {
        return Map.ofEntries(
            Map.entry("TO_HOME_PAGE", new ToHomePageCommand()));
    }

    public static Map<String, Command> getCommands() {
        return COMMANDS;
    }
}
