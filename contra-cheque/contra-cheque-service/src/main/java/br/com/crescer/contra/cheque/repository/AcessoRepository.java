
package br.com.crescer.contra.cheque.repository;

import br.com.crescer.contra.cheque.entity.Acesso;
import br.com.crescer.contra.cheque.entity.Colaborador;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author otavio.bubans
 */
public interface AcessoRepository extends CrudRepository<Acesso, Long> {

    Acesso findByIdColaboradorAndDiaSemanaAndHora(Colaborador idColaborador, String diaSemana, int hora);
        
}
