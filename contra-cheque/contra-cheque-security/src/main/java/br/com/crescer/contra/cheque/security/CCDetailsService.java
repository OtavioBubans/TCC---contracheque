/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.contra.cheque.security;


import br.com.crescer.contra.cheque.entity.Usuario;
import br.com.crescer.contra.cheque.service.UsuarioService;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    public UserDetails loadUserByUsername(String username) throws AuthenticationException {
        if (username.isEmpty()) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        Usuario usuarioEncontrado = usuarioService.findByEmail(username);
        Collection<ContraChequeRoles> roles = new ArrayList();
        if(usuarioEncontrado == null){
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        if(!usuarioEncontrado.isLoginValido()){
            throw new LoginInvalidoException("autenticacao extra");
        }
        if(usuarioEncontrado.getFuncao().equals("admin")){
            roles = ContraChequeRoles.valuesToList();
        }else if(usuarioEncontrado.getFuncao().equals("user")){
            roles.add(ContraChequeRoles.ROLE_USER);
        }
        return new User(username, new BCryptPasswordEncoder().encode(usuarioEncontrado.getSenha()), roles);
    }
}
