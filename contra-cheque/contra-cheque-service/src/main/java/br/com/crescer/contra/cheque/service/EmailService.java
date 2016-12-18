/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.contra.cheque.service;

import br.com.crescer.contra.cheque.entity.Email;
import br.com.crescer.contra.cheque.entity.EmailInterface;
//import org.springframework.mail.*;


/**
 *
 * @author Otávio
 */
public class EmailService implements EmailInterface {

    @Override
    public void enviarEmail(Email email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    private MailSender mailSender;
//    private SimpleMailMessage templateMessage;
//
//    public void setMailSender(MailSender mailSender) {
//        this.mailSender = mailSender;
//    }
//    public void setTemplateMessage(SimpleMailMessage templateMessage) {
//        this.templateMessage = templateMessage;
//    }
//    
//  
//    @Override
//     public void enviarEmail(Email email) {
//
//       
//        SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
//        msg.setTo(email.getDestinatario());
//        msg.setText(email.getTexto());
//        
//        try{
//            this.mailSender.send(msg);
//        }
//        catch (MailException ex) {
//            // simply log it and go on...
//            System.err.println(ex.getMessage());
//        }
//    }
//   

}
