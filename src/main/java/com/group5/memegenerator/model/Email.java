package com.group5.memegenerator.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class Email implements IEmail {

    private static final String mailUserName = "dh409430@dal.ca";

    private static final String password = "DCdalhali@147";

    private static final String mailHost = "smtp.office365.com";

    private static final String mailPort = "587";

    private static final boolean mailSMTPAuth = true;

    private static final boolean mailStartTLS = true;

    @Setter
    @Getter
    private String mailSubject;

    @Setter
    @Getter
    private String mailContent;

    @Setter
    @Getter
    private String baseUrl;

    @Override
    public void setEmailConfig(String mailSubject, String mailContent) {

        this.mailSubject = mailSubject;
        this.mailContent = mailContent;
    }

    @Override
    public void sendEmail(String email, String mailSubject, String mailContent) {

        try {

            setEmailConfig(mailSubject, mailContent);

            Properties props = new Properties();

            props.put("mail.smtp.auth", mailSMTPAuth);
            props.put("mail.smtp.starttls.enable", mailStartTLS);
            props.put("mail.smtp.host", mailHost);
            props.put("mail.smtp.port", mailPort);

            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(mailUserName, password);
                }
            });

            try {
                MimeMessage message = new MimeMessage(session);
                message.setContent(mailContent, "text/html; charset=utf-8");
                message.setFrom(new InternetAddress(mailUserName));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
                message.setSubject("SUBJECT");

                Transport.send(message);

                Transport.send(message);

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }

        } catch (Exception ignored) {
        }
    }
}