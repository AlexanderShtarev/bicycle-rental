package com.epam.jwd.controller.command.impl;

import com.epam.jwd.controller.PageConstant;
import com.epam.jwd.controller.RequestConstant;
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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class DeleteCardCommand implements Command {
    private final UserService userService;

    public DeleteCardCommand() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        this.userService = serviceFactory.getUserService();
    }

    @Override
    public ViewResolver resolve(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException, IOException, ServletException {

        ViewResolver resolver = new ViewResolver(PageConstant.PROFILE_PAGE);

        if (request.getParameter(RequestConstant.CARD_ID) != null) {
            int cardId = Integer.parseInt(request.getParameter(RequestConstant.CARD_ID));
            User user = (User) request.getSession().getAttribute(RequestConstant.USER);

            userService.removeUserCard(user.getId(), cardId);
            List<CreditCard> cards = userService.getUserCreditCards(user.getId());
            user.setCards(cards);

            request.getSession().setAttribute(RequestConstant.USER, user);
            resolver.setResolveAction(ResolveAction.REDIRECT);
        } else {
            request.setAttribute(RequestConstant.ERROR,
                    MessageManager.INSTANCE.getMessage(MessageConstant.SOMETHING_IS_WRONG));
        }

        return resolver;
    }

}
