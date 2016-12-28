package br.com.crescer.contra.cheque.web;

import br.com.crescer.contra.cheque.entity.Acesso;
import br.com.crescer.contra.cheque.entity.Colaborador;
import br.com.crescer.contra.cheque.entity.Email;
import br.com.crescer.contra.cheque.entity.Lancamento;
import br.com.crescer.contra.cheque.service.UsuarioService;
import br.com.crescer.contra.cheque.entity.Usuario;
import br.com.crescer.contra.cheque.entity.enumeration.TipoOperacaoLog;
import br.com.crescer.contra.cheque.service.AcessoService;
import br.com.crescer.contra.cheque.service.DateService;
import br.com.crescer.contra.cheque.service.EmailService;
import br.com.crescer.contra.cheque.service.LancamentoService;
import br.com.crescer.contra.cheque.service.LogService;
import br.com.crescer.contra.cheque.service.exceptions.RegraDeNegocioException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {

    @Autowired
    UsuarioService usuarioService;
    @Autowired
    LogService logService;
    @Autowired
    AcessoService acessoService;
    @Autowired
    EmailService emailService;
    @Autowired
    LancamentoService lancamentoService;
    @Autowired
    DateService dateService;
    @Autowired
    ContraChequeUtils utils;

    @Secured({"ROLE_USER"})
    @RequestMapping("/home")
    String home(Model model) {
        registrarAcesso();
        String role = utils.usuarioLogado().getFuncao();
        model.addAttribute("anos", dateService.popularAnosAdmin());
        model.addAttribute("meses", dateService.popularMeses());
        if (role.equals("admin")) {
            return "admin";
        } else {
            return "home";
        }
    }

    @Secured({"ROLE_USER"})
    @RequestMapping(value = "/")
    String index() {
        return "apresentacao";
    }

    @Secured({"ROLE_USER"})
    @RequestMapping(value = "/home", method = RequestMethod.POST)
    String home(Model model, String mes, Long ano, RedirectAttributes redirectAttributes) throws RegraDeNegocioException {
        Date dataPesquisada = dateService.dataSelecionada(mes, ano);
        Colaborador colaborador = utils.usuarioLogado().getColaborador();
        List<Lancamento> listaDescontos = lancamentoService.pesquisarPorUsuarioMesETipo(colaborador, dataPesquisada, 'D');
        List<Lancamento> listaProventos = lancamentoService.pesquisarPorUsuarioMesETipo(colaborador, dataPesquisada, 'P');
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        if (listaDescontos.isEmpty() && listaProventos.isEmpty()) {
            redirectAttributes.addFlashAttribute("msg", "Não foram encontrados registros nessa consulta");
            return "redirect: home";
        }
        model.addAttribute("usuario", colaborador);
        model.addAttribute("admissao", formato.format(colaborador.getAdminssao()));
        model.addAttribute("descontos", listaDescontos);
        model.addAttribute("proventos", listaProventos);
        model.addAttribute("totalProventos", lancamentoService.pesquisarPorUsuarioMesECodigo(colaborador, dataPesquisada, "911"));
        model.addAttribute("totalDescontos", lancamentoService.pesquisarPorUsuarioMesECodigo(colaborador, dataPesquisada, "912"));
        model.addAttribute("totalLiquido", lancamentoService.pesquisarPorUsuarioMesECodigo(colaborador, dataPesquisada, "913"));
        model.addAttribute("irrf", lancamentoService.pesquisarPorUsuarioMesECodigo(colaborador, dataPesquisada, "711"));
        model.addAttribute("salarioBase", lancamentoService.pesquisarPorUsuarioMesECodigo(colaborador, dataPesquisada, "900"));
        model.addAttribute("inss", lancamentoService.pesquisarPorUsuarioMesECodigo(colaborador, dataPesquisada, "901"));
        model.addAttribute("fgts", lancamentoService.pesquisarPorUsuarioMesECodigo(colaborador, dataPesquisada, "902"));

        return "contracheque";
    }

    private void registrarAcesso() {
        Colaborador colaboradorLogado = utils.usuarioLogado().getColaborador();
        Calendar c = new GregorianCalendar();
        c.setTime(new Date());
        List<String> diasSemana = Arrays.asList("domingo", "segunda-feira", "terca-feira", "quarta-feira", "quinta-feira", "sexta-feira", "sabado");
        int dia = c.get(Calendar.DAY_OF_WEEK);
        String diaSemana = diasSemana.get(dia - 1);
        int hora = c.get(Calendar.HOUR_OF_DAY);
        Acesso acessoAtual = acessoService.findByIdColaboradorAndDiaSemanaAndHora(colaboradorLogado, diaSemana, hora);
        if (acessoAtual == null) {
            acessoAtual = new Acesso();
            acessoAtual.setDiaSemana(diaSemana);
            acessoAtual.setHora(hora);
            acessoAtual.setIdColaborador(colaboradorLogado);
            acessoAtual.setQtdAcessos(1);
        } else {
            acessoAtual.setQtdAcessos(acessoAtual.getQtdAcessos() + 1);
        }
        acessoService.save(acessoAtual);
        utils.registrarOperacao(colaboradorLogado, TipoOperacaoLog.ACESSO, null);
        verificarIpSuspeito(colaboradorLogado);
    }

    private void verificarIpSuspeito(Colaborador colaboradorLogado) {
        String ipLogado = utils.pegarIpLogado();
        Long registros = logService.findByIdColaboradorAndTipoOperacao(colaboradorLogado, TipoOperacaoLog.ACESSO);
        Long registrosPorIp = logService.findByIdColaboradorAndTipoOperacaoAndIp(colaboradorLogado, TipoOperacaoLog.ACESSO, ipLogado);
        if (registros >= 10 && (ipLogado.equals("unknown") || registrosPorIp == 1)) {
            adicionarInvalidez(ipLogado);
        }
        verificarAcessoSuspeito(colaboradorLogado, ipLogado);
    }

    private void verificarAcessoSuspeito(Colaborador colaboradorLogado, String ipLogado) {
        List<Acesso> acessos = colaboradorLogado.getAcessos();
        Integer totalAcessos = 0;

        for (Acesso acesso : acessos) {
            totalAcessos += acesso.getQtdAcessos();
            double percentualAcesso = (double) acesso.getQtdAcessos() / (double) totalAcessos;

            if (totalAcessos >= 10 && percentualAcesso < 0.15) {
                adicionarInvalidez(ipLogado);
                break;
            }
        }
    }

    private void adicionarInvalidez(String ip) {
        Usuario usuarioLogado = utils.usuarioLogado();
        int loginsSuspeitos = usuarioLogado.getLoginsSuspeitos();
        usuarioLogado.setLoginsSuspeitos(loginsSuspeitos + 1);
        if (loginsSuspeitos == 1) {
            Email novoEmail = new Email(usuarioLogado.getEmail(), ip, usuarioLogado.getColaborador().getNome());
            emailService.enviarEmail(novoEmail);
        } else {
            usuarioService.save(usuarioLogado);
        }
    }
}
