package com.epam.jwd.validator;

import com.epam.jwd.util.MessageConstant;
import com.epam.jwd.util.MessageManager;
import com.mysql.cj.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Optional;
import java.util.regex.Pattern;

public class Validator {
    private static final Logger LOGGER = LoggerFactory.getLogger(Validator.class);
    protected static final String POSITIVE_INTEGER = "^[1-9]\\d*$";
    protected static final String POSITIVE_DOUBLE = "^\\d+(\\.\\d+)*$";

    public static boolean isValidString(String string) {
        return string != null && !string.isEmpty();
    }

    public static Optional<String> checkString(String string) {
        LOGGER.info("Validate for string method executing");
        String error = null;
        if (StringUtils.isNullOrEmpty(string)) {
            error = MessageManager.INSTANCE.getMessage(MessageConstant.EMPTY_FIELD);
        }
        return Optional.ofNullable(error);
    }

    public static Optional<String> checkPositiveInteger(String value) {
        LOGGER.info("Validate for positive integer method executing");
        String error = null;
        if (StringUtils.isNullOrEmpty(value) || !Pattern.compile(POSITIVE_INTEGER).matcher(value).matches()) {
            error = MessageManager.INSTANCE.getMessage(MessageConstant.WRONG_NUMBER);
        }
        return Optional.ofNullable(error);
    }

    public static Optional<String> checkPositiveDouble(String value) {
        LOGGER.info("Validate for positive double method executing");
        String error = null;
        if (StringUtils.isNullOrEmpty(value) || !Pattern.compile(POSITIVE_DOUBLE).matcher(value).matches()) {
            error = MessageManager.INSTANCE.getMessage(MessageConstant.WRONG_NUMBER);
        }
        return Optional.ofNullable(error);
    }

    public static Optional<String> checkDateMoreThanNow(Date expiration) {
        LOGGER.info("Date validation method has been started");
        String message = null;

        if (expiration == null) {
            message = MessageManager.INSTANCE.getMessage(MessageConstant.EMPTY_FIELD)
                    + ": " + MessageManager.INSTANCE.getMessage("validation.expiration");
        } else if (expiration.getTime() < new Date().getTime()) {
            message = MessageManager.INSTANCE.getMessage(MessageConstant.WRONG_EXPIRATION);
        }

        return Optional.ofNullable(message);
    }

}
