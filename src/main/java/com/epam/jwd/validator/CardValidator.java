package com.epam.jwd.validator;

import com.epam.jwd.domain.CreditCard;
import com.epam.jwd.util.MessageConstant;
import com.epam.jwd.util.MessageManager;
import com.mysql.cj.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class CardValidator extends Validator {
    private static final Logger LOGGER = LoggerFactory.getLogger(CardValidator.class);
    protected static final String CREDIT_CARD_NUMBER = "^((67\\d{2})|(4\\d{3})|(5[1-5]\\d{2})|(6011))-?\\s?\\d{4}-?\\s?\\d{4}-?\\s?\\d{4}|3[4,7]\\d{13}$";

    public static List<String> checkCard(CreditCard card) {
        LOGGER.info("Card validation method has been started");
        List<String> errors = new ArrayList<>();

        if (card != null) {
            checkString(card.getOwner()).ifPresent(errors::add);
            checkCVV(card.getCVV()).ifPresent(errors::add);
            checkNumber(card.getNumber()).ifPresent(errors::add);
            checkDateMoreThanNow(card.getExpiration()).ifPresent(errors::add);
        }
        return errors;
    }

    private static Optional<String> checkNumber(String number) {
        LOGGER.info("Card number validation method has been started");
        String message = null;

        if (StringUtils.isNullOrEmpty(number)) {
            message = MessageManager.INSTANCE.getMessage(MessageConstant.EMPTY_FIELD)
                    + ": " + MessageManager.INSTANCE.getMessage("validation.number");
        } else {
            number = number.replaceAll("-", "");
            number = number.trim();
            if (!Pattern.compile(CREDIT_CARD_NUMBER).matcher(number).matches()) {
                message = MessageManager.INSTANCE.getMessage(MessageConstant.WRONG_NUMBER);
            }
        }

        return Optional.ofNullable(message);
    }

    private static Optional<String> checkCVV(String cvv) {
        LOGGER.info("Card cvv validation method has been started");
        String message = null;

        if (StringUtils.isNullOrEmpty(cvv)) {
            message = MessageManager.INSTANCE.getMessage(MessageConstant.EMPTY_FIELD)
                    + ": " + MessageManager.INSTANCE.getMessage("validation.cvv");
        } else if (cvv.length() < 3 || cvv.length() > 4) {
            message = MessageManager.INSTANCE.getMessage(MessageConstant.WRONG_CVV);
        }

        return Optional.ofNullable(message);
    }

}
