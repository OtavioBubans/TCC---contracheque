/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.contra.cheque.service.repository;

import br.com.crescer.contra.cheque.TestRun;
import br.com.crescer.contra.cheque.entity.Usuario;
import javax.persistence.EntityManager;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author matha
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(TestRun.class)
@Transactional
public class UsuarioRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    private Usuario usuario;

    @Before
    public void setBefore() {
        this.usuario = new Usuario(1l, "teste@teste.com", "senha", "admin", 0);
        entityManager.persist(usuario);
    }
    
    @Test
    public void findByEmailComEmailRegistrado(){
        Usuario usuario = usuarioRepository.findByEmail("teste@teste.com");
        assertTrue(usuario.equals(this.usuario));
    }
    
    @Test
    public void findByEmailComEmailNaoRegistrado(){
        Usuario usuario = usuarioRepository.findByEmail("testando@teste.com");
        assertEquals(null, usuario);
    }
}
