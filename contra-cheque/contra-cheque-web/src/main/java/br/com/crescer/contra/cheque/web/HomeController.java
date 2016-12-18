/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.contra.cheque.web;

import br.com.crescer.contra.cheque.entity.Log;
import br.com.crescer.contra.cheque.service.UsuarioService;
import br.com.crescer.contra.cheque.entity.Usuario;
import br.com.crescer.contra.cheque.entity.enumeration.TipoOperacaoLog;
import br.com.crescer.contra.cheque.service.LogService;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
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
        registrarOperacao(TipoOperacaoLog.ACESSO, null);
        return "home";
    }

    private void registrarOperacao(TipoOperacaoLog tipoOperacao, Date dataConsultada) {
        Log log = new Log();
        log.setDataHora(new Date());
        log.setIdColaborador(usuarioLogado().getColaborador());
        log.setIp(pegarIpLogado());
        log.setTipoOperacao(tipoOperacao);
        log.setDataConsultaCc(dataConsultada);
        if (dataConsultada != null && tipoOperacao != TipoOperacaoLog.CONSULTA_CC) {
            log.setDataConsultaCc(null);
        }
        logService.save(log);
    }

    private Usuario usuarioLogado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return usuarioService.findByEmail(username);
    }

    private String pegarIpLogado() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException ex) {
            return "unknown";
        }
    }
}
