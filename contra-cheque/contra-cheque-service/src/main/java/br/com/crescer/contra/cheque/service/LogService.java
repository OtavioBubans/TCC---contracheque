/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.contra.cheque.service;

import br.com.crescer.contra.cheque.service.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author otavio.bubans
 */
public class LogService {
    
    @Autowired
    LogRepository logRepository;
    
}
