
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


/**
 *
 * @author Otávio
 */
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
                             protected PasswordAuthentication getPasswordAuthentication() 
                             {
                                   return new PasswordAuthentication("contracheque.cwi@gmail.com", "crescer16");
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
                  message.setText(email.getTexto());
                
                  Transport.send(message);
             } catch (MessagingException e) {
                  throw new RuntimeException(e);
            }
    
	} 

}
