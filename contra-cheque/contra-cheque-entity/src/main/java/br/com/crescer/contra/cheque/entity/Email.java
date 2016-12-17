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
    private String assunto;
    private String destinatario;
    private final Date data;

    public Email(String texto, String assunto, String destinatario) {
        this.texto = texto;
        this.assunto = assunto;
        this.destinatario = destinatario;
        this.data = new Date();
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

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public Date getData() {
        return data;
    }
    
    
    
    
    
}
