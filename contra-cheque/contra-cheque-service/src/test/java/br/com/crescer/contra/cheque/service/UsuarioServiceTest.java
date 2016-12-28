/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.contra.cheque.service;

import br.com.crescer.contra.cheque.entity.Usuario;
import br.com.crescer.contra.cheque.repository.UsuarioRepository;
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
public class UsuarioServiceTest {
    
    @Mock
    private UsuarioRepository usuarioRepository;
    
    @InjectMocks
    private UsuarioService usuarioService;
    
    @Mock
    private Usuario usuario;
    
    @Before
    public void setUp() {
        when(usuarioRepository.findByEmail("teste@teste.com")).thenReturn(usuario);
        when(usuarioRepository.save(usuario)).thenReturn(usuario);
    }
    
    @Test
    public void testSave() {
        assertEquals(usuario, usuarioService.save(usuario));
        verify(usuarioRepository).save(usuario);
    }
    
    @Test
    public void testFindByEmail() {
        assertEquals(usuario, usuarioService.findByEmail("teste@teste.com"));
        verify(usuarioRepository).findByEmail("teste@teste.com");
    }
}
