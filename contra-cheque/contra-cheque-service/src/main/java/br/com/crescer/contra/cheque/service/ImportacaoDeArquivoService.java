package br.com.crescer.contra.cheque.service;

import br.com.crescer.contra.cheque.entity.Colaborador;
import br.com.crescer.contra.cheque.entity.Lancamento;
import br.com.crescer.contra.cheque.service.exceptions.RegraDeNegocioException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author mateus.teixeira
 */
@Service
public class ImportacaoDeArquivoService {

    private static final Integer CARACTERES_POR_LINHA = 263;

    @Autowired
    ColaboradorService colaboradorService;

    private String gerarSubstring(String palavra, int pontoInicial, int pontoFinal) {
        return palavra.substring(pontoInicial, pontoFinal).trim();
    }

    private String susbstituirVirgulaPorPonto(String palavra) {
        return palavra.replace(",", ".");
    }

    private boolean contemCaracteresVazios(String palavra) {
        return palavra.contains(" ");
    }

    private RegraDeNegocioException adicionarMensagemParaOUsuario(String mensagem) {
        return new RegraDeNegocioException(mensagem);
    }

    public Stream<String> lerArquivo(String caminho) throws RegraDeNegocioException {

        Path path = Paths.get(caminho);
        Stream<String> retorno = null;
        try {
            retorno = Files.lines(path);
        } catch (IOException ex) {
            throw new RegraDeNegocioException("Ocorreu um problema na leitura do arquivo, verifique e tente novamente");
        }

        return retorno;
    }

    public List<Lancamento> importarArquivo(Stream<String> arquivo, Date dataLancamento) throws RegraDeNegocioException {
        List<Lancamento> listaLancamentos = new ArrayList<>();
        Long idAtual = null;
        Integer contador = 1;
        List<String> linhas = arquivo.filter(linha -> !linha.isEmpty()).collect(Collectors.toList());

        for (String linhaAtual : linhas) {

            Lancamento lancamento = new Lancamento();
            Colaborador colaborador = new Colaborador();

            String linha = linhaAtual.trim();

            if (linha.length() != CARACTERES_POR_LINHA) {
                throw new RegraDeNegocioException("Quantidade de caracteres inválida. Linha: " + contador);
            }

            String idColaborador = gerarSubstring(linha, 0, 5);
            Long idColaboradorAtual = Long.parseLong(idColaborador);

            String nomeColaborador = gerarSubstring(linha, 5, 56);
            String tipoSalario = gerarSubstring(linha, 56, 58);
            String codigo = gerarSubstring(linha, 58, 63);
            String valorParametro = gerarSubstring(linha, 63, 77);
            String valorBase = gerarSubstring(linha, 77, 92);
            String total = gerarSubstring(linha, 92, 107);
            String tipo = gerarSubstring(linha, 107, 109);
            String desc = gerarSubstring(linha, 109, 150);
            String ocorrencia = gerarSubstring(linha, 150, 153);
            String centroCusto = gerarSubstring(linha, 153, 194);
            String dataAdmissao = gerarSubstring(linha, 194, 205);
            String banco = gerarSubstring(linha, 205, 256);
            String contaColaborador = gerarSubstring(linha, 256, 263);
            if (idColaboradorAtual != idAtual) {
                colaborador = colaboradorService.findById(idColaboradorAtual);
                if (colaborador == null) {
                    throw adicionarMensagemParaOUsuario(String.format("ID de Colaborador não cadastrastrado no sistema. Linha: %d", contador));
                }

                idAtual = idColaboradorAtual;
            }
            if (!colaborador.getNome().equals(nomeColaborador)) {
                throw adicionarMensagemParaOUsuario(String.format("Colaborador de ID %d com nome diferente ao cadastrado no sistema. Linha %d", idColaboradorAtual, contador));
            }
//            if (!colaborador.getIdCentroCusto().getNome().equals(centroCusto)) {
//                throw adicionarMensagemParaOUsuario(String.format("Colaborador de ID %d com Centro Custo diferente ao cadastrado no sistema. Linha %d", idColaboradorAtual, contador));
//            }
//            if (colaborador.getConta() == null || !colaborador.getConta().getCodigo().equals(contaColaborador)) {
//                throw adicionarMensagemParaOUsuario(String.format("Colaborador de ID %d com Conta do Banco diferente ao cadastrado no sistema. Linha %d", idColaboradorAtual, contador));
//            }
            boolean campoVazio = ocorrencia.isEmpty() || banco.isEmpty() || tipoSalario.isEmpty() || dataAdmissao.isEmpty();
            if (campoVazio) {
                throw adicionarMensagemParaOUsuario(String.format("Algum dos campos está vazio, verifique o arquivo e tente novamente. Linha %d", contador));
            }
            boolean possuiCaracteresVazios = contemCaracteresVazios(valorBase) || contemCaracteresVazios(total) || contemCaracteresVazios(valorParametro);
            if (possuiCaracteresVazios) {
                throw adicionarMensagemParaOUsuario(String.format("Algum dos campos de valores está com problemas, verifique o arquivo e tente novamente. Linha %d", contador));
            }

            lancamento.setBase(valorBase.isEmpty() ? 0.0 : Double.parseDouble(susbstituirVirgulaPorPonto(valorBase)));
            lancamento.setTipo(tipo.charAt(0));
            lancamento.setData(dataLancamento);
            lancamento.setTotal(Double.parseDouble(susbstituirVirgulaPorPonto(total)));
            lancamento.setValorParam(valorParametro.isEmpty() ? 0.0 : Double.parseDouble(susbstituirVirgulaPorPonto(valorParametro)));
            lancamento.setDescricao(desc);
            lancamento.setCodConta(codigo);
            lancamento.setIdColaborador(colaborador);

            listaLancamentos.add(lancamento);

            contador++;
        }

        return listaLancamentos;
    }
}
