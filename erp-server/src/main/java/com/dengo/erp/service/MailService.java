package com.dengo.erp.service;

import com.dengo.erp.config.properties.MailProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * MailService
 * This service connects to mail and sends messages to users
 *
 * @author Andrii Blyznuk
 */
@Configuration
@EnableConfigurationProperties(MailProperties.class)
public class MailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailService.class);

    @Autowired
    MailProperties mailProperties;

    //This method is executed to send letters to users by mail
    public void sendMessageToEmail(String receiverEmail, String subject, String text) throws MessagingException {

        LOGGER.debug("Sending message to email " + receiverEmail + ", test: " + text);

        final String username = mailProperties.getUserName();
        final String password = mailProperties.getPassword();

        Properties props = new Properties();
        props.put("mail.transport.protocol", mailProperties.getProtocol());
        props.put("mail.smtp.auth", mailProperties.getAuth());
        props.put("mail.smtp.starttls.enable", mailProperties.getStarttlsEnable());
        props.put("mail.smtps.ssl.checkserveridentity", mailProperties.getCheckServerIdentity());
        props.put("mail.smtps.ssl.trust", mailProperties.getTrust());
        props.put("mail.smtp.starttls.required", mailProperties.getStarttlsRequired());
        props.put("mail.smtp.host", mailProperties.getHost());
        props.put("mail.smtp.port", mailProperties.getPort());
        props.put("mail.debug", mailProperties.getDebug());

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiverEmail));
        message.setSubject(subject);
        message.setContent(text, "text/html");

        Transport.send(message);


    }


}
