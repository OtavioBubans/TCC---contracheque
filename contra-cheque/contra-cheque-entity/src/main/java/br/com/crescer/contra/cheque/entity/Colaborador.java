/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.contra.cheque.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Otávio
 */
@Entity
@Table(name = "COLABORADOR")
public class Colaborador implements Serializable {

    @Id
    @Column(name = "ID_COLABORADOR")
    private Long id;

    @ManyToOne
    private CentroDeCusto centroDeCusto;

    @Column(name = "NOME", nullable = false, length = 50)
    private String nome;

    @Column(name = "TIPO_SALARIO")
    private char tipoSalario;

    @Temporal(TemporalType.DATE)
    @Column(name = "DT_ADIMISSAO")
    private Date admissao;

    @Temporal(TemporalType.DATE)
    @Column(name = "DT_NASCIMENTO")
    private Date nascimento;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Usuario usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

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

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

}
