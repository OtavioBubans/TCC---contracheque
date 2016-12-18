/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.contra.cheque.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author matha
 */
@Entity
@Table(name = "COLABORADOR")
@XmlRootElement
public class Colaborador implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_COLABORADOR")
    private Long idColaborador;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOME")
    private String nome;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIPO_SALARIO")
    private Character tipoSalario;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "NASCIMENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date nascimento;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "ADMISSAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date admissao;
       
    @JoinColumn(name = "ID_CARGO", referencedColumnName = "ID_CARGO")
    @ManyToOne(optional=false)
    private Cargo cargo;
    
    @JoinColumn(name = "ID_CENTRO_CUSTO", referencedColumnName = "ID_CENTRO_CUSTO")
    @ManyToOne(optional = false)
    private CentroCusto idCentroCusto;
    
    @JoinColumn(name = "ID_COLABORADOR", referencedColumnName = "ID_USUARIO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Usuario usuario;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idColaborador")
    private List<Acesso> acessos;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idColaborador")
    private List<Lancamento> lancamentos;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "colaborador")
    private Conta conta;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idColaborador")
    private List<Log> logs;

    public Long getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(Long idColaborador) {
        this.idColaborador = idColaborador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Character getTipoSalario() {
        return tipoSalario;
    }

    public void setTipoSalario(Character tipoSalario) {
        this.tipoSalario = tipoSalario;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public Date getAdminssao() {
        return admissao;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
    
    

    public void setAdminssao(Date admissao) {
        this.admissao = admissao;
    }

    public CentroCusto getIdCentroCusto() {
        return idCentroCusto;
    }

    public void setIdCentroCusto(CentroCusto idCentroCusto) {
        this.idCentroCusto = idCentroCusto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @XmlTransient
    public List<Acesso> getAcessos() {
        return acessos;
    }

    public void setAcessos(List<Acesso> acessos) {
        this.acessos = acessos;
    }

    @XmlTransient
    public List<Lancamento> getLancamentos() {
        return lancamentos;
    }

    public void setLancamentoCollection(List<Lancamento> lancamentos) {
        this.lancamentos = lancamentos;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    @XmlTransient
    public List<Log> getLogs() {
        return logs;
    }

    public void setLogs(List<Log> logs) {
        this.logs = logs;
    }
}
