
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
@Table(name = "CENTRO_CUSTO")
@XmlRootElement
public class CentroCusto implements Serializable {

    private static final String SQ_NAME = "SEQ_CENTRO_CUSTO";

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = SQ_NAME)
    @SequenceGenerator(name = SQ_NAME, sequenceName = SQ_NAME, allocationSize = 1)
    @Column(name = "ID_CENTRO_CUSTO")
    private Long idCentroCusto;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "NOME")
    private String nome;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCentroCusto")
    private List<Colaborador> colaboradores;

    public CentroCusto() {
    }

    public CentroCusto(String nome) {
        this.nome = nome;
    }
    
    public Long getIdCentroCusto() {
        return idCentroCusto;
    }

    public void setIdCentroCusto(Long idCentroCusto) {
        this.idCentroCusto = idCentroCusto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @XmlTransient
    public List<Colaborador> getColaboradores() {
        return colaboradores;
    }

    public void setColaboradores(List<Colaborador> colaboradores) {
        this.colaboradores = colaboradores;
    }

    public boolean equals(CentroCusto centroCusto) {
        if (this == centroCusto) {
            return true;
        }
        if (centroCusto == null) {
            return false;
        }
        if (!this.nome.equals(centroCusto.nome)) {
            return false;
        }
        return this.idCentroCusto.equals(centroCusto.idCentroCusto);
    }
    
}
