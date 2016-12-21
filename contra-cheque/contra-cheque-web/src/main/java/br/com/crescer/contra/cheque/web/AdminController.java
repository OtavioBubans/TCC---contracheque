package br.com.crescer.contra.cheque.web;

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
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author mateus.teixeira
 */
@Controller
public class AdminController {

    @Autowired
    LancamentoService lancamentoService;
    @Autowired
    private Environment environment;

    @RequestMapping("/admin")
    String admin() {
        return "admin";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    String admin(@RequestParam("file") MultipartFile file, Long ano, Long mes, RedirectAttributes redirectAttributes) {
        Date date = new Date();
        
        
        String nomeArquivo = file.getOriginalFilename();
        String diretorio = environment.getProperty("upload.arquivo");
        Path path = Paths.get(diretorio, nomeArquivo);
        Stream<String> arquivoImportado = null;

        if (file.isEmpty() || file.getSize() == 0) {
            return "redirect:admin";
        }
        if(!nomeArquivo.contains(".txt")){
            redirectAttributes.addFlashAttribute("msg", "Errouuuuu");
            return "redirect:admin";
        }
        try (BufferedOutputStream buffer = new BufferedOutputStream(new FileOutputStream(new File(path.toString())))) {
            buffer.write(file.getBytes());
            arquivoImportado = Files.lines(path);
        } catch (IOException e) {
            return "redirect:admin";
        }

        try {
            lancamentoService.importarArquivo(arquivoImportado, new Date());
        } catch (RegraDeNegocioException ex) {
            redirectAttributes.addFlashAttribute("msg", ex.getMessage());
            return "redirect:admin";
        }

        redirectAttributes.addFlashAttribute("msg", "Arquivo importado com sucesso");
        return "admin";
    }
}
