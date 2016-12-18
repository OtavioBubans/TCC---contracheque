/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.contra.cheque.infrastructure;

import br.com.crescer.contra.cheque.service.LogService;
import br.com.crescer.contra.cheque.service.UsuarioService;

/**
 *
 * @author matha
 */
public class MonitorDeIPs {
    
    private final LogService logService;
    
    private final UsuarioService usuarioService;
    
    public MonitorDeIPs(UsuarioService usuarioService, LogService logService){
        this.logService = logService;
        this.usuarioService = usuarioService;
    }
}
