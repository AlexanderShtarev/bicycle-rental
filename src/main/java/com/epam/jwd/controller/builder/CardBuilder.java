package com.epam.jwd.controller.builder;

import com.epam.jwd.controller.RequestConstant;
import com.epam.jwd.domain.CreditCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CardBuilder {
    private static final Logger LOGGER = LoggerFactory.getLogger(CardBuilder.class);

    public static CreditCard buildCardFromRequest(HttpServletRequest request) throws ParseException {

        LOGGER.info("Getting credit card from request");

        String number = request.getParameter(RequestConstant.NUMBER);
        String owner = request.getParameter(RequestConstant.OWNER);
        String CVV = request.getParameter(RequestConstant.CVV);
        String month = request.getParameter(RequestConstant.MONTH);
        String year = request.getParameter(RequestConstant.YEAR);

        String dateString = year.trim() + "-" + month.trim();
        Date date = new SimpleDateFormat("yy-MM").parse(dateString);

        return new CreditCard(owner, number, CVV, date);
    }

}
