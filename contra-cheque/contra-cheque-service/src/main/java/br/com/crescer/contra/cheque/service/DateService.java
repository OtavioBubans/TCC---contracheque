package br.com.crescer.contra.cheque.service;

import br.com.crescer.contra.cheque.service.exceptions.RegraDeNegocioException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
/**
 *
 * @author mateus.teixeira
 */
@Service
public class DateService {

    public Date DataSelecionada(String mes, Long ano) throws RegraDeNegocioException {
        Date dataAtual = new Date();
        Long mesCadastrado = verificaMes(mes);
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
        return Arrays.asList("Janeiro","Fevereiro","Março","Abril","Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro");
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
        return popularAnos(2010l, 7l);
    }

    private Long verificaMes(String mes) {
        Long mesCadastrado = null;
        switch (mes) {
            case "Janeiro":
                mesCadastrado = 1l;
                break;
            case "Fevereiro":
                mesCadastrado = 2l;
                break;
            case "Março":
                mesCadastrado = 3l;
                break;
            case "Abril":
                mesCadastrado = 4l;
                break;
            case "Maio":
                mesCadastrado = 5l;
                break;
            case "Junho":
                mesCadastrado = 6l;
                break;
            case "Julho":
                mesCadastrado = 7l;
                break;
            case "Agosto":
                mesCadastrado = 8l;
                break;
            case "Setembro":
                mesCadastrado = 9l;
                break;
            case "Outubro":
                mesCadastrado = 10l;
                break;
            case "Novembro":
                mesCadastrado = 11l;
                break;
            case "Dezembro":
                mesCadastrado = 12l;
                break;
        }
        return mesCadastrado;
    }
}
