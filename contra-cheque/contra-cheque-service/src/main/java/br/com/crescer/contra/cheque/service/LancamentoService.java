package br.com.crescer.contra.cheque.service;

import br.com.crescer.contra.cheque.entity.Lancamento;
import br.com.crescer.contra.cheque.service.exceptions.RegraDeNegocioException;
import br.com.crescer.contra.cheque.service.repository.LancamentoRepository;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;
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
    ImportacaoDeArquivoService importacaoDeArquivoService;

    public void importarArquivo(Stream<String> arquivoAtual, Date dataLancamento) throws RegraDeNegocioException {
        List<Lancamento> lancamentos = importacaoDeArquivoService.importarArquivo(arquivoAtual, dataLancamento);
        List<Lancamento> lancamentosAntigos = lancamentoRepository.findByData(dataLancamento);

        if (!lancamentosAntigos.isEmpty()) {
            lancamentoRepository.delete(lancamentosAntigos);
        }

        lancamentoRepository.save(lancamentos);
    }

}
