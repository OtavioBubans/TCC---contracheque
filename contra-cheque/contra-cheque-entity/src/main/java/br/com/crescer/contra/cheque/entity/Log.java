/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.contra.cheque.entity;

import br.com.crescer.contra.cheque.entity.enumeration.TipoOperacaoLog;
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
@Table(name = "LOGGER")
@XmlRootElement
public class Log implements Serializable {

    private static final String SQ_NAME = "SEQ_LOGGER";

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = SQ_NAME)
    @SequenceGenerator(name = SQ_NAME, sequenceName = SQ_NAME, allocationSize = 1)
    @Column(name = "ID_LOG")
    private Long idLog;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "IP")
    private String ip;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATA_HORA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHora;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIPO_OPERACAO")
    private TipoOperacaoLog tipoOperacao;
    
    @Column(name = "DATA_CONSULTA_CC")
    @Temporal(TemporalType.DATE)
    private Date dataConsultaCc;
    
    @JoinColumn(name = "ID_COLABORADOR", referencedColumnName = "ID_COLABORADOR")
    @ManyToOne(optional = false)
    private Colaborador idColaborador;

    public Log() {
    }

    public Log(Long idLog, String ip, Date dataHora, TipoOperacaoLog tipoOperacao, Date dataConsultaCc, Colaborador idColaborador) {
        this.idLog = idLog;
        this.ip = ip;
        this.dataHora = dataHora;
        this.tipoOperacao = tipoOperacao;
        this.dataConsultaCc = dataConsultaCc;
        this.idColaborador = idColaborador;
    }

    public Long getIdLog() {
        return idLog;
    }

    public void setIdLog(Long idLog) {
        this.idLog = idLog;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public TipoOperacaoLog getTipoOperacao() {
        return tipoOperacao;
    }

    public void setTipoOperacao(TipoOperacaoLog tipoOperacao) {
        this.tipoOperacao = tipoOperacao;
    }

    public Date getDataConsultaCc() {
        return dataConsultaCc;
    }

    public void setDataConsultaCc(Date dataConsultaCc) {
        this.dataConsultaCc = dataConsultaCc;
    }

    public Colaborador getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(Colaborador idColaborador) {
        this.idColaborador = idColaborador;
    }

    public boolean equals(Log log) {
        if (this == log) {
            return true;
        }
        if (log == null) {
            return false;
        }
        if (!this.ip.equals(log.ip)) {
            return false;
        }
        if (!this.idLog.equals(log.idLog)) {
            return false;
        }
        if (this.dataHora.compareTo(log.dataHora) == 1) {
            return false;
        }
        if (!this.tipoOperacao.equals(log.tipoOperacao)) {
            return false;
        }
        if (!this.dataConsultaCc.equals(log.dataConsultaCc)) {
            return false;
        }
        return this.idColaborador.equals(log.idColaborador);
    }
}
