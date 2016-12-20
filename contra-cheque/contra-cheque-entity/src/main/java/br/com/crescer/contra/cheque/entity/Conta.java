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
import javax.persistence.OneToOne;
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
@Table(name = "CONTA")
@XmlRootElement
public class Conta implements Serializable {

    private static final String SQ_NAME = "SEQ_CONTA";

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = SQ_NAME)
    @SequenceGenerator(name = SQ_NAME, sequenceName = SQ_NAME, allocationSize = 1)
    @Column(name = "ID_CONTA")
    private Long idConta;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "CODIGO")
    private String codigo;
    
    @JoinColumn(name = "ID_AGENCIA", referencedColumnName = "ID_AGENCIA")
    @ManyToOne(optional = false)
    private Agencia idAgencia;
    
    @JoinColumn(name = "ID_CONTA", referencedColumnName = "ID_COLABORADOR", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Colaborador colaborador;

    public Conta() {
    }

    public Conta(Long idConta, String codigo, Agencia idAgencia, Colaborador colaborador) {
        this.idConta = idConta;
        this.codigo = codigo;
        this.idAgencia = idAgencia;
        this.colaborador = colaborador;
    }

    public Long getIdConta() {
        return idConta;
    }

    public void setIdConta(Long idConta) {
        this.idConta = idConta;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Agencia getIdAgencia() {
        return idAgencia;
    }

    public void setIdAgencia(Agencia idAgencia) {
        this.idAgencia = idAgencia;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public boolean equals(Conta conta) {
        if (this == conta) {
            return true;
        }
        if (conta == null) {
            return false;
        }
        if (!this.codigo.equals(conta.codigo)) {
            return false;
        }
        if (!this.idConta.equals(conta.idConta)) {
            return false;
        }
        if (!this.idAgencia.equals(conta.idAgencia)) {
            return false;
        }
        return this.colaborador.equals(conta.colaborador);
    }
}
