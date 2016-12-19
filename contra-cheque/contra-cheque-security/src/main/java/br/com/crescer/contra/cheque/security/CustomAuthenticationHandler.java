/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.contra.cheque.security;

import java.io.IOException;
import java.security.Principal;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

/**
 *
 * @author matheus.schmitz
 */
public class CustomAuthenticationHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest hsr, HttpServletResponse hsr1, AuthenticationException ae) 
            throws IOException, ServletException {
        String msg = ae.getMessage();
        Principal p = hsr.getUserPrincipal();
        if (msg != null && msg.equals("autenticacao extra")) {
            hsr1.sendRedirect("/login/autenticar");
        } else {
            hsr.setAttribute("param", "error");
            hsr1.sendRedirect("/login?error");
        }
    }
}
