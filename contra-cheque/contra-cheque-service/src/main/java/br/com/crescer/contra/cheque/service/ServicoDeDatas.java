package br.com.crescer.contra.cheque.service;

import br.com.crescer.contra.cheque.service.exceptions.RegraDeNegocioException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author mateus.teixeira
 */
@Service
public class ServicoDeDatas {

    public Date DataSelecionada(String mes, Long ano) throws RegraDeNegocioException {
        Date dataAtual = new Date();
        int mesCadastrado = verificaMes(mes);
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

        try {
            if (mesCadastrado != 0) {
                dataAtual = formatoData.parse(String.format("01/%d/%d", mesCadastrado, ano));
            } else {
                throw new RegraDeNegocioException("Ocorreu um erro ao selecionar a data");
            }
        } catch (ParseException ex) {
        }

        return dataAtual;
    }

    public List<String> popularMeses() {
        List<String> meses = new ArrayList<>();

        meses.add("Janeiro");
        meses.add("Fevereiro");
        meses.add("Março");
        meses.add("Abril");
        meses.add("Maio");
        meses.add("Junho");
        meses.add("Julho");
        meses.add("Agosto");
        meses.add("Setembro");
        meses.add("Outubro");
        meses.add("Novembro");
        meses.add("Dezembro");

        return meses;
    }

    public List<Long> popularAnos(Long ano, Long quantidadeDeAnos) {
        List<Long> anos = new ArrayList<>();

        for (int i = 0; i < quantidadeDeAnos; i++) {
            Long proximosAnos = ano + i;
            anos.add(proximosAnos);
        }
        return anos;
    }

    public List<Long> popularAnosAdmin() {
        return popularAnos(2000l, 20l);
    }

    private int verificaMes(String mes) {
        int mesCadastrado = 0;
        switch (mes) {
            case "Janeiro":
                mesCadastrado = 1;
                break;
            case "Fevereiro":
                mesCadastrado = 2;
                break;
            case "Março":
                mesCadastrado = 3;
                break;
            case "Abril":
                mesCadastrado = 4;
                break;
            case "Maio":
                mesCadastrado = 5;
                break;
            case "Junho":
                mesCadastrado = 6;
                break;
            case "Julho":
                mesCadastrado = 7;
                break;
            case "Agosto":
                mesCadastrado = 8;
                break;
            case "Setembro":
                mesCadastrado = 9;
                break;
            case "Outubro":
                mesCadastrado = 10;
                break;
            case "Novembro":
                mesCadastrado = 11;
                break;
            case "Dezembro":
                mesCadastrado = 12;
                break;
        }
        return mesCadastrado;
    }
}
