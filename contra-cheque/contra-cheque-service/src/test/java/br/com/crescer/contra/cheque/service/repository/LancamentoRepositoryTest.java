/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.contra.cheque.service.repository;

import br.com.crescer.contra.cheque.repository.LancamentoRepository;
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
        entityManager.persist(usuario);
        Cargo cargo = new Cargo("Contador");
        entityManager.persist(cargo);
        CentroCusto centroCusto = new CentroCusto("Administracao");
        entityManager.persist(centroCusto);
        this.data = new Date();
        Colaborador colaborador = new Colaborador(1l, "Teste", 'm', data, data, cargo, centroCusto, usuario);
        entityManager.persist(colaborador);
        this.lancamento = new Lancamento("total FGTS", data, 'd', "659", 2.5, 2.5, 2.5, colaborador);
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
        final List<Lancamento> lancamento = lancamentoRepository.findByData(new Date(1322018752992l));
        assertEquals(0, lancamento.size());
    }
}
