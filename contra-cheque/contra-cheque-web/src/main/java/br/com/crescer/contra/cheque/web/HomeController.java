/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.contra.cheque.web;

import br.com.crescer.contra.cheque.service.UsuarioService;
import br.com.crescer.contra.cheque.entity.Usuario;
import br.com.crescer.contra.cheque.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author matha
 */
@Controller
public class HomeController {

    @Autowired
    UsuarioService usuarioService;    
    
    @Autowired
    LogService logService;  
    
    @Secured({"ROLE_USER"})
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
    
    private Usuario usuarioLogado(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return usuarioService.findByEmail(username);
    }
}
