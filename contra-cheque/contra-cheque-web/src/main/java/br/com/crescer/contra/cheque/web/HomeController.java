/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.contra.cheque.web;

import br.com.crescer.contra.cheque.entity.Acesso;
import br.com.crescer.contra.cheque.entity.Colaborador;
import br.com.crescer.contra.cheque.entity.Log;
import br.com.crescer.contra.cheque.service.UsuarioService;
import br.com.crescer.contra.cheque.entity.Usuario;
import br.com.crescer.contra.cheque.entity.enumeration.TipoOperacaoLog;
import br.com.crescer.contra.cheque.service.AcessoService;
import br.com.crescer.contra.cheque.service.LogService;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

    @Autowired
    AcessoService acessoService;

    @Secured({"ROLE_USER"})
    @RequestMapping("/home")
    String home() {
        registrarAcesso();
        return "home";
    }

    private void registrarAcesso() {
        Colaborador colaboradorLogado = usuarioLogado().getColaborador();
        Calendar c = new GregorianCalendar();
        c.setTime(new Date());
        String nome = "";
        int dia = c.get(Calendar.DAY_OF_WEEK);
        switch (dia) {
            case Calendar.SUNDAY:
                nome = "domingo";
                break;
            case Calendar.MONDAY:
                nome = "segunda-feira";
                break;
            case Calendar.TUESDAY:
                nome = "terça-feira";
                break;
            case Calendar.WEDNESDAY:
                nome = "quarta-feira";
                break;
            case Calendar.THURSDAY:
                nome = "quinta-feira";
                break;
            case Calendar.FRIDAY:
                nome = "sexta-feira";
                break;
            case Calendar.SATURDAY:
                nome = "sábado";
                break;
        }
        int hora = c.get(Calendar.HOUR_OF_DAY);
        Acesso acessoAtual = acessoService.findByIdColaboradorAndDiaSemanaAndHora(colaboradorLogado, nome, hora);
        if (acessoAtual == null) {
            acessoAtual = new Acesso();
            acessoAtual.setDiaSemana(nome);
            acessoAtual.setHora(hora);
            acessoAtual.setIdColaborador(colaboradorLogado);
            acessoAtual.setQtdAcessos(1);
        } else {
            acessoAtual.setQtdAcessos(acessoAtual.getQtdAcessos()+1);
        }
        acessoService.save(acessoAtual);
        registrarOperacao(colaboradorLogado, TipoOperacaoLog.ACESSO, null);
    }

    private void registrarOperacao(Colaborador colaboradorLogado, TipoOperacaoLog tipoOperacao, Date dataConsultada) {
        String ipLogado = pegarIpLogado();
        Log log = new Log();
        log.setDataHora(new Date());
        log.setIdColaborador(colaboradorLogado);
        log.setIp(ipLogado);
        log.setTipoOperacao(tipoOperacao);
        log.setDataConsultaCc(dataConsultada);
        if (dataConsultada != null && tipoOperacao != TipoOperacaoLog.CONSULTA_CC) {
            log.setDataConsultaCc(null);
        }
        logService.save(log);
        verificarAcessoSuspeito(colaboradorLogado, ipLogado);
    }

    private void verificarAcessoSuspeito(Colaborador colaboradorLogado, String ipLogado) {
        int registros = logService.findByIdColaboradorAndTipoOperacao(colaboradorLogado, TipoOperacaoLog.ACESSO).size();
        int registrosPorIp = logService.findByIdColaboradorAndTipoOperacaoAndIp(colaboradorLogado, TipoOperacaoLog.ACESSO, ipLogado).size();
        if (registros >= 10 && (ipLogado.equals("unknown") || registrosPorIp == 1)) {
            adicionarInvalidez();
        }
    }

    private void adicionarInvalidez() {
        Usuario usuarioLogado = usuarioLogado();
        int loginsSuspeitos = usuarioLogado.getLoginsSuspeitos();
        usuarioLogado.setLoginsSuspeitos(loginsSuspeitos + 1);
        usuarioService.save(usuarioLogado);
        if (loginsSuspeitos == 1) {
            //méotodo de enviar e-mail
        }
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
