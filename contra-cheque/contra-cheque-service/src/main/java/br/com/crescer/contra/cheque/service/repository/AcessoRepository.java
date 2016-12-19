/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.contra.cheque.service.repository;

import br.com.crescer.contra.cheque.entity.Acesso;
import br.com.crescer.contra.cheque.entity.Colaborador;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author otavio.bubans
 */
public interface AcessoRepository extends CrudRepository<Acesso, Long> {

    Acesso findByIdColaboradorAndDiaSemanaAndHora(Colaborador colaborador, String diaSemana, int hora);

}
