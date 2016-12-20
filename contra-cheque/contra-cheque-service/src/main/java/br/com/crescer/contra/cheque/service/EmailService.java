/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.contra.cheque.service;

import br.com.crescer.contra.cheque.entity.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;


/**
 *
 * @author Otávio
 */
@Service("enviarEmail")
public class EmailService {
 
	@Autowired
	private MailSender enviar;        
        
	public void enviarEmail(Email email) {
 
		SimpleMailMessage conteudo = new SimpleMailMessage();
		conteudo.setFrom(email.getDestinatario());
		conteudo.setTo(email.getDestinatario());
		conteudo.setSubject(email.getAssunto());
		conteudo.setText(email.getTexto());
		enviar.send(conteudo);
	} 

}
