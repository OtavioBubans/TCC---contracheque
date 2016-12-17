/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.contra.cheque.service;

import org.springframework.beans.factory.annotation.Autowired;
import br.com.crescer.contra.cheque.service.repository.LoggerRepository;

/**
 *
 * @author otavio.bubans
 */
public class LogService {
    
    @Autowired
    LoggerRepository logRepository;
    
}
