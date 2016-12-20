/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.contra.cheque.service;

import br.com.crescer.contra.cheque.service.exceptions.RegraDeNegocioException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author mateus.teixeira
 */
interface ImportacaoDeArquivo<T> {

    List<T> importarArquivo(String s, Date d) throws RegraDeNegocioException;
}
