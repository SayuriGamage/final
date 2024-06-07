package lk.ijse.email;

import javax.mail.*;
import javax.mail.internet.*;
import java.net.PasswordAuthentication;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class GmailSender {

    static List<String> ids;
    static String employeegmail;

    public static void setData(List<String> idlist, String gmail) {
        ids = idlist;
        employeegmail = gmail;
        sendEmail();
    }

    public static void sendEmail() {
        String host = "smtp.gmail.com";
        final String user = "sayuriyashodhara@gmail.com";
        final String password = "lnkignxgliujozle";


        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");


        javax.mail.Session session = javax.mail.Session.getInstance(props, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(user, password);
            }
        });

        try {

            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(user));

            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(employeegmail));

            message.setSubject("ID List");

            StringBuilder content = new StringBuilder("Show your today warranty end equipments:\n");
            for (String id : ids) {
                content.append(id).append("\n");
            }
            content.append("\nPlease ensure to review the warranty terms and conditions, and consider scheduling any necessary maintenance or repairs before the warranty expires. If you have any questions or need assistance, feel free to contact our support team.\n\n");
            content.append("Thank you for choosing our services. We value your business and look forward to continuing to serve you.\n\n");
            content.append("Best regards,\n");
            content.append("Kalubowila S&M\n");
            content.append("Customer Support Team\n");
            content.append("Phone: (011) 456-7890\n");
            content.append("Email: support@kalubowila.com\n");
            message.setText(content.toString());

            Transport.send(message);

            System.out.println("Message sent successfully");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }


}