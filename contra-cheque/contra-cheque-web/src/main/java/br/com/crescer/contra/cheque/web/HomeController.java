/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.contra.cheque.web;

import br.com.crescer.contra.cheque.entity.Acesso;
import br.com.crescer.contra.cheque.entity.Colaborador;
import br.com.crescer.contra.cheque.entity.Email;
import br.com.crescer.contra.cheque.entity.Log;
import br.com.crescer.contra.cheque.service.UsuarioService;
import br.com.crescer.contra.cheque.entity.Usuario;
import br.com.crescer.contra.cheque.entity.enumeration.TipoOperacaoLog;
import br.com.crescer.contra.cheque.service.AcessoService;
import br.com.crescer.contra.cheque.service.EmailService;
import br.com.crescer.contra.cheque.service.LogService;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    EmailService emailService;
    
    @Autowired
    HttpServletRequest request;

    @Secured({"ROLE_USER"})
    @RequestMapping("/home")
    String home() {
        registrarAcesso();
        return "home";
    }

//      Teste para enviar email    
//    @RequestMapping("/email")
//    String envioEmail(){
//        Email email= new Email("otaviobubans@hotmail.com","12345","1233");
//        emailService.enviarEmail(email);
//        return("home");
//    }
    private void registrarAcesso() {
        Colaborador colaboradorLogado = usuarioLogado().getColaborador();
        Calendar c = new GregorianCalendar();
        c.setTime(new Date());
        List<String> diasSemana = Arrays.asList("domingo", "segunda-feira", "terca-feira", "quarta-feira", "quinta-feira", "sexta-feira", "sabado");
        int dia = c.get(Calendar.DAY_OF_WEEK);
        String diaSemana = diasSemana.get(dia);
        int hora = c.get(Calendar.HOUR_OF_DAY);
        Acesso acessoAtual = acessoService.findByIdColaboradorAndDiaSemanaAndHora(colaboradorLogado, diaSemana, hora);
        if (acessoAtual == null) {
            acessoAtual = new Acesso();
            acessoAtual.setDiaSemana(diaSemana);
            acessoAtual.setHora(hora);
            acessoAtual.setIdColaborador(colaboradorLogado);
            acessoAtual.setQtdAcessos(1);
        } else {
            acessoAtual.setQtdAcessos(acessoAtual.getQtdAcessos() + 1);
        }
        acessoService.save(acessoAtual);
        registrarOperacao(colaboradorLogado, TipoOperacaoLog.ACESSO, null);
        verificarIpSuspeito(colaboradorLogado);
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
    }

    private void verificarIpSuspeito(Colaborador colaboradorLogado) {
        String ipLogado = pegarIpLogado();
        Long registros = logService.findByIdColaboradorAndTipoOperacao(colaboradorLogado, TipoOperacaoLog.ACESSO);
        Long registrosPorIp = logService.findByIdColaboradorAndTipoOperacaoAndIp(colaboradorLogado, TipoOperacaoLog.ACESSO, ipLogado);
        if (registros >= 10 && (ipLogado.equals("unknown") || registrosPorIp == 1)) {
            adicionarInvalidez(ipLogado);
        }
        verificarAcessoSuspeito(colaboradorLogado, ipLogado);
    }

    private void verificarAcessoSuspeito(Colaborador colaboradorLogado, String ipLogado) {
        List<Acesso> acessos = colaboradorLogado.getAcessos();
        Integer totalAcessos = 0;
        for (Acesso acesso : acessos) {
            totalAcessos += acesso.getQtdAcessos();
        }
        if (totalAcessos >= 10) {
            for (Acesso acesso : acessos) {
                int percentualAcesso = acesso.getQtdAcessos() / totalAcessos;
                if (percentualAcesso < 0.15) {
                    adicionarInvalidez(ipLogado);
                }
            };
        }

    }

    private void adicionarInvalidez(String ip) {
        Usuario usuarioLogado = usuarioLogado();
        int loginsSuspeitos = usuarioLogado.getLoginsSuspeitos();
        usuarioLogado.setLoginsSuspeitos(loginsSuspeitos + 1);
        usuarioService.save(usuarioLogado);
        if (loginsSuspeitos == 1) {

            Email novoEmail = new Email(usuarioLogado.getEmail(), ip, usuarioLogado().getColaborador().getNome());
            emailService.enviarEmail(novoEmail);
        }
    }

    private Usuario usuarioLogado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return usuarioService.findByEmail(username);
    }

    private String pegarIpLogado() {
        /*try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException ex) {
            return "unknown";
        }*/
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }
}
