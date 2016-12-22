
package br.com.crescer.contra.cheque.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author matha
 */
@Entity
@Table(name = "AGENCIA")
@XmlRootElement
public class Agencia implements Serializable {

    private static final String SQ_NAME = "SEQ_AGENCIA";

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = SQ_NAME)
    @SequenceGenerator(name = SQ_NAME, sequenceName = SQ_NAME, allocationSize = 1)
    @Column(name = "ID_AGENCIA")
    private Long idAgencia;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "CODIGO")
    private String codigo;
    
    @JoinColumn(name = "ID_BANCO", referencedColumnName = "ID_BANCO")
    @ManyToOne(optional = false)
    private Banco idBanco;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAgencia")
    private List<Conta> contas;

    public Agencia() {
    }

    public Agencia(String codigo, Banco idBanco) {
        this.codigo = codigo;
        this.idBanco = idBanco;
    }

    public Long getIdAgencia() {
        return idAgencia;
    }

    public void setIdAgencia(Long idAgencia) {
        this.idAgencia = idAgencia;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Banco getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Banco idBanco) {
        this.idBanco = idBanco;
    }

    @XmlTransient
    public List<Conta> getContas() {
        return contas;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }

    public boolean equals(Agencia agencia) {
        if (this == agencia) {
            return true;
        }
        if (agencia == null) {
            return false;
        }
        if (!this.codigo.equals(agencia.codigo)) {
            return false;
        }
        if (!this.idAgencia.equals(agencia.idAgencia)) {
            return false;
        }
        return this.idBanco.equals(agencia.idBanco);
    }
    
}
