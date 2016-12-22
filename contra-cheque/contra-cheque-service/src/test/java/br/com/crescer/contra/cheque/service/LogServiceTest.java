/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.contra.cheque.service;

import br.com.crescer.contra.cheque.entity.Colaborador;
import br.com.crescer.contra.cheque.entity.Log;
import br.com.crescer.contra.cheque.entity.enumeration.TipoOperacaoLog;
import br.com.crescer.contra.cheque.service.repository.LogRepository;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author matha
 */
@RunWith(MockitoJUnitRunner.class)
public class LogServiceTest {
    
    @Mock
    private LogRepository logRepository;   
    
    @InjectMocks
    private LogService logService;
    
    @Mock
    private Log log;
    
    private Colaborador colaborador = new Colaborador();
    
    @Before
    public void setUp() {
        when(logRepository.countByIdColaboradorAndTipoOperacaoAndIp(colaborador, TipoOperacaoLog.ACESSO, "1.1.1.1")).thenReturn(1l);
        when(logRepository.countByIdColaboradorAndTipoOperacao(colaborador, TipoOperacaoLog.ACESSO)).thenReturn(1l);
        when(logRepository.save(log)).thenReturn(log);
    }
    @Test
    public void testSave(){
        assertEquals(log, logService.save(log));
        verify(logRepository).save(log);
    }
    
    @Test
    public void testFindByIdColaboradorAndTipoOperacaoAndIp(){
        assertEquals(1l, logService.findByIdColaboradorAndTipoOperacaoAndIp(colaborador, TipoOperacaoLog.ACESSO, "1.1.1.1").longValue());
        verify(logRepository).countByIdColaboradorAndTipoOperacaoAndIp(colaborador, TipoOperacaoLog.ACESSO, "1.1.1.1");
    }
    
    @Test
    public void testFindByIdColaboradorAndTipoOperacao(){
        assertEquals(1l, logService.findByIdColaboradorAndTipoOperacao(colaborador, TipoOperacaoLog.ACESSO).longValue());
        verify(logRepository).countByIdColaboradorAndTipoOperacao(colaborador, TipoOperacaoLog.ACESSO);
    }
}
