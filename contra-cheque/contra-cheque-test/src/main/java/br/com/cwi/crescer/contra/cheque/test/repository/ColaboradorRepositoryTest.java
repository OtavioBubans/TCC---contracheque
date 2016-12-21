/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cwi.crescer.contra.cheque.test.repository;

import br.com.crescer.contra.cheque.entity.Cargo;
import br.com.crescer.contra.cheque.entity.CentroCusto;
import br.com.crescer.contra.cheque.entity.Colaborador;
import br.com.crescer.contra.cheque.entity.Usuario;
import static org.junit.Assert.assertTrue;
import br.com.crescer.contra.cheque.service.repository.ColaboradorRepository;
import br.com.cwi.crescer.contra.cheque.test.TestRun;
import java.util.Date;
import javax.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author matha
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(TestRun.class)
@Transactional
public class ColaboradorRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    private Colaborador colaborador;

    @Before
    public void setBefore() {
        Usuario usuario = new Usuario(1l, "teste@teste.com", "senha", "admin", 0);
        Cargo cargo = new Cargo(1l, "Contador");
        CentroCusto centroCusto = new CentroCusto(1l, "Administracao");
        this.colaborador = new Colaborador(1l, "Teste", 'm', new Date(), new Date(), cargo, centroCusto, usuario);
        entityManager.persist(usuario);
        entityManager.persist(cargo);
        entityManager.persist(centroCusto);
        entityManager.persist(this.colaborador);
    }
    
    @Test
    public void testFindOneByIdComIdCadastrado(){
        final Colaborador colaborador = colaboradorRepository.findOne(1l);
        assertTrue(colaborador.equals(this.colaborador));
    }
    
    @Test
    public void testFindOneByIdSemIdCadastrado(){
        final Colaborador colaborador = colaboradorRepository.findOne(7l);
        assertTrue(colaborador == null);
    }
}
