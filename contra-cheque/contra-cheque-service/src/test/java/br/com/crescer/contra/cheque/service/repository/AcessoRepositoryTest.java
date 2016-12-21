/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.contra.cheque.service.repository;

import br.com.crescer.contra.cheque.TestRun;
import br.com.crescer.contra.cheque.entity.Acesso;
import br.com.crescer.contra.cheque.entity.Cargo;
import br.com.crescer.contra.cheque.entity.CentroCusto;
import br.com.crescer.contra.cheque.entity.Colaborador;
import br.com.crescer.contra.cheque.entity.Usuario;
import java.util.Date;
import javax.persistence.EntityManager;
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
public class AcessoRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private AcessoRepository acessoRepository;

    private Colaborador colaborador;
    private Acesso acesso;
    
    @Before
    public void setBefore() {
        Usuario usuario = new Usuario(1l, "teste@teste.com", "senha", "admin", 0);
        entityManager.persist(usuario);
        Cargo cargo = new Cargo("pedreiro");
        entityManager.persist(cargo);
        CentroCusto centroCusto = new CentroCusto("Administracao");
        entityManager.persist(centroCusto);
        this.colaborador = new Colaborador("Teste", 'm', new Date(), new Date(), cargo, centroCusto, usuario);
        entityManager.persist(this.colaborador);
        this.acesso = new Acesso("segunda-feira", 11, 1, colaborador);
        entityManager.persist(this.acesso);
    }
    
    @Test
    public void testFindByIdColaboradorAndDiaSemanaAndHoraComRegistro() {
        final Acesso acesso = acessoRepository.findByIdColaboradorAndDiaSemanaAndHora(colaborador, "segunda-feira", 11);
        assertTrue(acesso.equals(this.acesso));
    }
    
    @Test
    public void testFindByIdColaboradorAndDiaSemanaAndHoraSemRegistro() {
        final Acesso acesso = acessoRepository.findByIdColaboradorAndDiaSemanaAndHora(colaborador, "terca-feira", 11);
        assertTrue(acesso == null);
    }
}
