package com.epam.jwd.controller.command;

import com.epam.jwd.controller.PageConstant;
import com.epam.jwd.controller.command.impl.ForwardCommand;
import com.mysql.cj.util.StringUtils;

public class CommandFactory {

    private CommandFactory() {
    }

    public static Command getCommand(String commandName) {

        if (StringUtils.isNullOrEmpty(commandName)) {
            throw new IllegalArgumentException();
        }

        return CommandType.getCommands()
                .getOrDefault(commandName.toUpperCase(), new ForwardCommand(PageConstant.HOME_PAGE));

    }

}
