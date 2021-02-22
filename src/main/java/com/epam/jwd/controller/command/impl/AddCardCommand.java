package com.epam.jwd.controller.command.impl;

import com.epam.jwd.controller.PageConstant;
import com.epam.jwd.controller.RequestConstant;
import com.epam.jwd.controller.builder.CardBuilder;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.resolver.ResolveAction;
import com.epam.jwd.controller.command.resolver.ViewResolver;
import com.epam.jwd.domain.CreditCard;
import com.epam.jwd.domain.User;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.service.ServiceFactory;
import com.epam.jwd.service.UserService;
import com.epam.jwd.util.MessageConstant;
import com.epam.jwd.util.MessageManager;
import com.epam.jwd.validator.CardValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class AddCardCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddCardCommand.class);
    private final UserService userService;

    public AddCardCommand() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        this.userService = serviceFactory.getUserService();
    }

    @Override
    public ViewResolver resolve(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException, IOException, ServletException {

        LOGGER.debug("Add card command received");

        ViewResolver resolver = new ViewResolver(PageConstant.PROFILE_PAGE);

        try {
            CreditCard card = CardBuilder.buildCardFromRequest(request);
            List<String> errors = CardValidator.checkCard(card);

            if (errors.isEmpty()) {
                User user = (User) request.getSession().getAttribute(RequestConstant.USER);
                List<CreditCard> cards = userService.getUserCreditCards(user.getId());

                if (!cards.contains(card)) {
                    user.getCards().add(card);
                    userService.addUserCard(user.getId(), card);

                    request.getSession().setAttribute(RequestConstant.USER, user);
                    resolver.setResolveAction(ResolveAction.REDIRECT);
                } else {
                    request.setAttribute(RequestConstant.ERROR,
                            MessageManager.INSTANCE.getMessage(MessageConstant.CARD_ALREADY_EXIST));
                }

            } else {
                request.setAttribute(RequestConstant.ERRORS, errors);
            }

        } catch (ParseException e) {
            LOGGER.warn("Can't parse date: ", e);
            request.setAttribute(RequestConstant.ERROR,
                    MessageManager.INSTANCE.getMessage(MessageConstant.SOMETHING_IS_WRONG));
        }

        return resolver;
    }

}
