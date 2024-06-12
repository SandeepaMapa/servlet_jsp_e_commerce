package com.example.services;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class EmailService {
    private static final Dotenv dotenv = Dotenv.load();
    private static final String API_KEY = dotenv.get("MAILJET_API");
    private static final String SECRET_KEY = dotenv.get("MAILJET_SECRET");
    public static void sendVerificationEmail(String email, String token) {
        final Dotenv dotenv = Dotenv.load();

        final String username = API_KEY;
        final String password = SECRET_KEY;

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "in-v3.mailjet.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props,
                new jakarta.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("gayansandeepam88@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Email Verification");
            message.setText("Dear User,"
                    + "\n\n Please verify your email by clicking the link below:"
                    + "\n http://localhost:8081/jsp_servlet_project_war/verify?token=" + token);

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}