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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author matha
 */
@Entity
@Table(name = "ACESSO")
@XmlRootElement
public class Acesso implements Serializable {

    private static final String SQ_NAME = "SEQ_ACESSO";

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = SQ_NAME)
    @SequenceGenerator(name = SQ_NAME, sequenceName = SQ_NAME, allocationSize = 1)
    @Column(name = "ID_ACESSO")
    private Long idAcesso;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 13)
    @Column(name = "DIA_SEMANA")
    private String diaSemana;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "HORA")
    private Integer hora;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "QTD_ACESSOS")
    private Integer qtdAcessos;

    @JoinColumn(name = "ID_COLABORADOR", referencedColumnName = "ID_COLABORADOR")
    @ManyToOne(optional = false)
    private Colaborador idColaborador;

    public Acesso() {
    }

    public Acesso(Long idAcesso, String diaSemana, Integer hora, Integer qtdAcessos, Colaborador idColaborador) {
        this.idAcesso = idAcesso;
        this.diaSemana = diaSemana;
        this.hora = hora;
        this.qtdAcessos = qtdAcessos;
        this.idColaborador = idColaborador;
    }

    public Long getIdAcesso() {
        return idAcesso;
    }

    public void setIdAcesso(Long idAcesso) {
        this.idAcesso = idAcesso;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public Integer getHora() {
        return hora;
    }

    public void setHora(Integer hora) {
        this.hora = hora;
    }

    public Integer getQtdAcessos() {
        return qtdAcessos;
    }

    public void setQtdAcessos(Integer qtdAcessos) {
        this.qtdAcessos = qtdAcessos;
    }

    public Colaborador getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(Colaborador idColaborador) {
        this.idColaborador = idColaborador;
    }

    public boolean equals(Acesso acesso) {
        if (this == acesso) {
            return true;
        }
        if (acesso == null) {
            return false;
        }
        if (!this.diaSemana.equals(acesso.diaSemana)) {
            return false;
        }
        if (!this.idAcesso.equals(acesso.idAcesso)) {
            return false;
        }
        if (!this.hora.equals(acesso.hora)) {
            return false;
        }
        if (!this.qtdAcessos.equals(acesso.qtdAcessos)) {
            return false;
        }
        return this.idColaborador.equals(acesso.idColaborador);
    }
    
}
