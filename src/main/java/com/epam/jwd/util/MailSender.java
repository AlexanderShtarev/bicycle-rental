package com.epam.jwd.util;

import com.epam.jwd.context.config.MailConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class MailSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(MailSender.class);
    private static MailSender instance;

    public static MailSender getInstance() {
        if (instance == null) {
            instance = new MailSender();
        }
        return instance;
    }

    public void sendEmail(String body, String subject, String recipient) throws MessagingException {
        LOGGER.trace("Sending message...");
        MailConfig mailConfig = MailConfig.getInstance();

        final String from = mailConfig.getUsername();
        final String password = mailConfig.getPassword();
        final Properties props = mailConfig.getProps();

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from,password);
                    }
                });

        Message message = prepareMessage(session, from, recipient, subject, body);

        Transport transport = session.getTransport();
        transport.connect();
        Transport.send(message);
        transport.close();
        LOGGER.info("Message sent successfully");
    }

    private static Message prepareMessage(Session session, String from, String recipient, String subject, String body)
            throws MessagingException {

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        String[] emails = {recipient};
        InternetAddress dests[] = new InternetAddress[emails.length];

        for (int i = 0; i < emails.length; i++) {
            dests[i] = new InternetAddress(emails[i].trim().toLowerCase());
        }

        message.setRecipients(Message.RecipientType.TO, dests);
        message.setSubject(subject, "UTF-8");
        Multipart mp = new MimeMultipart();
        MimeBodyPart mbp = new MimeBodyPart();
        mbp.setContent(body, "text/html;charset=utf-8");
        mp.addBodyPart(mbp);
        message.setContent(mp);
        message.setSentDate(new java.util.Date());
        LOGGER.info("Mail message created");
        return message;
    }

}
