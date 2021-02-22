package com.epam.jwd.validator;

import com.epam.jwd.domain.User;
import com.epam.jwd.util.MessageConstant;
import com.epam.jwd.util.MessageManager;
import com.mysql.cj.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class UserValidator extends Validator {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserValidator.class);

    private static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
    private static final String EMAIL_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

    public static Optional<String> checkEmail(String email) {
        LOGGER.info("User email validation method has been started");
        String message = null;

        if (StringUtils.isNullOrEmpty(email)) {
            message = MessageManager.INSTANCE.getMessage(MessageConstant.EMPTY_FIELD)
                    + ": " + MessageManager.INSTANCE.getMessage("validation.email");
        } else if (!Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE).matcher(email).matches()) {
            message = MessageManager.INSTANCE.getMessage(MessageConstant.WRONG_EMAIL);
        }

        return Optional.ofNullable(message);
    }

    public static Optional<String> checkPassword(String password) {
        LOGGER.info("User password validation method has been started");
        String message = null;

        if (StringUtils.isNullOrEmpty(password)) {
            message = MessageManager.INSTANCE.getMessage(MessageConstant.EMPTY_FIELD)
                    + ": " + MessageManager.INSTANCE.getMessage("validation.password");
        } else if (!Pattern.compile(PASSWORD_REGEX, Pattern.CASE_INSENSITIVE).matcher(password).matches()) {
            message = MessageManager.INSTANCE.getMessage(MessageConstant.WRONG_PASSWORD);
        }

        return Optional.ofNullable(message);
    }

    public static List<String> checkUser(User user) {
        LOGGER.info("User validation method has been started");
        List<String> errors = new ArrayList<>();

        if (user != null) {
            checkString(user.getName()).ifPresent(errors::add);
            checkString(user.getSurname()).ifPresent(errors::add);
            checkEmail(user.getEmail()).ifPresent(errors::add);
            checkPassword(user.getPassword()).ifPresent(errors::add);
        } else {
            errors.add(MessageManager.INSTANCE.getMessage(MessageConstant.EMPTY_FIELD));
        }

        return errors;
    }

}
