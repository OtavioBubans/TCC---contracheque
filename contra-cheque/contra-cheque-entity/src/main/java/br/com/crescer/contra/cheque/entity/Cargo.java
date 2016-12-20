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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Otávio
 */
@Entity
@Table(name = "CARGO")
public class Cargo implements Serializable {
    
    private static final String SQ_NAME = "SEQ_CARGO";
    
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = SQ_NAME)
    @SequenceGenerator(name = SQ_NAME, sequenceName = SQ_NAME, allocationSize = 1)
    @Column(name = "ID_CARGO")
    private Long idCargo;
    
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOME")
    private String nome;

    public Cargo() {
    }

    public Cargo(Long idCargo, String nome) {
        this.idCargo = idCargo;
        this.nome = nome;
    }
    
    public Long getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Long idCargo) {
        this.idCargo = idCargo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean equals(Cargo cargo) {
        if (this == cargo) {
            return true;
        }
        if (cargo == null) {
            return false;
        }
        if (!this.nome.equals(cargo.nome)) {
            return false;
        }
        return this.idCargo.equals(cargo.idCargo);
    }
    
}
