/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.contra.cheque.service.repository;

import br.com.crescer.contra.cheque.TestRun;
import br.com.crescer.contra.cheque.entity.Cargo;
import br.com.crescer.contra.cheque.entity.CentroCusto;
import br.com.crescer.contra.cheque.entity.Colaborador;
import br.com.crescer.contra.cheque.entity.Lancamento;
import br.com.crescer.contra.cheque.entity.Usuario;
import java.util.Date;
import java.util.List;
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
public class LancamentoRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private LancamentoRepository lancamentoRepository;
    
    private Lancamento lancamento;
    private Date data;

    @Before
    public void setBefore() {
        Usuario usuario = new Usuario(1l, "teste@teste.com", "senha", "admin", 0);
        Cargo cargo = new Cargo("Contador");
        CentroCusto centroCusto = new CentroCusto("Administracao");
        this.data = new Date();
        Colaborador colaborador = new Colaborador("Teste", 'm', data, data, cargo, centroCusto, usuario);
        this.lancamento = new Lancamento("total FGTS", data, 'd', "659", 2.5, 2.5, 2.5, colaborador);
        entityManager.persist(usuario);
        entityManager.persist(cargo);
        entityManager.persist(centroCusto);
        entityManager.persist(colaborador);
        entityManager.persist(this.lancamento);
    }
    
    @Test
    public void testFindByDataCadastrado(){
        final List<Lancamento> lancamentos = lancamentoRepository.findByData(data);
        assertEquals(1, lancamentos.size());
        assertTrue(lancamentos.get(0).equals(this.lancamento));
    }
    
    @Test
    public void testFindByDataComDataNaoRegistrada(){
        final List<Lancamento> lancamento = lancamentoRepository.findByData(data);
        assertTrue(lancamento == null);
    }
}
