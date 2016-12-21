/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.contra.cheque.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author matha
 */
@Entity
@Table(name = "LANCAMENTO")
@XmlRootElement
public class Lancamento implements Serializable {

    private static final String SQ_NAME = "SEQ_LANCAMENTO";

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = SQ_NAME)
    @SequenceGenerator(name = SQ_NAME, sequenceName = SQ_NAME, allocationSize = 1)
    @Column(name = "ID_LANCAMENTO")
    private Long idLancamento;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "DESCRICAO")
    private String descricao;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATA")
   
    @Temporal(TemporalType.DATE)
    private Date data;

    @Basic(optional = false)
    @NotNull
    @Column(name = "TIPO")
    private Character tipo;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "COD_CONTA")
    private String codConta;

    @Column(name = "BASE")
    private Double base;

    @Column(name = "VALOR_PARAM")
    private Double valorParam;

    @Column(name = "TOTAL")
    private Double total;

    @JoinColumn(name = "ID_COLABORADOR", referencedColumnName = "ID_COLABORADOR")
    @ManyToOne(optional = false)
    private Colaborador idColaborador;

    public Lancamento() {
    }

    public Lancamento(Long idLancamento, String descricao, Date data, Character tipo, String codConta, Double base, Double valorParam, Double total, Colaborador idColaborador) {
        this.idLancamento = idLancamento;
        this.descricao = descricao;
        this.data = data;
        this.tipo = tipo;
        this.codConta = codConta;
        this.base = base;
        this.valorParam = valorParam;
        this.total = total;
        this.idColaborador = idColaborador;
    }

    public Long getIdLancamento() {
        return idLancamento;
    }

    public void setIdLancamento(Long idLancamento) {
        this.idLancamento = idLancamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Character getTipo() {
        return tipo;
    }

    public void setTipo(Character tipo) {
        this.tipo = tipo;
    }

    public String getCodConta() {
        return codConta;
    }

    public void setCodConta(String codConta) {
        this.codConta = codConta;
    }

    public Double getBase() {
        return base;
    }

    public void setBase(Double base) {
        this.base = base;
    }

    public Double getValorParam() {
        return valorParam;
    }

    public void setValorParam(Double valorParam) {
        this.valorParam = valorParam;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Colaborador getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(Colaborador idColaborador) {
        this.idColaborador = idColaborador;
    }
    
    public boolean equals(Lancamento lancamento) {
        if (this == lancamento) {
            return true;
        }
        if (lancamento == null) {
            return false;
        }
        if (!this.descricao.equals(lancamento.descricao)) {
            return false;
        }
        if (!this.codConta.equals(lancamento.codConta)) {
            return false;
        }
        if (!this.idLancamento.equals(lancamento.idLancamento)) {
            return false;
        }
        if (this.data.compareTo(lancamento.data) == 1) {
            return false;
        }
        if (!this.tipo.equals(lancamento.tipo)) {
            return false;
        }
        if (Double.compare(this.base, lancamento.base) == 1) {
            return false;
        }
        if (Double.compare(this.valorParam, lancamento.valorParam) == 1) {
            return false;
        }
        if (Double.compare(this.total, lancamento.total) == 1) {
            return false;
        }
        return this.idColaborador == lancamento.idColaborador;
    }
}
