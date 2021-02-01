package com.epam.jwd.util;

import com.epam.jwd.context.MailProperties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class MailSenderUtil {

    public void sendEmail(String body, String subject, String recipient) throws MessagingException {
        MailProperties mailConfig = new MailProperties();
        Properties props = new Properties();

        props.put("mail.smtp.from", mailConfig.getUsername());
        props.put("mail.smtp.host", mailConfig.getHost());
        props.put("mail.smtp.port", mailConfig.getPort());
        props.put("mail.smtp.auth", mailConfig.getAuth());
        props.put("mail.smtp.socketFactory.port", mailConfig.getPort());
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.starttls.enable", "true");

        final String from = mailConfig.getUsername();
        final String password = mailConfig.getPassword();

        Session session = Session.getDefaultInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, password);
                    }
                });

        Message message = prepareMessage(session, from, recipient, subject, body);

        Transport transport = session.getTransport();
        transport.connect();
        Transport.send(message);
        transport.close();
        System.out.println("Message sent successfully");
    }

    private static Message prepareMessage(Session session,
                                          String from,
                                          String recipient,
                                          String subject,
                                          String body)
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
        return message;
    }

}
