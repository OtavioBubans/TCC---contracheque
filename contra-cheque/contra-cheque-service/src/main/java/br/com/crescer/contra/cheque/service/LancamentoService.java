package br.com.crescer.contra.cheque.service;

import br.com.crescer.contra.cheque.entity.Lancamento;
import br.com.crescer.contra.cheque.service.exceptions.RegraDeNegocioException;
import br.com.crescer.contra.cheque.service.repository.LancamentoRepository;
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

    private static final Integer CARACTERES_POR_LINHA = 263;

    @Autowired
    LancamentoRepository lancamentoRepository;
    @Autowired
    ImportacaoDeArquivo importacaoDeArquivo = new ImportacaoDeArquivoService();

    public List<Lancamento> importarArquivo(String caminhoArquivo, Date dataLancamento) throws RegraDeNegocioException {
        return importacaoDeArquivo.importarArquivo(caminhoArquivo, dataLancamento);
    }    

    public void save(Iterable<Lancamento> lancamentos) {
        lancamentoRepository.save(lancamentos);
    }

}
