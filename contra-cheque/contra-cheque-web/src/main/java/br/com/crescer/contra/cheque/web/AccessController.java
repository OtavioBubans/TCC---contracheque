/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.contra.cheque.web;

import br.com.crescer.contra.cheque.entity.Usuario;
import br.com.crescer.contra.cheque.service.UsuarioService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author matha
 */
@Controller
public class AccessController {

    @Autowired
    UsuarioService usuarioService;

    @RequestMapping("/login")
    String login(Model model, Usuario usuario, @AuthenticationPrincipal User user) {
       if(user != null){
           return "redirect:home";
       }
        model.addAttribute("formAction", "/login");
        model.addAttribute("autenticacaoExtra", false);
        return "login";
    }

    @RequestMapping(value = "/login/autenticar", method = GET)
    String autenticacaoExtra(Model model) {
        model.addAttribute("formAction", "/login/autenticar");
        model.addAttribute("autenticacaoExtra", true);
        return "login";
    }

    @ResponseBody
    @RequestMapping(value = "/login/autenticar", method = POST)
    boolean autenticarExtra(Model model, @RequestBody String conteudoInputs) {
        byte[] valorDecodificado = Base64.decodeBase64(conteudoInputs);
        String conteudo = new String(valorDecodificado);
        String[] inputs = conteudo.split("&");
        Usuario usuario = usuarioService.findByEmail(inputs[0].replace("username=", "").replace("%40", "@"));
        boolean senhaIgual = usuario.getSenha().equals(inputs[1].replace("password=", ""));
        boolean nascimentoIgual = compararNascimento(inputs[2].replace("data=", ""), usuario.getColaborador().getNascimento());
        if (senhaIgual && nascimentoIgual) {
            usuario.setLoginsSuspeitos(0);
            usuarioService.save(usuario);
            return true;
        }
        return false;
    }

    @RequestMapping("/logout")
    String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:login";
    }

    private boolean compararNascimento(String stringDataInserida, Date nascimento) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        Date dataInserida = new Date();
        try {
            dataInserida = sdf.parse(stringDataInserida);
        } catch (ParseException ex) {
            Logger.getLogger(AccessController.class.getName()).log(Level.SEVERE, null, ex);
        }
        int teste = dataInserida.compareTo(nascimento);
        if (teste == 1) {
            return false;
        }
        return true;
    }
}
