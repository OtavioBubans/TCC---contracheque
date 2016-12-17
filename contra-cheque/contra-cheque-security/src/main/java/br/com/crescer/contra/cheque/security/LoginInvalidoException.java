/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.contra.cheque.security;

import org.springframework.security.core.AuthenticationException;

/**
 *
 * @author matheus.schmitz
 */
public class LoginInvalidoException extends AuthenticationException {
        
    public LoginInvalidoException(String msg){
        super(msg);
    }
    
    public LoginInvalidoException(String msg, Throwable t) {
        super(msg, t);
    }
}
