/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.contra.cheque.security;

import br.com.crescer.contra.cheque.entity.Usuario;
import br.com.crescer.contra.cheque.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author matheus.schmitz
 */
@Service
public class CCDetailsService implements UserDetailsService {

    @Autowired
    UsuarioService usuarioService;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.isEmpty()) {
            throw new UsernameNotFoundException(String.format("User with username=%s was not found", username));
        }
        Usuario usuarioEncontrado = usuarioService.findByEmail(username);
        ContraChequeRoles role;
        if(usuarioEncontrado.getRole().equals("admin")){
            role = ContraChequeRoles.ROLE_ADMIN;
        }
        return new User(username, usuarioEncontrado.getSenha(), ContraChequeRoles.valuesToList());

    }
}
