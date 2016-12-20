/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.contra.cheque.service;

import br.com.crescer.contra.cheque.entity.Email;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
 
/**
 *
 * @author Otávio
 */
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
 * @author otavio.bubans
 */
public class EmailTest {
    public static void main(String[] args) {
        
        
        EmailService emailService =new EmailService();
        
        Email email = new Email("otaviobubans@hotmail.com","123.4567","4324234");
        emailService.enviarEmail(email);
        
         
            
    }
}
