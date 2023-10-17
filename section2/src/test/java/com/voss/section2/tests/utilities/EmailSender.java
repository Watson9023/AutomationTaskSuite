package com.voss.section2.tests.utilities;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {
    public static void sendEmailNotification() {
        final String username = "testerwatty0@gmail.com"; // Gmail email address
        final char[] passwordChars = "owxc nhvh cscn glno\n".toCharArray();
        String password = new String(passwordChars);

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        //props.put("mail.smtp.ssl.ciphersuites", "TLS_RSA_WITH_AES_128_CBC_SHA");


        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        session.setDebug(true); // Add this line for debugging

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("testerwatty0@gmail.com")); // Recipient's email address
            message.setSubject("Manual Intervention Required");
            message.setText("Dear User,\n\nPlease complete the CAPTCHA manually. Once done, you can resume the automation process.");

            Transport.send(message);

            System.out.println("Email sent successfully.");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        sendEmailNotification();
    }
}