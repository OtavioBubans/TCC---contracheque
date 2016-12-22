/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.contra.cheque.service;

import br.com.crescer.contra.cheque.entity.Colaborador;
import br.com.crescer.contra.cheque.service.repository.ColaboradorRepository;
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
public class ColaboradorServiceTest {

    @Mock
    private ColaboradorRepository colaboradorRepository;
    
    @InjectMocks
    private ColaboradorService colaboradorService;
    
    @Mock
    private Colaborador colaborador;
    
    @Before
    public void setUp() {
        when(colaboradorRepository.findOne(1l)).thenReturn(colaborador);
    }
    
    @Test
    public void testFindOne() {
        assertEquals(colaborador, colaboradorService.findById(1l));
        verify(colaboradorRepository).findOne(1l);
    }
}
