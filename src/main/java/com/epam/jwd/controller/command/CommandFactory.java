package com.epam.jwd.controller.command;

import com.epam.jwd.controller.command.impl.*;
import com.epam.jwd.controller.command.impl.admin.*;
import com.epam.jwd.controller.command.impl.direction.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class CommandFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandFactory.class);
    private static final Map<String, Command> COMMAND_MAP = initCommands();
    private static CommandFactory instance;

    public static CommandFactory getInstance() {
        if (instance == null) {
            instance = new CommandFactory();
        }
        return instance;
    }

    private CommandFactory() {
    }

    private static Map<String, Command> initCommands() {
        LOGGER.trace("Initializing commands map");
        return Map.ofEntries(
                Map.entry("to_home_page", new ToHomePageCommand()),
                Map.entry("to_registration_page", new ToRegistrationPageCommand()),
                Map.entry("to_login_page", new ToLoginPageCommand()),
                Map.entry("register", new RegisterCommand()),
                Map.entry("to_products_page", new ToProductsPageCommand()),
                Map.entry("login", new LoginCommand()),
                Map.entry("logout", new LogoutCommand()),
                Map.entry("to_profile_page", new ToProfilePageCommand()),
                Map.entry("update_user", new UpdateUserCommand()),
                Map.entry("add_card", new AddCardCommand()),
                Map.entry("delete_card", new DeleteCardCommand()),
                Map.entry("top_up_balance", new TopUpBalanceCommand()),
                Map.entry("add_category", new AddCategoryCommand()),
                Map.entry("add_manufacturer", new AddManufacturerCommand()),
                Map.entry("add_product", new AddProductCommand()),
                Map.entry("to_update_product_page", new ToUpdateProductPageCommand()),
                Map.entry("update_product", new UpdateProductCommand()),
                Map.entry("to_cart_page", new ToCartPageCommand()),
                Map.entry("change_locale", new ChangeLocaleCommand()),
                Map.entry("to_view_products", new ToViewProductsPageCommand()),
                Map.entry("to_view_users", new ToViewUsersPageCommand()),
                Map.entry("to_view_categories", new ToViewCategoriesPageCommand()),
                Map.entry("to_view_manufacturers", new ToViewManufacturersPageCommand()),
                Map.entry("to_admin", new ToAdminPageCommand()),
                Map.entry("confirm_account", new ConfirmAccountCommand()),
                Map.entry("to_contact_us_page", new ToContactUsPageCommand()),
                Map.entry("contact_us", new ContactUsCommand()),
                Map.entry("rent", new RentCommand()),
                Map.entry("to_view_rentals", new ToViewRentalsPageCommand())
        );
    }

    public Command getCommand(String commandName) {

        LOGGER.info("Resolving command");
        return COMMAND_MAP.getOrDefault(commandName, new ToHomePageCommand());
    }

}
