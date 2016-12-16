/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.contra.cheque.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Otávio
 */

@Entity
public class Colaborador implements Serializable {
    
   //private final String SEQ_NAME = "SEQ_COLABORADOR";

    @Id
    @OneToOne
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID")
    private Usuario usuario; 
    
    
    @ManyToOne
    private CentroDeCusto centroDeCusto;
    
    @Column(name="NOME", nullable= false, length=50)
    private String nome;
    
    @Column(name="TIPO_SALARIO")
    private char tipoSalario;
    
    @Temporal(TemporalType.DATE)
    @Column(name="DT_ADIMISSAO")
    private Date admissao;
    

    public CentroDeCusto getCentroDeCusto() {
        return centroDeCusto;
    }

    public void setCentroDeCusto(CentroDeCusto centroDeCusto) {
        this.centroDeCusto = centroDeCusto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public char getTipoSalario() {
        return tipoSalario;
    }

    public void setTipoSalario(char tipoSalario) {
        this.tipoSalario = tipoSalario;
    }

    public Date getAdmissao() {
        return admissao;
    }

    public void setAdmissao(Date admissao) {
        this.admissao = admissao;
    }
    
}






