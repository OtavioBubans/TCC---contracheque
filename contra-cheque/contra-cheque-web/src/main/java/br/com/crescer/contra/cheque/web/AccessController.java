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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
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
    String login(Model model, Usuario usuario) {
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
        String[] inputs = conteudoInputs.split("&");
        Usuario usuario = usuarioService.findByEmail(inputs[0].replace("username=", "").replace("%40", "@"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        Date dataInserida = new Date();
        try {
            dataInserida = sdf.parse(inputs[2].replace("data=", ""));
        } catch (ParseException ex) {
            Logger.getLogger(AccessController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Calendar c = new GregorianCalendar();
        c.setTime(dataInserida);
        Calendar c1 = new GregorianCalendar();
        c1.setTime(usuario.getColaborador().getNascimento());
        boolean senhaIgual = usuario.getSenha().equals(inputs[1].replace("password=", ""));
        boolean dataNascimentoIgual = c.get(Calendar.DAY_OF_MONTH) == c1.get(Calendar.DAY_OF_MONTH)
                                   && c.get(Calendar.MONTH) == c1.get(Calendar.MONTH)
                                   && c.get(Calendar.YEAR) == c1.get(Calendar.YEAR);
        if (senhaIgual && dataNascimentoIgual) {
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
}
