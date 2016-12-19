/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.contra.cheque.web;

import br.com.crescer.contra.cheque.entity.Lancamento;
import br.com.crescer.contra.cheque.service.LancamentoService;
import br.com.crescer.contra.cheque.service.exceptions.RegraDeNegocioException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    String admin(String caminho, RedirectAttributes redirectAttributes) {

        try {
            if (caminho != null) {
                Iterable<Lancamento> lancamentos = lancamentoService.importarArquivo(caminho, new Date());
                lancamentoService.save(lancamentos);
            }
        } catch (RegraDeNegocioException e) {
            redirectAttributes.addFlashAttribute("msg", e.getMessage());
            return "redirect:admin";
        }

        return "admin";
    }
}
