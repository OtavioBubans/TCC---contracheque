
package br.com.crescer.contra.cheque.repository;

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

    Long countByIdColaboradorAndTipoOperacaoAndIp(Colaborador colaborador, TipoOperacaoLog tipoOperacao, String id);
    Long countByIdColaboradorAndTipoOperacao(Colaborador colaborador, TipoOperacaoLog tipoOperacao);
}
