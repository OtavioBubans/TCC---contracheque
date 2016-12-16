/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.contra.cheque.web;

import javax.servlet.http.HttpSession;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author matha
 */
@Controller
public class HomeController {
    @RequestMapping("/home")
    String home() {
        return "home";
    }

    //exemplo de local onde apenas ADMIN tem acesso
    /*@Secured({"ROLE_ADMIN"})
    @RequestMapping("/teste")
    String logout() {
        return "teste"; <- essa view é uma view que eu criei para testar e que não existe no projeto mais
    }*/
}
