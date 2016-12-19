/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.contra.cheque.service;

import br.com.crescer.contra.cheque.entity.Usuario;
import br.com.crescer.contra.cheque.service.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author matheus.schmitz
 */
@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario findByEmail(String email){
        return usuarioRepository.findByEmail(email);
    }
    
    public Usuario save(Usuario usuario){
        return usuarioRepository.save(usuario);
    }
}
