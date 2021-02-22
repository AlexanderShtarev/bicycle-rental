package com.epam.jwd.controller.command.impl;

import com.epam.jwd.controller.PageConstant;
import com.epam.jwd.controller.RequestConstant;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.resolver.ViewResolver;
import com.epam.jwd.domain.User;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.service.ServiceFactory;
import com.epam.jwd.service.UserService;
import com.epam.jwd.util.MessageConstant;
import com.epam.jwd.util.MessageManager;
import com.mysql.cj.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

public class TopUpBalanceCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(TopUpBalanceCommand.class);
    private final UserService userService;

    public TopUpBalanceCommand() {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        this.userService = serviceFactory.getUserService();
    }

    @Override
    public ViewResolver resolve(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException, IOException, ServletException {

        LOGGER.debug("Top up balance command received");

        User user = (User) request.getSession().getAttribute(RequestConstant.USER);

        if (!StringUtils.isNullOrEmpty(request.getParameter(RequestConstant.AMOUNT))) {
            BigDecimal amount = new BigDecimal(request.getParameter(RequestConstant.AMOUNT));
            BigDecimal userBalance = user.getBalance();

            user.setBalance(amount.add(userBalance));
            userService.updateUser(user);

            request.getSession().setAttribute(RequestConstant.USER, user);
            request.getSession().setAttribute(RequestConstant.CARDS, user.getCards());
        } else {
            request.setAttribute(RequestConstant.ERROR,
                    MessageManager.INSTANCE.getMessage(MessageConstant.WRONG_AMOUNT));
        }

        return new ViewResolver(PageConstant.PROFILE_PAGE);
    }

}
