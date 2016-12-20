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
public class EmailTest {
 
	@SuppressWarnings("resource")
	public static void main(String args[]) {
 
		// Spring Bean file you specified in /src/main/resources folder
		String crunchifyConfFile = "email-bean.xml";
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(crunchifyConfFile);
 
		// @Service("crunchifyEmail") <-- same annotation you specified in CrunchifyEmailAPI.java
		EmailService email = (EmailService) context.getBean("enviarEmail");
		String toAddr = "otavio.bubans@cwi.com.br";
		//String fromAddr = "otaviobubans@gmail.com";
 
		// email subject
		String subject = "Teste Email";
 
		// email body
		String body = "Conteudo Teste do email";
                
                Email novoemail = new Email(toAddr,"123.1667.123","otavio");
                
		email.enviarEmail(novoemail);
	}
}
