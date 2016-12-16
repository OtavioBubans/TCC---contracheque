/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.contra.cheque.infrastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author mateus.teixeira
 */
public class ServicoDeImportacaoDeArquivo {

    public void importarArquivo(String caminho) throws IOException {
        Path arquivo = Paths.get(System.getProperty("user.home"), caminho);

//        Stream<String> linhas = Files.lines(arquivo);
//        List<String> list = linhas.lines().collect(Collectors.toList());
//        for(String linha : linhas.toArray()){
//        
//        }
//        List<String> list = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(arquivo)) {
            List<String> lista = br.lines().collect(Collectors.toList());
            for (String linha : lista) {
                String id = linha.substring(0, 5).trim();
                String nome = linha.substring(6, 56).trim();
                String tipoSalario = linha.substring(57, 58).trim();
                String codigo = linha.substring(59, 62).trim();
                String valorParametro = linha.substring(63, 77).trim();
                String valorBase = linha.substring(78, 92).trim();
                String total = linha.substring(93, 107).trim();
                String tipo = linha.substring(108, 109).trim();
                String desc = linha.substring(110, 150).trim();
                String ocorr = linha.substring(151, 153).trim();
                String centroCusto = linha.substring(154, 194).trim();
                String dataAdm = linha.substring(195, 205).trim();
                String banco = linha.substring(206, 256).trim();
                String conta = linha.substring(257, 264).trim();

                System.out.format("%s %s %s %s %s %s %s %s %s %s %s %s %s %s \n", id, nome, tipoSalario, codigo, valorParametro, valorBase, total, tipo, desc, ocorr, centroCusto, dataAdm, banco, conta);
            }
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public static void main(String[] args) throws IOException {
        String caminho = "/Downloads/dados-importacao.txt";
        ServicoDeImportacaoDeArquivo auxiliar = new ServicoDeImportacaoDeArquivo();
        auxiliar.importarArquivo(caminho);
    }
}
