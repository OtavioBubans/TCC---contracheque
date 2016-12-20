package br.com.crescer.contra.cheque.web;

import br.com.crescer.contra.cheque.entity.Lancamento;
import br.com.crescer.contra.cheque.service.LancamentoService;
import br.com.crescer.contra.cheque.service.exceptions.RegraDeNegocioException;
import java.io.IOException;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author mateus.teixeira
 */
@Controller
public class AdminController {

    @Autowired
    LancamentoService lancamentoService;

    @RequestMapping("/admin")
    String admin(String caminho, RedirectAttributes redirectAttributes) throws IOException {

        try {
            if (caminho != null) {
                lancamentoService.importarArquivo(caminho, new Date());
            }
        } catch (RegraDeNegocioException e) {
            redirectAttributes.addFlashAttribute("msg", e.getMessage());
            return "redirect:admin";
        }
        
        redirectAttributes.addFlashAttribute("msg", "Arquivo importado com sucesso");
        return "admin";
    }
}


