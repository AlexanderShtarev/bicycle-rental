package com.epam.jwd.controller.command;


import com.epam.jwd.controller.PageConstant;
import com.epam.jwd.controller.command.impl.*;
import com.epam.jwd.controller.command.impl.user.UpdateUserProfileCommand;

import java.util.Map;

public class CommandType {
    private static final Map<String, Command> COMMANDS = null;

    private static Map<String, Command> initMap() {
        return Map.ofEntries(
                //common
                Map.entry("CHECK_OTP", new CheckOtpCommand()),
                Map.entry("LOGIN", new LoginCommand()),
                Map.entry("LOGOUT", new LogoutCommand()),
                Map.entry("REGISTER", new RegisterCommand()),

                Map.entry("TO_REGISTER_PAGE", new ForwardCommand(PageConstant.REGISTER_PAGE)),
                Map.entry("TO_HOME_PAGE", new ForwardCommand(PageConstant.HOME_PAGE)),
                Map.entry("TO_LOGIN_PAGE", new ForwardCommand(PageConstant.LOGIN_PAGE)),
                Map.entry("TO_PRODUCTS_PAGE", new ForwardCommand(PageConstant.PRODUCTS_PAGE)),

                //user
                Map.entry("TO_PROFILE_PAGE", new ForwardCommand(PageConstant.PROFILE_PAGE)),
                Map.entry("UPDATE_USER_PROFILE", new UpdateUserProfileCommand()),

                //admin
                /*
                Map.entry("ADD_PRODUCT", new AddProductCommand()),
                Map.entry("DELETE_PRODUCT", new DeleteProductCommand()),
                Map.entry("SEARCH_PRODUCT", new SearchProductCommand()),
                Map.entry("UPDATE_PRODUCT", new UpdateProductCommand()),
                */

                Map.entry("TO_ADD_PRODUCT_PAGE", new ForwardCommand(PageConstant.ADD_PRODUCT_PAGE)),
                Map.entry("TO_UPDATE_PRODUCT_PAGE", new ForwardCommand(PageConstant.UPDATE_PRODUCT_PAGE)),
                Map.entry("TO_VIEW_PRODUCTS_PAGE", new ForwardCommand(PageConstant.VIEW_PRODUCTS_PAGE)),
                Map.entry("TO_SEARCH_PRODUCT_PAGE", new ForwardCommand(PageConstant.SEARCH_PRODUCT_PAGE)));
    }

    public static Map<String, Command> getCommands() {
        return COMMANDS;
    }

}
