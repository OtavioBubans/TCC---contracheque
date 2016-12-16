/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.contra.cheque.web;

import br.com.crescer.contra.cheque.entity.Usuario;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author matha
 */
@Controller
public class AccessController {
    
    @RequestMapping("/login")
    String login(Usuario usuario) {
        return "login";
    }

    @RequestMapping("/logout")
    String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:login";
    }
}
