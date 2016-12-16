/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.contra.cheque.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author otavio.bubans
 */
@Entity
@Table(name = "ACESSO")
public class Acesso implements Serializable {

    private static final String SQ_NAME = "SEQ_ACESSO";

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = SQ_NAME)
    @SequenceGenerator(name = SQ_NAME, sequenceName = SQ_NAME, allocationSize = 1)
    @Column(name = "ID_ACESSO")
    private int id;

    @ManyToOne
    private Colaborador colaborador;

    @Basic(optional = false)
    @Column(name = "DIA_SEMANA")
    private String diaDaSemana;

    @Basic(optional = false)
    @Column(name = "HORA_ACESSO")
    private int horaAcesso;

    @Basic(optional = false)
    @Column(name = "QTD_ACESSO")
    private int qtdAcesso;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public String getDiaDaSemana() {
        return diaDaSemana;
    }

    public void setDiaDaSemana(String diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }

    public int getHoraAcesso() {
        return horaAcesso;
    }

    public void setHoraAcesso(int horaAcesso) {
        this.horaAcesso = horaAcesso;
    }

    public int getQtdAcesso() {
        return qtdAcesso;
    }

    public void setQtdAcesso(int qtdAcesso) {
        this.qtdAcesso = qtdAcesso;
    }

}
