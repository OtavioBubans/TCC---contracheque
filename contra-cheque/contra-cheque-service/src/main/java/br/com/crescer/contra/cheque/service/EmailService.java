package br.com.crescer.contra.cheque.service;

import br.com.crescer.contra.cheque.entity.Email;
import org.springframework.stereotype.Service;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    public void enviarEmail(Email email) {

        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                String email = System.getenv().get("EMAIL_LOGIN");
                String senha = System.getenv().get("EMAIL_SENHA");
                return new PasswordAuthentication(email, senha);
            }
        });

        session.setDebug(true);
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email.getEmailAdmin()));

            Address[] toUser = InternetAddress
                    .parse(email.getDestinatario());
            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject(email.getAssunto());
            message.setContent(email.getTexto(), "text/html; charset=utf-8");

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

}
