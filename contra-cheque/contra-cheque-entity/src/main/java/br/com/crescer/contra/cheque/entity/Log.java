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
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Otávio
 */
@Entity
public class Log implements Serializable {
    
    private final String SEQ_NAME = "SEQ_LOG";

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "ID_COLABORADOR", referencedColumnName = "ID")
    private Colaborador colaborador;
    
    @Column(name="IP", nullable= false, length = 15)
    private String ip;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="DATA_HORA",nullable= false)
    private Date dataEHora;
    
    @Temporal(TemporalType.DATE)
    @Column(name="DATA_CONSULTA", nullable=false)
    private Date dataConsultaCC;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getDataEHora() {
        return dataEHora;
    }

    public void setDataEHora(Date dataEHora) {
        this.dataEHora = dataEHora;
    }

    public Date getDataConsultaCC() {
        return dataConsultaCC;
    }

    public void setDataConsultaCC(Date dataConsultaCC) {
        this.dataConsultaCC = dataConsultaCC;
    }
    
    
        
   
}
