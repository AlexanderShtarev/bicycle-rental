package com.epam.jwd.controller.command;

import com.epam.jwd.controller.command.impl.admin.*;
import com.epam.jwd.controller.command.impl.user.*;

import java.util.Map;

public class CommandType {
    private static final Map<String, Command> COMMANDS = initMap();

    private static Map<String, Command> initMap() {
        return Map.ofEntries(
                //admin
                Map.entry("ADD_PRODUCT", new AddProductCommand()),
                Map.entry("DELETE_PRODUCT", new DeleteProductCommand()),
                Map.entry("SEARCH_PRODUCT", new SearchProductCommand()),

                Map.entry("TO_ADD_PRODUCT_PAGE", new ToAddProductPageCommand()),
                Map.entry("TO_UPDATE_PRODUCT_PAGE", new ToUpdateProductPageCommand()),
                Map.entry("TO_VIEW_PRODUCTS_PAGE", new ToViewProductsPageCommand()),
                Map.entry("TO_SEARCH_PRODUCT_PAGE", new ToSearchProductPageCommand()),

                Map.entry("UPDATE_PRODUCT", new UpdateProductCommand()),

                //user
                Map.entry("CHECK_OTP", new CheckOtpCommand()),
                Map.entry("LOGIN", new LoginCommand()),
                Map.entry("LOGOUT", new LogoutCommand()),
                Map.entry("REGISTER", new RegisterCommand()),

                Map.entry("TO_HOME_PAGE", new ToHomePageCommand()),
                Map.entry("TO_LOGIN_PAGE", new ToLoginPageCommand()),
                Map.entry("TO_PRODUCTS_PAGE", new ToProductsPageCommand()),
                Map.entry("TO_PROFILE_PAGE", new ToProfilePageCommand()),
                Map.entry("TO_REGISTER_PAGE", new ToRegisterPageCommand()),

                Map.entry("UPDATE_USER", new UpdateUserCommand()));
    }

    public static Map<String, Command> getCommands() {
        return COMMANDS;
    }

}
