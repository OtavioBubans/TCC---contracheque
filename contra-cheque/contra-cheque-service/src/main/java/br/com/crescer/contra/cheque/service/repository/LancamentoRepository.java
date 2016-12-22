/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.contra.cheque.service.repository;

import br.com.crescer.contra.cheque.entity.Lancamento;
import java.util.Date;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author otavio.bubans
 */
public interface LancamentoRepository extends CrudRepository<Lancamento,Long> {
    List<Lancamento> findByData(Date data);
    List<Lancamento> findByIdColaboradorAndDataAndTipo(Long idColaborador, Date data, Character tipo);
    List<Lancamento> findByIdColaboradorAndDataAndCodigo(Long idColaborador, Date data, String codConta);
}
