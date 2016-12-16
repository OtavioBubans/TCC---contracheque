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
@Table(name="PERGUNTA_SEGURANCA")
public class PerguntaDeSegurança implements Serializable {
    
    
     private final String SEQ_NAME = "SEQ_PERGUNTA_SEGURANCA";

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "ID_COLABORADOR", referencedColumnName = "ID")
    private Colaborador colaborador;
    
    
    @Column(name="PERGUNTA", nullable=false,length=100)
    private String pergunta;
    
    @Column(name="RESPOSTA", nullable=false,length=50)
    private String resposta;

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

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }
    
    
    
    
    
}
