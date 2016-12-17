/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.contra.cheque.service;

import br.com.crescer.contra.cheque.entity.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.crescer.contra.cheque.service.repository.LogRepository;

/**
 *
 * @author otavio.bubans
 */
@Service
public class LogService {
    
    @Autowired
    LogRepository logRepository;
    
    public Log save(Log log){
        return logRepository.save(log);
    }
}
