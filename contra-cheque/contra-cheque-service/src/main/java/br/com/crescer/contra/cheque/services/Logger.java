/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.contra.cheque.services;

import br.com.crescer.contra.cheque.entity.Colaborador;
import br.com.crescer.contra.cheque.entity.Log;
import br.com.crescer.contra.cheque.entity.enumeration.TipoOperacaoLog;
import br.com.crescer.contra.cheque.service.LogService;
import java.util.Date;

/**
 *
 * @author matha
 */
public class Logger {
    
    private final LogService logService;
    
    public Logger(LogService logService){
        this.logService = logService;
    }
    
    public void registrarOparacao(Colaborador colaborador, TipoOperacaoLog tipoOperacao, String ip){
        this.registrarOparacao(colaborador, tipoOperacao, ip, null);
    }
    
    public void registrarOparacao(Colaborador colaborador, TipoOperacaoLog tipoOperacao, String ip, Date dataConsultada){
        Log log = new Log();
        log.setDataHora(new Date());
        log.setIdColaborador(colaborador);
        log.setIp(ip);
        log.setTipoOperacao(tipoOperacao);
        log.setDataConsultaCc(dataConsultada);
        if(dataConsultada != null && tipoOperacao != TipoOperacaoLog.CONSULTA_CC){
            log.setDataConsultaCc(null);
        }
        logService.save(log);
    }
}
