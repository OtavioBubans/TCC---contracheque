/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.contra.cheque.service;

import br.com.crescer.contra.cheque.entity.Lancamento;
import br.com.crescer.contra.cheque.service.repository.ColaboradorRepository;
import br.com.crescer.contra.cheque.service.repository.LancamentoRepository;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author otavio.bubans
 */
@Service
public class LancamentoService {
    
    @Autowired
    LancamentoRepository lancamentoRepository;
    @Autowired
    ColaboradorRepository colaboradorRepository;
    public List<Lancamento> importarArquivo(String caminho, Date dataLancamento) throws IOException {
        Path arquivo = Paths.get(System.getProperty("user.home"), caminho);
        Integer contador = 1;
        Lancamento lancamento = new Lancamento();
        List<Lancamento> listaLancamentos = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(arquivo)) {
            String linhaAtual = null;

            while ((linhaAtual = br.readLine()) != null) {
                if (linhaAtual.isEmpty()) {
                    System.out.println("Linha Vazia");
                } else {

                    String linha = linhaAtual.trim();
                    int linhaCount = linha.length();
                    String id = linha.substring(0, 5).trim();
                    String nome = linha.substring(5, 56).trim();
                    String tipoSalario = linha.substring(56, 58).trim();
                    String codigo = linha.substring(58, 63).trim();
                    String valorParametro = linha.substring(63, 77).trim();
                    String valorBase = linha.substring(77, 92).trim();
                    String total = linha.substring(92, 107).trim();
                    String tipo = linha.substring(107, 109).trim();
                    String desc = linha.substring(109, 150).trim();
                    String ocorr = linha.substring(150, 153).trim();
                    String centroCusto = linha.substring(153, 194).trim();
                    String dataAdm = linha.substring(194, 205).trim();
                    String banco = linha.substring(205, 256).trim();
                    String conta = linha.substring(256, 263).trim();
                    String aux = conta;
                    
                    lancamento.setBase(valorBase.isEmpty() ? 0.0 : Double.parseDouble(valorBase.replace(",", ".")));
                    lancamento.setTipo(tipo.charAt(0));
                    lancamento.setData(dataLancamento);
                    lancamento.setTotal(Double.parseDouble(total.replace(",", ".")));
                    lancamento.setValorParam(valorParametro.isEmpty() ? 0.0 : Double.parseDouble(valorParametro.replace(",", ".")));
                    lancamento.setDescricao(desc);
                    lancamento.setCodConta(codigo);

                    listaLancamentos.add(lancamento);
                    contador++;
                }
            }
        }
        return listaLancamentos;
    }

    public static void main(String[] args) throws IOException {
        LancamentoService aux = new LancamentoService();
        String caminho = "/Downloads/dados-importacao-contracheque-02.txt";
        Date dataLancamento = new Date();
        List<Lancamento> auxiliar = aux.importarArquivo(caminho, dataLancamento);
    }
}
