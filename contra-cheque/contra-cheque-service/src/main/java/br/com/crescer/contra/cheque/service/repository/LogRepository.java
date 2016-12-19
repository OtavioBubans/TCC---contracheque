/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.contra.cheque.service.repository;

import br.com.crescer.contra.cheque.entity.Colaborador;
import br.com.crescer.contra.cheque.entity.Log;
import br.com.crescer.contra.cheque.entity.enumeration.TipoOperacaoLog;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author otavio.bubans
 */
public interface LogRepository extends CrudRepository<Log, Long> {

    List<Log> findByIdColaboradorAndTipoOperacaoAndIp(Colaborador colaborador, TipoOperacaoLog tipoOperacao, String id);
    List<Log> findByIdColaboradorAndTipoOperacao(Colaborador colaborador, TipoOperacaoLog tipoOperacao);
}
