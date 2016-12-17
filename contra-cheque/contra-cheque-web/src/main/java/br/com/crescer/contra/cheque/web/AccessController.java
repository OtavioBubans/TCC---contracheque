/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.contra.cheque.web;

import br.com.crescer.contra.cheque.entity.Email;
import br.com.crescer.contra.cheque.entity.Usuario;
import br.com.crescer.contra.cheque.service.EmailService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author matha
 */
@Controller
public class AccessController {
    
//    @Autowired
//    EmailService emailservice;
//    
//    @Autowired
//    Email email;
   
    @RequestMapping("/login")
    String login(Usuario usuario) {
//        emailservice.enviarEmail(new Email("Email Teste","Teste","otaviobubans@hotmail.com"));
        return "login";
    }

    @RequestMapping("/logout")
    String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:login";
    }
}
