
package br.com.crescer.contra.cheque.service;

import br.com.crescer.contra.cheque.entity.Acesso;
import br.com.crescer.contra.cheque.entity.Colaborador;
import br.com.crescer.contra.cheque.service.repository.AcessoRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author otavio.bubans
 */
@Service
public class AcessoService {

    @Autowired
    AcessoRepository acessoRepository;

    public Acesso findByIdColaboradorAndDiaSemanaAndHora(Colaborador idColaborador, String diaSemana, int hora) {
        return acessoRepository.findByIdColaboradorAndDiaSemanaAndHora(idColaborador, diaSemana, hora);
    }

    public Acesso save(Acesso acesso) {
        return acessoRepository.save(acesso);
    }
}
