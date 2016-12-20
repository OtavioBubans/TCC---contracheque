/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cwi.crescer.contra.cheque.test;

import br.com.crescer.contra.cheque.entity.Acesso;
import br.com.crescer.contra.cheque.entity.Agencia;
import br.com.crescer.contra.cheque.entity.Banco;
import br.com.crescer.contra.cheque.entity.Cargo;
import br.com.crescer.contra.cheque.entity.CentroCusto;
import br.com.crescer.contra.cheque.entity.Colaborador;
import br.com.crescer.contra.cheque.entity.Conta;
import br.com.crescer.contra.cheque.entity.Lancamento;
import br.com.crescer.contra.cheque.entity.Usuario;
import java.util.Date;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author matheus.schmitz
 */
@SpringBootApplication
public class TestRun {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(TestRun.class, args);
    }
}
