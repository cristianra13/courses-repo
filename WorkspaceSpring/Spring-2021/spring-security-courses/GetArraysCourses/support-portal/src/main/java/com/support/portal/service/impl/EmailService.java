package com.support.portal.service.impl;

import com.sun.mail.smtp.SMTPTransport;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

import static com.support.portal.constant.EmailConstant.*;
import static javax.mail.Message.RecipientType.*;

@Service
public class EmailService {

    /**
     * Método encargado del envío del emial con el nuevo password
     * @param firstName
     * @param password
     * @param email
     * @throws MessagingException
     */
    public void sendNewPasswordEmail(String firstName, String password, String email) throws MessagingException {
        Message message = createEmail(firstName, password, email);
        SMTPTransport smtpTransport =
                (SMTPTransport) getEmailSession().getTransport(SIMPLE_MAIL_TRANSFER_PROTOCOL);
        smtpTransport.connect(GMAIL_SMTP_SERVER, USERNAME, PASSWORD);
        smtpTransport.sendMessage(message, message.getAllRecipients());
        smtpTransport.close();
    }

    /**
     * Método encargado dela creación del email
     *
     * @param firstName
     * @param password
     * @param email
     * @return
     * @throws MessagingException
     */
    private Message createEmail(String firstName, String password, String email) throws MessagingException {
        Message message = new MimeMessage(getEmailSession());
        message.setFrom(new InternetAddress(FROM_EMAIL));
        message.setRecipients(TO, InternetAddress.parse(email, false));
        message.setRecipients(CC, InternetAddress.parse(CC_EMAIL, false));
        message.setSubject(EMAIL_SUBJECT);
        message.setText("Hello " + firstName + ", \n\n Your new account password is: " + password + "\n\nThe Support Team.");
        message.setSentDate(new Date());
        message.saveChanges();

        return message;
    }

    /**
     * Método encargado de seteo de propiedades para email
     *
     * @return
     */
    private Session getEmailSession() {
        Properties properties = System.getProperties();
        properties.put(SMTP_HOST, GMAIL_SMTP_SERVER);
        properties.put(SMTP_AUTH, true);
        properties.put(SMTP_PORT, DEFAULT_PORT);
        properties.put(SMTP_STARTTLS_ENABLE, true);
        properties.put(SMTP_HOST, GMAIL_SMTP_SERVER);
        properties.put(SMTP_STARTTLS_REQUIRED, true);
        return Session.getDefaultInstance(properties, null);
    }
}
