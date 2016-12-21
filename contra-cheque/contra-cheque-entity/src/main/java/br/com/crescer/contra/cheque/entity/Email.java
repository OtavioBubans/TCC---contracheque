/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.contra.cheque.entity;

import java.util.Date;

/**
 *
 * @author Otávio
 */
public class Email {
    
    private String texto;
    private final String assunto;
    private String destinatario;
    private final String remetente;
    

    public Email( String destinatario, String ip, String usuario) {
        this.assunto = "EMAIL SEGUNÇA CONTRACHEQUE";
        this.destinatario = destinatario;
        this.remetente = "contracheque.cwi@gmail.com"; // email do admin;
        this.texto = "Houve uma incorfomidade suspeita no acesso do contracheque.   "
                    + "     Informações do IP: "+ip
                    + "     Colaborador: "+usuario;
         
    }
    
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getAssunto() {
        return assunto;
    }
    
    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getEmailAdmin() {
        return  remetente;
    }

    
    

   
    
    
    
    
    
}
