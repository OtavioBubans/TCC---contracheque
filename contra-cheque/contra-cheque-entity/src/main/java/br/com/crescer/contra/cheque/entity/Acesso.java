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
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author otavio.bubans
 */

@Entity
@Table(name = "ACESSO")
public class Acesso implements Serializable {
    
    private static final String SQ_NAME = "SQ_ACESSO";
    
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = SQ_NAME)
    @SequenceGenerator(name = SQ_NAME, sequenceName = SQ_NAME, allocationSize = 1)
    @Column(name = "ID_ACESSO")
    private int id;
    
    
    @ManyToOne
    private Colaborador colaborador;
    
    @Basic(optional = false)
    @Column(name = "DIA_SEMANA") 
    private String diaDaSemana;
    
    
    @Basic(optional = false)
    @Column(name = "HORA_ACESSO")
    private int horaAcesso;
    
    @Basic(optional = false)
    @Column(name = "QTD_ACESSO")
    private int qtdAcesso;
    
    
    
    
}
