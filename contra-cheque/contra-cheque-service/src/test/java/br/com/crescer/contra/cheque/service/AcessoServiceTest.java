/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.contra.cheque.service;

import br.com.crescer.contra.cheque.entity.Acesso;
import br.com.crescer.contra.cheque.entity.Cargo;
import br.com.crescer.contra.cheque.entity.CentroCusto;
import br.com.crescer.contra.cheque.entity.Colaborador;
import br.com.crescer.contra.cheque.entity.Usuario;
import br.com.crescer.contra.cheque.repository.AcessoRepository;
import java.util.Date;
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
public class AcessoServiceTest {
    
    @Mock
    private AcessoRepository acessoRepository;
    
    @InjectMocks
    private AcessoService acessoService;
    
    @Mock
    private Acesso acesso;
    
    private final Colaborador colaborador = new Colaborador();
    
    @Before
    public void setUp() {
        when(acessoRepository.findByIdColaboradorAndDiaSemanaAndHora(colaborador, "segunda-feira", 11)).thenReturn(acesso);
        when(acessoRepository.save(acesso)).thenReturn(acesso);
    }
    
    @Test
    public void testSave() {
        assertEquals(acesso, acessoService.save(acesso));
        verify(acessoRepository).save(acesso);
    }
    
    @Test
    public void testFindByIdColaboradorAndDiaSemanaAndHora(){
        assertEquals(acesso, acessoService.findByIdColaboradorAndDiaSemanaAndHora(colaborador, "segunda-feira", 11));
        verify(acessoRepository).findByIdColaboradorAndDiaSemanaAndHora(colaborador, "segunda-feira", 11);
    }
}
