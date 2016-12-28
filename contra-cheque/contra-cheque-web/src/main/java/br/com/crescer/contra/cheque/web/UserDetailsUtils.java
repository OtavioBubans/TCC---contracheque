package br.com.crescer.contra.cheque.web;

import br.com.crescer.contra.cheque.entity.Usuario;
import br.com.crescer.contra.cheque.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsUtils {
    
    @Autowired
    UsuarioService usuarioService;
    
    public Usuario usuarioLogado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return usuarioService.findByEmail(username);
    }
}
