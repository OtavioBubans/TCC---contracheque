/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.contra.cheque.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Otávio
 */

@Entity
@Table(name="LANCAMENTO")
public class Lancamento implements Serializable {
    
     private final String SEQ_NAME = "SEQ_LANCAMENTO";

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name="ID_COLABORADOR",referencedColumnName="ID")
    private Colaborador colaboraador;
    
    @Column(name="DESCRICAO", nullable=false,length=40)
    private String descricao;
    
    @Column(name="TIPO", nullable=false,length=1)
    private char tipo;
    
    @Column(name="COD_CONTA", nullable=false, length=3)
    private String codConta;
    
    
    @Column(name="VALOR_PARAM",nullable=false)
    private double valorParam;
    
    @Column(name="BASE", nullable=false)
    private double base;
    
    
    @Column(name="TOTAl", nullable=false)
    private double total;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Colaborador getColaboraador() {
        return colaboraador;
    }

    public void setColaboraador(Colaborador colaboraador) {
        this.colaboraador = colaboraador;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public String getCodConta() {
        return codConta;
    }

    public void setCodConta(String codConta) {
        this.codConta = codConta;
    }

    public double getValorParam() {
        return valorParam;
    }

    public void setValorParam(double valorParam) {
        this.valorParam = valorParam;
    }

    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
    
    
    
    
}
