
package br.com.crescer.contra.cheque.service.repository;

import br.com.crescer.contra.cheque.entity.Colaborador;
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
    List<Lancamento> findByIdColaboradorAndDataAndTipo(Colaborador idColaborador, Date data, Character tipo);
    List<Lancamento> findByIdColaboradorAndDataAndCodConta(Colaborador idColaborador, Date data, String codConta);
    List<Lancamento> findByAndDataAndTipo(Date data, Character tipo);
    Long countByData(Date data);
}
