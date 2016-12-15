/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.contra.cheque.entity;

import java.io.Serializable;
import javax.persistence.*;
import static javax.persistence.GenerationType.SEQUENCE;

/**
 *
 * @author mateus.teixeira
 */
@Entity
public class Agencia implements Serializable {

    private final String SEQ_NAME = "SEQ_AGENCIA";

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private Long id;

    @Column(name = "CODIGO", nullable = false, length = 6)
    private String codigo;

    @ManyToOne
    @JoinColumn(name = "ID_BANCO", referencedColumnName = "ID")
    private Banco banco;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }
    
}
