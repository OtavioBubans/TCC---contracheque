/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cwi.crescer.contra.cheque.test.repository;

import br.com.crescer.contra.cheque.entity.Acesso;
import br.com.crescer.contra.cheque.entity.Cargo;
import br.com.crescer.contra.cheque.entity.CentroCusto;
import br.com.crescer.contra.cheque.entity.Colaborador;
import br.com.crescer.contra.cheque.entity.Usuario;
import br.com.crescer.contra.cheque.service.repository.AcessoRepository;
import br.com.cwi.crescer.contra.cheque.test.TestRun;
import static org.junit.Assert.assertEquals;
import java.util.Date;
import javax.persistence.EntityManager;
import static org.junit.Assert.assertTrue;
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
public class AcessoRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private AcessoRepository acessoRepository;

    private Usuario usuario;
    private Cargo cargo;
    private CentroCusto centroCusto;
    private Colaborador colaborador;
    private Acesso acesso;
    
    @Before
    public void setBefore() {
        this.usuario = new Usuario(1l, "teste@teste.com", "senha", "admin", 0);
        this.cargo = new Cargo(1l, "Contador");
        this.centroCusto = new CentroCusto(1l, "Administracao");
        this.colaborador = new Colaborador(1l, "Teste", 'm', new Date(), new Date(), cargo, centroCusto, usuario);
        this.acesso = new Acesso(1l, "segunda-feira", 11, 1, colaborador);
        entityManager.persist(this.usuario);
        entityManager.persist(this.cargo);
        entityManager.persist(this.centroCusto);
        entityManager.persist(this.colaborador);
        entityManager.persist(this.acesso);
    }
    
    @Test
    public void testFindByIdColaboradorAndDiaSemanaAndHora() {
        final Acesso acesso = acessoRepository.findByIdColaboradorAndDiaSemanaAndHora(colaborador, "segunda-feira", 11);
        assertTrue(acesso.equals(this.acesso));
    }
}
