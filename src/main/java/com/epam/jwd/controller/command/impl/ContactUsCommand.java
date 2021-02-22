package com.epam.jwd.controller.command.impl;

import com.epam.jwd.context.config.MailConfig;
import com.epam.jwd.controller.PageConstant;
import com.epam.jwd.controller.RequestConstant;
import com.epam.jwd.controller.command.Command;
import com.epam.jwd.controller.command.resolver.ViewResolver;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.util.MailSender;
import com.epam.jwd.util.MessageConstant;
import com.epam.jwd.util.MessageManager;
import com.epam.jwd.validator.UserValidator;
import com.epam.jwd.validator.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ContactUsCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContactUsCommand.class);
    private static final String SUBJECT = "`GoBIKE` SUPPORT, ";

    public ContactUsCommand() {
    }

    @Override
    public ViewResolver resolve(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException, IOException, ServletException {

        LOGGER.debug("Contact Us command received");

        ViewResolver resolver = new ViewResolver(PageConstant.CONTACT_US);

        String email = request.getParameter(RequestConstant.EMAIL);
        String message = request.getParameter(RequestConstant.MESSAGE);
        String name = request.getParameter(RequestConstant.NAME);

        List<String> errors = new ArrayList<>();
        Validator.checkString(message).ifPresent(errors::add);
        UserValidator.checkEmail(email).ifPresent(errors::add);

        if (errors.isEmpty()) {

            try {
                MailSender.getInstance().sendEmail(message, SUBJECT + name, MailConfig.getInstance().getUsername());
                request.setAttribute(RequestConstant.MESSAGE,
                        MessageManager.INSTANCE.getMessage(MessageConstant.MESSAGE_SENT));
                LOGGER.info("Message sent");
            } catch (MessagingException e) {
                LOGGER.warn("Message can't be sent: ", e);
                request.setAttribute(RequestConstant.ERROR,
                        MessageManager.INSTANCE.getMessage(MessageConstant.MESSAGE_NOT_SENT));
            }

        } else {
            LOGGER.info("Validation failed");
            request.setAttribute(RequestConstant.ERRORS, errors);
        }

        return resolver;
    }
}
