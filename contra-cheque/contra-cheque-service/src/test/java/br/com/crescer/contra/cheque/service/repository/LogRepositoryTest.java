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
import br.com.crescer.contra.cheque.entity.Log;
import br.com.crescer.contra.cheque.entity.Usuario;
import br.com.crescer.contra.cheque.entity.enumeration.TipoOperacaoLog;
import java.util.Date;
import javax.persistence.EntityManager;
import static org.junit.Assert.assertEquals;
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
public class LogRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private LogRepository logRepository;

    private Date data;
    private Colaborador colaborador;
    private Colaborador colaboradorSemRegistros;

    @Before
    public void setBefore() {
        Usuario usuario = new Usuario(1l, "teste@teste.com", "senha", "admin", 0);
        Usuario usuarioSemRegistros = new Usuario(2l, "teste@testando.com", "senha", "admin", 0);
        Cargo cargo = new Cargo("Contador");
        CentroCusto centroCusto = new CentroCusto("Administracao");
        this.data = new Date();
        this.colaborador = new Colaborador("Teste", 'm', data, data, cargo, centroCusto, usuario);
        this.colaboradorSemRegistros = new Colaborador("Teste", 'm', data, data, cargo, centroCusto, usuarioSemRegistros);
        Log log1 = new Log("19.19.19.19", data, TipoOperacaoLog.IMPORTACAO, null, colaborador);
        Log log2 = new Log("19.19.19.19", data, TipoOperacaoLog.ACESSO, null, colaborador);
        Log log3 = new Log("19.19.19.19", data, TipoOperacaoLog.CONSULTA_CC, data, colaborador);
        entityManager.persist(usuario);
        entityManager.persist(cargo);
        entityManager.persist(centroCusto);
        entityManager.persist(this.colaborador);
        entityManager.persist(this.colaboradorSemRegistros);
        entityManager.persist(log1);
        entityManager.persist(log2);
        entityManager.persist(log3);
    }
    
    @Test
    public void testCountByIdColaboradorAndImportacaoAndIpComDadosRegistrados(){
        Long quantidade = logRepository.countByIdColaboradorAndTipoOperacaoAndIp(colaborador, TipoOperacaoLog.IMPORTACAO, "19.19.19.19");
        assertEquals(quantidade.intValue(), 1);
    }
    
    @Test
    public void testCountByIdColaboradorAndAcessoAndIpComDadosRegistrados(){
        Long quantidade = logRepository.countByIdColaboradorAndTipoOperacaoAndIp(colaborador, TipoOperacaoLog.ACESSO, "19.19.19.19");
        assertEquals(quantidade.intValue(), 1);
    }
    
    @Test
    public void testCountByIdColaboradorAndConsultaCCAndIpComDadosRegistrados(){
        Long quantidade = logRepository.countByIdColaboradorAndTipoOperacaoAndIp(colaborador, TipoOperacaoLog.CONSULTA_CC, "19.19.19.19");
        assertEquals(quantidade.intValue(), 1);
    }
    
    @Test
    public void testCountByIdColaboradorAndImportacaoAndIpComDadosNaoRegistrados(){
        Long quantidade = logRepository.countByIdColaboradorAndTipoOperacaoAndIp(colaborador, TipoOperacaoLog.IMPORTACAO, "19.19.19.190");
        assertEquals(quantidade.intValue(), 0);
    }
    
    @Test
    public void testCountByIdColaboradorAndAcessoAndIpComDadosNaoRegistrados(){
        Long quantidade = logRepository.countByIdColaboradorAndTipoOperacaoAndIp(colaborador, TipoOperacaoLog.ACESSO, "19.19.19.190");
        assertEquals(quantidade.intValue(), 0);
    }
    
    @Test
    public void testCountByIdColaboradorAndConsultaCCAndIpComDadosNaoRegistrados(){
        Long quantidade = logRepository.countByIdColaboradorAndTipoOperacaoAndIp(colaborador, TipoOperacaoLog.CONSULTA_CC, "19.19.19.190");
        assertEquals(quantidade.intValue(), 0);
    }
    
    @Test
    public void testCountByIdColaboradorAndImportacaoComDadosRegistrados(){
        Long quantidade = logRepository.countByIdColaboradorAndTipoOperacao(colaborador, TipoOperacaoLog.IMPORTACAO);
        assertEquals(quantidade.intValue(), 1);
    }
    
    @Test
    public void testCountByIdColaboradorAndAcessoComDadosRegistrados(){
        Long quantidade = logRepository.countByIdColaboradorAndTipoOperacao(colaborador, TipoOperacaoLog.ACESSO);
        assertEquals(quantidade.intValue(), 1);
    }
    
    @Test
    public void testCountByIdColaboradorAndConsultaCCComDadosRegistrados(){
        Long quantidade = logRepository.countByIdColaboradorAndTipoOperacao(colaborador, TipoOperacaoLog.CONSULTA_CC);
        assertEquals(quantidade.intValue(), 1);
    }
    
    @Test
    public void testCountByIdColaboradorAndImportacaoComDadosNaoRegistrados(){
        Long quantidade = logRepository.countByIdColaboradorAndTipoOperacao(colaboradorSemRegistros, TipoOperacaoLog.IMPORTACAO);
        assertEquals(quantidade.intValue(), 0);
    }
    
    @Test
    public void testCountByIdColaboradorAndAcessoComDadosNaoRegistrados(){
        Long quantidade = logRepository.countByIdColaboradorAndTipoOperacao(colaboradorSemRegistros, TipoOperacaoLog.ACESSO);
        assertEquals(quantidade.intValue(), 0);
    }
    
    @Test
    public void testCountByIdColaboradorAndConsultaCCComDadosNaoRegistrados(){
        Long quantidade = logRepository.countByIdColaboradorAndTipoOperacao(colaboradorSemRegistros, TipoOperacaoLog.CONSULTA_CC);
        assertEquals(quantidade.intValue(), 0);
    }
}
