package br.com.crescer.contra.cheque.web;

import br.com.crescer.contra.cheque.entity.Lancamento;
import br.com.crescer.contra.cheque.entity.enumeration.TipoOperacaoLog;
import br.com.crescer.contra.cheque.service.DateService;
import br.com.crescer.contra.cheque.service.LancamentoService;
import br.com.crescer.contra.cheque.service.exceptions.RegraDeNegocioException;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdminController {

    @Autowired
    Environment environment;
    @Autowired
    DateService dateService;
    @Autowired
    LancamentoService lancamentoService;
    @Autowired
    ContraChequeUtils utils;

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value = "/relatorio")
    String relatorio(Model model) {
        model.addAttribute("anos", dateService.popularAnosAdmin());
        model.addAttribute("meses", dateService.popularMeses());
        return "relatorio";
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value = "/relatorio", method = RequestMethod.POST)
    String relatorio(Model model, String mes, Long ano, RedirectAttributes redirectAttributes) throws RegraDeNegocioException {
        Date data = dateService.dataSelecionada(mes, ano);

        double totalBeneficios = 0.0;
        double totalDescontos = 0.0;
        List<Lancamento> proventos = lancamentoService.pesquisarPorMesETipo(data, 'P');
        List<Lancamento> descontos = lancamentoService.pesquisarPorMesETipo(data, 'D');

        totalDescontos = descontos.stream().map(desconto -> desconto.getTotal()).reduce(totalDescontos, (acumulador, item) -> acumulador + item);
        totalBeneficios = proventos.stream().map(provento -> provento.getTotal()).reduce(totalBeneficios, (acumulador, item) -> acumulador + item);

        model.addAttribute("anos", dateService.popularAnosAdmin());
        model.addAttribute("meses", dateService.popularMeses());
        model.addAttribute("totalBeneficios", String.format("R$ %1$,.2f", totalBeneficios));
        model.addAttribute("totalDescontos", String.format("R$ %1$,.2f", totalDescontos));

        return "relatorio";
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    String admin(@RequestParam("file") MultipartFile file, String mes, Long ano, RedirectAttributes redirectAttributes) {
        String nomeArquivo = file.getOriginalFilename();
        String diretorio = environment.getProperty("upload.arquivo");
        Path path = Paths.get(diretorio, nomeArquivo);
        Stream<String> arquivoImportado = null;
        Date dataDeImportacao = new Date();

        if (file.isEmpty() || file.getSize() == 0) {
            redirectAttributes.addFlashAttribute("msg", "O arquivo importado está vazio");
            return "redirect:home";
        }
        if (!nomeArquivo.contains(".txt")) {
            redirectAttributes.addFlashAttribute("msg", "O arquivo não está no formato .txt");
            return "redirect:home";
        }
        try (BufferedOutputStream buffer = new BufferedOutputStream(new FileOutputStream(new File(path.toString())))) {
            buffer.write(file.getBytes());
            arquivoImportado = Files.lines(path);
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("msg", "Ocorreu um erro na leitura ou na importação do arquivo, tente novamente");
            return "redirect:home";
        }

        try {
            dataDeImportacao = dateService.dataSelecionada(mes, ano);
            if (dataDeImportacao.equals(new Date())) {
                redirectAttributes.addFlashAttribute("msg", "Ocorreu um erro na data informada, tente novamente");
                return "redirect:home";
            }
            lancamentoService.importarArquivo(arquivoImportado, dataDeImportacao);
        } catch (RegraDeNegocioException ex) {
            redirectAttributes.addFlashAttribute("msg", ex.getMessage());
            return "redirect:home";
        }
        utils.registrarOperacao(utils.usuarioLogado().getColaborador(), TipoOperacaoLog.IMPORTACAO, null);
        redirectAttributes.addFlashAttribute("success", "Arquivo importado com sucesso");

        return "redirect:home";
    }

}
