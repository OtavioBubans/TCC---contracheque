/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.contra.cheque.web;

import br.com.crescer.contra.cheque.entity.Lancamento;
import br.com.crescer.contra.cheque.service.LancamentoService;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author mateus.teixeira
 */
@Controller
public class AdminController {

    @Autowired
    LancamentoService lancamentoService;

    @Secured({"ROLE_ADMIN"})
    @RequestMapping("/admin")
    String admin(String caminho) {
        try {
            if (caminho != null) {
                Iterable<Lancamento> auxiliar = lancamentoService.importarArquivo(caminho, new Date());
                lancamentoService.save(auxiliar);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return "admin";
    }
}
